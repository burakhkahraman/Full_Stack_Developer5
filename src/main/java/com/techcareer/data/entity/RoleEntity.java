package com.techcareer.data.entity;

import com.techcareer.role.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//@SneakyThrows
// RoleDto

// ENTITY
@Entity(name = "Roles")
@Table(name="roles")
// Role(M) Register(N)
public class RoleEntity  implements Serializable {

    // SERILESTIRME
    public static final Long serialVersionUID=1L;

    // Role ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    // Role Name
    // Validation = columnDefinition =>  Default USER olsun
    @Column(name = "role_name", length = 20,nullable = true, columnDefinition = "varchar(255) default USER")
    private String roleName;

    // System Created Date
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;
} //end class RoleEntity