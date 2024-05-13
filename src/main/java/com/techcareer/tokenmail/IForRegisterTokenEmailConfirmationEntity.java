package com.techcareer.tokenmail;

// CrudRepository<RegisterEntity,Long>
// JpaRepository<RegisterEntity,Long>
// PagingAndSortingRepository<RegisterEntity,Long>

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IForRegisterTokenEmailConfirmationEntity extends CrudRepository<ForRegisterTokenEmailConfirmationEntity,Long> {

    // Delivered Query
    Optional<ForRegisterTokenEmailConfirmationEntity> findTokenConfirmationEntityByToken(String token);
}
