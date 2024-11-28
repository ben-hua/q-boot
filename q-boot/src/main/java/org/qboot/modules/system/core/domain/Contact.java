package org.qboot.modules.system.core.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Contact {
    private String contactFirstName;
    private String contactLastName;
    private String contactTitle;

    private String contactEmail;
    private String contactPhone;
    private String contactFax;

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }
}
