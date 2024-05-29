package com.techcareer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techcareer.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter

// abstract Class
@MappedSuperclass
@JsonIgnoreProperties(value = {}, allowGetters = true) // Json, burada verdiğim özellikleri takip etme
public abstract class BaseEntity extends AuditingAwareBaseEntity implements Serializable {

    // SERILESTIRME
    private static final long serialVersionUID = 1L;

    // Role ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    // System Created Date
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "system_created_date")
    protected Date systemCreatedDate;

    // Parametresiz kurucu
    public BaseEntity() {
        this.systemCreatedDate = new Date();
    }
} //end BaseEntity