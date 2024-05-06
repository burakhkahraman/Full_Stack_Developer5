package com.techcareer.business.services.impl;

import com.techcareer.business.dto.RoleDto;
import com.techcareer.business.services.IRoleService;
import com.techcareer.data.entity.RoleEntity;

import java.util.List;

public class RoleServicesImpl implements IRoleService<RoleDto, RoleEntity> {


    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return null;
    }

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
}