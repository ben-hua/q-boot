package org.qboot.modules.system.core.application.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.qboot.modules.system.core.application.OrganizationService;
import org.qboot.modules.system.core.application.models.OrganizationDTO;
import org.qboot.modules.system.core.application.models.OrganizationDTOMapper;
import org.qboot.modules.system.core.domain.Organization;
import org.qboot.modules.system.core.domain.OrganizationFactory;
import org.qboot.modules.system.core.domain.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Inject
    OrganizationRepository orgRepository;

    @Inject
    OrganizationFactory organizationFactory;

    @Override
    public OrganizationDTO addOrg(Organization params, String parentID) {
        Organization org = organizationFactory.of(params, parentID);
        orgRepository.persist(org);
        LOG.info("new Organization with id {}", org.getId().toString());

        OrganizationDTO dto = OrganizationDTOMapper.INSTANCE.toDto(org);

        return dto;
    }

    @Override
    public void update(Organization params) {
        orgRepository.findByIdOptional(params.getId()).ifPresentOrElse(t -> {
            t.setName(params.getName());
            t.setType(params.getType());
            t.setCode(params.getCode());
            t.setStatus(params.getStatus());
            t.setNameEn(params.getNameEn());
            t.setNameAbbr(params.getNameAbbr());
            t.setDescription(params.getDescription());
            t.setOrderNum(params.getOrderNum());
            t.setWebsite(params.getWebsite());
            t.setLogoFileId(params.getLogoFileId());
            t.setAreasOfInterest(params.getAreasOfInterest());
            t.setLevel(params.getLevel());
            t.setContact(params.getContact());
            t.setAddress(params.getAddress());
            t.setEnterpriseWebchatID(params.getEnterpriseWebchatID());
            orgRepository.persist(t);
        }, () -> {
            throw new RuntimeException("organization not found");
        });
    }

    @Override
    public boolean delete(String id) {
        return orgRepository.deleteById(id);
    }

}
