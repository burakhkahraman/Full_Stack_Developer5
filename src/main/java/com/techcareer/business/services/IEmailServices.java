package com.techcareer.business.services;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IBlogGenericsServices extends IProfileHeaderApp

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IBlogGenericsServices

// Generics
// D => Dto
// E => Entity

import com.techcareer.business.dto.EmailDto;

// Email
public interface IEmailServices<D, E> {

    // MODEL MAPPER
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    ////////////////////////////////////////////
    // Maili Öncelikle Database kaydedelim.
    public D mailDatabase(D d);


    ///////////////////////////////////////////////////////////////////////////////////////
    //**** EMAIL DATABASE *****************************************************************//
    // Email Database
    EmailDto mailDatabase(EmailDto emailDto);

    ////////////////////////////////////////////
    // EMAIL BASIC SEND (text)
    public D basicSendEmail(D d);

    // EMAIL INTERMEDIA ATTACHMENT SEND (image,word,text,files)
    public D intermediaSendEmail(D d);

}// end IEmailServices