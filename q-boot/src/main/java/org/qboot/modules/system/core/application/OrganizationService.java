package org.qboot.modules.system.core.application;

import org.qboot.modules.system.core.application.models.OrganizationDTO;
import org.qboot.modules.system.core.domain.Organization;


public interface OrganizationService {

    OrganizationDTO addOrg(Organization params, String parentID);

    void update(Organization params);

    boolean delete(String id);

}
