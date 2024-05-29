package com.techcareer.business.services.impl;

import com.techcareer.bean.ModelMapperBeanClass;
import com.techcareer.bean.PasswordEncoderBeanClass;
import com.techcareer.business.dto.RegisterDto;
import com.techcareer.business.services.IRegisterServices;
import com.techcareer.data.entity.EmailEntity;
import com.techcareer.data.entity.RegisterEntity;
import com.techcareer.data.entity.RoleEntity;
import com.techcareer.data.repository.IEmailRepository;
import com.techcareer.data.repository.IRegisterRepository;
import com.techcareer.data.repository.IRoleRepository;
import com.techcareer.exception.HamitMizrakException;
import com.techcareer.exception.Resource404NotFoundException;
import com.techcareer.tokenmail.ForRegisterTokenEmailConfirmationEntity;
import com.techcareer.tokenmail.IForRegisterTokenEmailConfirmationEntity;
import com.techcareer.tokenmail.IForRegisterTokenEmailConfirmationServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// Lombok
@RequiredArgsConstructor
@Log4j2
@Service
public class RegisterServicesImpl implements IRegisterServices<RegisterDto, RegisterEntity> {

    private final IRoleRepository iRoleRepository;
    private final IRegisterRepository iRegisterRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;
    private final IEmailRepository iEmailRepository;
    private final IForRegisterTokenEmailConfirmationServices tokenServices;
    private final IForRegisterTokenEmailConfirmationEntity iTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String serverMailAddress;

    @Override
    public RegisterDto entityToDto(RegisterEntity registerEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(registerEntity, RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return modelMapperBeanClass.modelMapperMethod().map(registerDto, RegisterEntity.class);
    }

    @Override
    @Transactional
    public String registerSpeedData(Long data) {
        if(data != null){
            RegisterDto registerDto = new RegisterDto();
            registerDto.setRegisterNickName("nickname");
            registerDto.setRegisterName("name");
            registerDto.setRegisterSurname("surname");
            registerDto.setRegisterPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode("1234"));
            registerDto.setRegisterEmail("hamitmizrak@gmail.com");
            registerDto.setIsAccountNonLocked(false);
            registerDto.setIsEnabled(true);
            registerDto.setIsAccountNonExpired(true);
            registerDto.setIsCredentialsNonExpired(true);

            RegisterEntity registerEntity = dtoToEntity(registerDto);
            iRegisterRepository.save(registerEntity);

            registerDto.setRegisterId(registerEntity.getRegisterId());
            registerDto.setSystemCreatedDate(registerEntity.getSystemCreatedDate());
            return data + " tane veri eklendi\nEklenen Veri: " + registerDto;
        }
        return null;
    }



    @Override
    @Transactional
    public String registerAllUserDelete() {
        iRegisterRepository.deleteAll();
        String information = registerServiceList().size() + " tane veri silindi";
        log.info(information);
        return information;
    }

    private RegisterDto mailSendMemberActive(RegisterDto registerDto, RegisterEntity registerEntity){
        ForRegisterTokenEmailConfirmationEntity tokenConfirmationEntity = new ForRegisterTokenEmailConfirmationEntity(registerEntity);
        String token = tokenServices.createToken(tokenConfirmationEntity);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(this.serverMailAddress);
        simpleMailMessage.setTo(registerDto.getRegisterEmail());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setSubject("Harika Üyeliğinizin aktif olmasına son bir adım kaldı");
        String mailContent = "<mark>Üyeliğinizi aktifleşmesine son bir adım lütfen linke tıklayınız.</mark>" +
                "http://localhost:4444/register/api/v1.0.0/confirm?token=" + token;
        simpleMailMessage.setText(mailContent);

        mailSender.send(simpleMailMessage);

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmailSubject(simpleMailMessage.getSubject());
        emailEntity.setEmailText(simpleMailMessage.getText());
        emailEntity.setEmailTo(registerDto.getRegisterEmail());
        emailEntity.setEmailFrom(simpleMailMessage.getFrom());
        iEmailRepository.save(emailEntity);

        return registerDto;
    }

