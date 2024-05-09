package com.techcareer.business.dto;

import com.techcareer.audit.AuditingAwareBaseDto;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// BaseDTO
abstract public class BaseDto extends AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    // ID
    protected Long id;

    @Builder.Default
    protected Date systemCreatedDate=new Date(System.currentTimeMillis());

}
