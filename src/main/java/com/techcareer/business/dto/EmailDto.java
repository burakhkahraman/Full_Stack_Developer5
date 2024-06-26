package com.techcareer.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Log4j2
//@Builder
// @SneakyThrows
// RoleDto(M) RegisterDto(N)

public class EmailDto extends BaseDto implements Serializable {

    // SERILESTIRME
    public static final Long serialVersionUID=1L;

    // Kimden (Gönderen)
    @NotEmpty(message = "{email.from.validation.constraints.NotNull.message}")
    private String emailFrom;

    private final Environment env;

    public EmailDto(Environment env) {
        super(); // BaseDto sınıfının no-args yapıcı metodunu çağır
        this.env = env;
        this.emailFrom = env.getProperty("spring.mail.username");
    }

    // Kime
    @NotEmpty(message = "{email.to.validation.constraints.NotNull.message}")
    private String emailTo;



    // Konu
    @NotEmpty(message = "{email.subject.validation.constraints.NotNull.message}")
    private String emailSubject;

    // İçerik
    @NotEmpty(message = "{email.text.validation.constraints.NotNull.message}")
    private String emailText;

    // CC: public
    private String emailCc;
    //private String[] emailCcArray;

    // BCC: private
    private String emailBcc;
    //private String[] emailBCcArray;

    // Resim
   // @Builder.Default
    private String image="image.png";

    // URL
    //@Builder.Default
    private String URL="http://localhost:4444/";

} //end Email