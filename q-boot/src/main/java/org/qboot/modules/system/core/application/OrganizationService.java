package com.qadmin.demo.sys.application;

import java.util.UUID;

import com.qadmin.demo.sys.application.dto.OrganizationDTO;
import com.qadmin.demo.sys.domain.model.Organization;

public interface OrganizationService {

    OrganizationDTO addOrg(Organization params, UUID parentID);

    void update(Organization params);

    boolean delete(UUID id);

}
