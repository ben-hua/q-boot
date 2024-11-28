package com.qadmin.demo.sys.application.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qadmin.demo.sys.application.OrganizationService;
import com.qadmin.demo.sys.application.dto.OrganizationDTO;
import com.qadmin.demo.sys.application.mapper.OrganizationDTOMapper;
import com.qadmin.demo.sys.domain.model.Organization;
import com.qadmin.demo.sys.domain.model.OrganizationFactory;
import com.qadmin.demo.sys.domain.model.OrganizationRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Inject
    OrganizationRepository orgRepository;

    @Inject
    OrganizationFactory organizationFactory;

    @Override
    public OrganizationDTO addOrg(Organization params, UUID parentID) {
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
    public boolean delete(UUID id) {
        return orgRepository.deleteById(id);
    }

}
