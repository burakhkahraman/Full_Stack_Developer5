package com.techcareer.business.services.impl;

import com.techcareer.business.dto.RegisterDto;
import com.techcareer.business.services.IRegisterServices;
import com.techcareer.data.entity.RegisterEntity;

import java.util.List;

public class RegisterImpl implements IRegisterServices<RegisterDto, RegisterEntity> {
    @Override
    public RegisterDto entityToDto(RegisterEntity registerEntity) {
        return null;
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return null;
    }

    @Override
    public String registerSpeedData(Long data) {
        return null;
    }

    @Override
    public String registerAllUSerDelete() {
        return null;
    }

    @Override
    public RegisterDto registerServiceCreate(Long rolesId, RegisterDto registerDto) {
        return null;
    }

    @Override
    public List<RegisterDto> registerServiceList() {
        return null;
    }

    @Override
    public RegisterDto registerServiceFindById(Long id) {
        return null;
    }

    @Override
    public RegisterDto registerServiceUpdateById(Long id, RegisterDto registerDto) {
        return null;
    }

    @Override
    public RegisterDto registerServiceDeleteById(Long id) {
        return null;
    }
}