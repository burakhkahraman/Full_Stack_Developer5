package com.techcareer.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@EqualsAndHashCode(callSuper = true)
@Data
@Log4j2
@Builder

// EMAIL
@Entity(name = "Emails") // Sql JOIN için yazdım
@Table(name = "email")
public class EmailEntity extends BaseEntity implements Serializable {

    // SERILESTIRME
    public static final Long serialVersionUID = 1L;

    // EMAIL TO ( KİME ) ZORUNLU
    @Column(name = "email_to")
    private String emailTo; //KİME

    // EMAIL FROM (KİMDEN GELİYOR) ZORUNLU
    @Column(name = "email_from")
    //@Value("${spring.mail.username}")
    private String emailFrom; //KİMDEN GELİYOR

    // EMAIL SUBJECT (KONU) ZORUNLU
    @Column(name = "email_subject")
    private String emailSubject; //KONU

    // EMAIL TEXT (İÇERİK)  ZORUNLU
    @Column(name = "email_text")
    private String emailText;

     /*
    CC yani Copy Carbon, göndereceğiniz e-postanın bütün alıcıların birbirini görmesini ve iletişime geçebilmesini sağlar.
    Fakat BCC bölümüne eklediğiniz alıcıyı sizin haricinizde kimse göremez ve iletişime geçemez.
    */
    // EMAIL CC
    /*
    @Column(name="email_cc")
    private String emailCc;// CC

    @Column(name="email_bcc")
    private String emailBcc; //BCC
    */

    // EMAIL IMAGE
    @Column(name = "email_image")
    private String image = "image.png";

    // EMAIL URL
    @Column(name = "email_url") //,columnDefinition = " default 'picture.jpg"
    @Lob // Lob: Large Object
    private String URL = "http://localhost:4444/";

    // EMAIL DATE
    @Column(name = "email_date")
    private Date sentDate; //NE ZAMAN

} //end class