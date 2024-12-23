package org.qboot.modules.system.core.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class OrganizationFactory {

    @Inject
    private OrganizationRepository organizationRepository;

    public Organization of(Organization org, String parentID) {
        final Organization parent = findParent(parentID);
        org.setParent(parent);
        org.setLevel(parent != null ? parent.getLevel() + 1 : 0);
        if (org.getStatus() == null) {
            org.setStatus(Organization.Status.Alive);
        }
        return org;
    }

    private Organization findParent(String id) {
        if (id == null) {
            return null;
        }

        final Optional<Organization> op = organizationRepository.findByIdOptional(id);

        if (op.isEmpty())
            throw new IllegalArgumentException("No organization with id " + id.toString() + " exists in the system");

        return op.get();
    }

}
