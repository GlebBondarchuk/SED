package com.bsu.sed.model;

public enum SystemAttributeCategory {
    EMAIL("label.system.email"),
    PEOPLES("label.system.people"),
    STUDENTS("label.system.student"),
    NEWS("label.system.news"),
    GALLERY("label.system.news"),
    SEARCH("label.system.news"),
    IMAGE("label.system.news"),
    CONTACT("label.system.news");

    private String displayMessageKey;

    SystemAttributeCategory(String displayMessageKey) {
        this.displayMessageKey = displayMessageKey;
    }

    public String getDisplayMessageKey() {
        return displayMessageKey;
    }

    public boolean in(SystemAttributeCategory... categories) {
        for (SystemAttributeCategory category : categories) {
            if (category.name().equals(this.name())) {
                return true;
            }
        }
        return false;
    }
}
