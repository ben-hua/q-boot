package com.qadmin.demo.sys.domain.model;

import java.util.List;
import java.util.UUID;

import com.qadmin.demo.sys.domain.model.Organization.Status;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepositoryBase<Organization, UUID> {

    public void deleteByIds(List<UUID> ids) {
        delete("id in (?1)", ids);
    }

    public List<Organization> findByStatus() {
        // create a query for all living Organizations
        PanacheQuery<Organization> livingOrganizations = find("status", Organization.Status.Alive);

        find("status", Sort.by("name").and("code", Direction.Descending), Organization.Status.Alive);

        // make it use pages of 25 entries at a time
        livingOrganizations.page(Page.ofSize(25));

        // get the first page
        List<Organization> firstPage = livingOrganizations.list();

        // get the second page
        List<Organization> secondPage = livingOrganizations.nextPage().list();

        PanacheQuery<Organization> lastPage = livingOrganizations.lastPage();

        // get page 7
        List<Organization> page7 = livingOrganizations.page(Page.of(7, 25)).list();

        // get the number of pages
        int numberOfPages = livingOrganizations.pageCount();

        // get the total number of entities returned by this query without paging
        long count = livingOrganizations.count();

        // and you can chain methods of course
        return find("status", Status.Alive)
                .page(Page.ofSize(25))
                .nextPage()
                .stream().toList();
    }
}
