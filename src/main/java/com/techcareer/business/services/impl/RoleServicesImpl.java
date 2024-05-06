package com.techcareer.business.services.impl;

import com.techcareer.business.dto.RoleDto;
import com.techcareer.business.services.IRoleService;
import com.techcareer.data.entity.RoleEntity;
import com.techcareer.data.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

// Lombok
@RequiredArgsConstructor
@Log4j2

// Service: Asıl İş Yükünü sırtlayan
@Service
@Component("roleServicesImpl") // @Component => Spring'in bir parcasısın
public class RoleServicesImpl implements IRoleService<RoleDto, RoleEntity> {

    // Injection IRoleRepository (1.YOL => @Autowired)
    /*
    @Autowired
    private IRoleRepository iRoleRepository;
    */


    // Injection IRoleRepository (2.YOL => Constructor Injection)
    /*
    private final IRoleRepository iRoleRepository;
    @Autowired
    public RoleServicesImpl(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }
    */

    // 3. YOL (Lombok => Constructor Injection)
    private final IRoleRepository iRoleRepository;
    private final ModelMapper modelMapper;

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** Model Mapper *****************************************************************//
    // Model Mapper
    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity,RoleDto.class);
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, RoleEntity.class);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //**** CRUD*****************************************************************//
    //
    @Override
    public RoleDto roleServiceCreate(RoleDto roleDto) {
        return null;
    }

    @Override
    public List<RoleDto> roleServiceList(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto roleServiceFindById(Long id) {
        return null;
    }

    @Override
    public RoleDto roleServiceUpdateById(Long id, RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto roleServiceDeleteById(Long id) {
        return null;
    }

} //end RoleServicesImpl