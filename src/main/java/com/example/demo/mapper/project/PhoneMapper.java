package com.example.demo.mapper.project;

import com.example.demo.dto.PhoneDTO;
import com.example.demo.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneMapper {

    @Mapping(source = "dd", target = "dd")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "chip", target = "chip")
    PhoneDTO toDTO(Phone phone);

}
