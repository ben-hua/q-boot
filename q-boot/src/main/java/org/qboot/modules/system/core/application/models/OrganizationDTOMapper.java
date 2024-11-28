package org.qboot.modules.system.core.application.dto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.qboot.modules.system.core.domain.Organization;

@Mapper
public interface OrganizationDTOMapper {

    OrganizationDTOMapper INSTANCE = Mappers.getMapper(OrganizationDTOMapper.class);

    @Mapping(target = "parent", ignore = true)
    Organization fromDTO(OrganizationDTO cmd);

    @Mapping(target = "parentId", source = "parent.id")
    OrganizationDTO toDto(Organization org);

}
