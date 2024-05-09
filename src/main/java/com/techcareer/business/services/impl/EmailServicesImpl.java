package com.techcareer.business.services.impl;

import com.techcareer.bean.ModelMapperBeanClass;
import com.techcareer.bean.PasswordEncoderBeanClass;
import com.techcareer.business.dto.EmailDto;
import com.techcareer.business.services.IEmailServices;
import com.techcareer.data.entity.EmailEntity;
import com.techcareer.data.repository.IEmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Service: Asıl İş Yükünü sırtlayan
@Service
@Component("emailServicesImpl") // @Component => Spring'in bir parcasısın
public class EmailServicesImpl implements IEmailServices<EmailDto, EmailEntity> {

    // Injection
    private final ModelMapperBeanClass modelMapperBeanClass;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

    // Injection Email
    private final IEmailRepository iEmailRepository;
    private final JavaMailSender javaMailSender;

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** Model Mapper *****************************************************************//
    // Model Mapper
    @Override
    public EmailDto entityToDto(EmailEntity emailEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(emailEntity, EmailDto.class);
    }

    @Override
    public EmailEntity dtoToEntity(EmailDto emailDto) {
        return modelMapperBeanClass.modelMapperMethod().map(emailDto, EmailEntity.class);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** EMAIL DATABASE *****************************************************************//
    // Email Database
    @Override
    public EmailDto mailDatabase(EmailDto emailDto) {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** EMAIL SEND BASIC*****************************************************************//
    // Email Basic Send
    @Override
    public EmailDto basicSendEmail(EmailDto emailDto) {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** EMAIL SEND INTERMEDIA *****************************************************************//
    // Email Intermedia Send
    @Override
    public EmailDto intermediaSendEmail(EmailDto emailDto) {
        return null;
    }

}  //end EmailServicesImpl
