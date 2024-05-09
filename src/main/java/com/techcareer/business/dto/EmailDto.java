package com.techcareer.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder

public class EmailDto  extends BaseDto implements Serializable {

    // SERİLEŞTİRME
    public static final Long serialVersionUID = 1L;

    // EMAIL TO ( KİME ) ZORUNLU
    @NotEmpty(message = "{email.to.validation.constraints.NotNull.message}")
    private String emailTo; //KİME
    //private String[] emailToArray; //KİMLERE

    // EMAIL FROM (KİMDEN GELİYOR) ZORUNLU
    @Value(staticConstructor = "${spring.mail.username}")
    @NotEmpty(message = "{email.from.validation.constraints.NotNull.message}")
    //@Builder.Default
    private String emailFrom; //KİMDEN GELİYOR



    // EMAIL SUBJECT (KONU) ZORUNLU
    @NotEmpty(message = "{email.subject.validation.constraints.NotNull.message}")
    private String emailSubject; //KONU

    // EMAIL TEXT (İÇERİK)  ZORUNLU
    @NotEmpty(message = "{email.text.validation.constraints.NotNull.message}")
    private String emailText;

    /*
    CC yani Copy Carbon, göndereceğiniz e-postanın bütün alıcıların birbirini görmesini ve iletişime geçebilmesini sağlar.
    Fakat BCC bölümüne eklediğiniz alıcıyı sizin haricinizde kimse göremez ve iletişime geçemez.
    */
    // EMAIL CC
    private String emailCc;// CC
    private String[] emailCcArray;

    // EMAIL BCC
    private String emailBcc; //BCC
    private String[] emailBccArray;

    // IMAGE
    @Builder.Default
    private String image="image.png";

    // URL
    @Builder.Default
    private String URL="http://localhost:4444/";

    // DATE(SEND)
    @Builder.Default
    private Date sentDate=new Date(System.currentTimeMillis()); //NE ZAMAN

} //end EmailDto