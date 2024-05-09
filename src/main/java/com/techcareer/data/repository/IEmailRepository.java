package com.techcareer.data.repository;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

import com.techcareer.data.entity.EmailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmailRepository extends CrudRepository<EmailEntity,Long> {

    // Delivered Query (database query
    Optional<EmailEntity> findByEmailTo(String emailTo);

} //end interface
