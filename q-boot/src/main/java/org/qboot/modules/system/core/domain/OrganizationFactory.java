package com.qadmin.demo.sys.domain.model;

import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrganizationFactory {

    @Inject
    private OrganizationRepository organizationRepository;

    public Organization of(Organization org, UUID parentID) {
        final Organization parent = findParent(parentID);
        org.setParent(parent);
        org.setLevel(parent != null ? parent.getLevel() + 1 : 0);
        if (org.getStatus() == null) {
            org.setStatus(Organization.Status.Alive);
        }
        return org;
    }

    private Organization findParent(UUID id) {
        if (id == null) {
            return null;
        }

        final Optional<Organization> op = organizationRepository.findByIdOptional(id);

        if (op.isEmpty())
            throw new IllegalArgumentException("No organization with id " + id.toString() + " exists in the system");

        return op.get();
    }

}