    @Transactional
    public void emailTokenConfirmation(ForRegisterTokenEmailConfirmationEntity tokenConfirmationEntity) {
        final RegisterEntity registerEntity = tokenConfirmationEntity.getRegisterEntity();
        registerEntity.getEmbeddableUserDetails().setIsAccountNonLocked(Boolean.TRUE);
        iRegisterRepository.save(registerEntity);
        tokenServices.deleteToken(tokenConfirmationEntity.getId());
    }

    public Optional<ForRegisterTokenEmailConfirmationEntity> findTokenConfirmation(String token) {
        return iTokenRepository.findTokenConfirmationEntityByToken(token);
    }

    @Override
    @Transactional
    public RegisterDto registerServiceCreate(Long rolesId, RegisterDto registerDto) {
        if(registerDto != null && !iRoleRepository.findAll().isEmpty()){
            RegisterEntity registerEntity = dtoToEntity(registerDto);
            registerEntity.setRegisterPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(registerDto.getRegisterPassword()));

            int roleIdMatch = Math.toIntExact(rolesId);
            RoleEntity roleEntity = iRoleRepository.findAll().get(roleIdMatch - 1);
            Set<RoleEntity> roleEntitySet = new HashSet<>();
            roleEntitySet.add(roleEntity);
            registerEntity.setRoles(roleEntitySet);

            iRegisterRepository.save(registerEntity);

            registerDto.setRegisterId(registerEntity.getRegisterId());
            registerDto.setSystemCreatedDate(registerEntity.getSystemCreatedDate());

            mailSendMemberActive(registerDto, registerEntity);

            return registerDto;
        }
        return null;
    }

    @Override
    public List<RegisterDto> registerServiceList() {
        Iterable<RegisterEntity> registerEntityIterable = iRegisterRepository.findAll();
        List<RegisterDto> registerDtoList = new ArrayList<>();
        for(RegisterEntity temp: registerEntityIterable){
            registerDtoList.add(entityToDto(temp));
        }
        log.info("Register Liste sayısı: " + registerDtoList.size());
        return registerDtoList;
    }

    @Override
    public RegisterDto registerServiceFindById(Long id) {
        Boolean booleanRegisterEntityFindById = iRegisterRepository.findById(id).isPresent();
        RegisterEntity registerEntity = null;
        if (booleanRegisterEntityFindById) {
            registerEntity = iRegisterRepository.findById(id).orElseThrow(() -> new Resource404NotFoundException(id + " nolu ID Bulunamadı"));
        } else if (!booleanRegisterEntityFindById) {
            throw new HamitMizrakException("Roles Dto id boş değer geldi");
        }
        return entityToDto(registerEntity);
    }

    @Override
    @Transactional
    public RegisterDto registerServiceUpdateById(Long id, RegisterDto registerDto) {
        if(registerDto != null){
            RegisterEntity registerEntity = new RegisterEntity();
            registerEntity.setRegisterNickName(registerDto.getRegisterNickName());
            registerEntity.setRegisterName(registerDto.getRegisterName());
            registerEntity.setRegisterSurname(registerDto.getRegisterSurname());
            registerEntity.setRegisterEmail(registerDto.getRegisterEmail());
            registerEntity.setRegisterPassword(registerDto.getRegisterPassword());
            iRegisterRepository.save(registerEntity);
            return entityToDto(registerEntity);
        }
        return null;
    }

    @Override
    @Transactional
    public RegisterDto registerServiceDeleteById(Long id) {
        RegisterDto registerFindDto = registerServiceFindById(id);
        if(registerFindDto != null){
            iRegisterRepository.deleteById(id);
        }
        return registerFindDto;
    }
}