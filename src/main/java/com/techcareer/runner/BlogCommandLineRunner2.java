package com.techcareer.runner;

import com.techcareer.business.dto.RegisterDto;
import com.techcareer.business.services.IRegisterServices;
import com.techcareer.business.services.IRoleService;
import com.techcareer.data.entity.RoleEntity;
import com.techcareer.data.repository.IRegisterRepository;
import com.techcareer.data.repository.IRoleRepository;
import com.techcareer.role.ERole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

@Component // Spring Boot bir parçasısın.
public class BlogCommandLineRunner2 implements CommandLineRunner {
    // Injection
    private final IRoleRepository iRoleRepository;
    @Qualifier("roleServicesImpl")
    private final IRoleService iRoleService;

    private final IRegisterRepository iRegisterRepository;
    @Qualifier("registerServicesImpl")
    private final IRegisterServices iRegisterServices;

    // Role ve Register Ekleme
    private void roleAndRegisterCreate(){
        //log.info("role And Register Create");
        System.out.println("role And Register Create");
        synchronized (this){
            Long adminRoleID= iRoleRepository.save(RoleEntity.builder().roleId(0L).roleName(ERole.ADMIN.toString()).build()).getRoleId();
            Long writerRoleID= iRoleRepository.save(RoleEntity.builder().roleId(0L).roleName(ERole.WRITER.toString()).build()).getRoleId();
            Long userRoleID= iRoleRepository.save(RoleEntity.builder().roleId(0L).roleName(ERole.USER.toString()).build()).getRoleId();

            // Thread
            Thread thread= new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });

            for (long i = 1; i <=3 ; i++) {
                // REGISTER
                RegisterDto registerDto=new RegisterDto();
                registerDto.setRegisterNickName("nickname"+i);
                registerDto.setRegisterName("name"+i);
                registerDto.setRegisterSurname("surname"+i);
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("email").append("@gmail.com");
                registerDto.setRegisterEmail(stringBuilder.toString());
                registerDto.setRegisterPassword("Java12345@.");

                // USER DETAILS
                registerDto.setIsEnabled(true);
                registerDto.setIsCredentialsNonExpired(true);
                registerDto.setIsAccountNonExpired(true);
                registerDto.setIsAccountNonLocked(true);

                // KAYDET
                iRegisterServices.registerServiceCreate(i,registerDto);
                System.out.println("EKLENDI");
            }

        }
    }

    @Override
    public void run(String... args) throws Exception {
        //log.info("Command Line Runner Bean-2");
        System.out.println("Command Line Runner Bean-2");
        roleAndRegisterCreate();
    }

} //end BlogCommandLineRunner1