package com.bsu.sed.model;

import javax.persistence.*;

/**
 * System Application Attributes.
 *
 * @author gbondarchuk
 */
@Entity(name="System")
@Table(name = "sed_system")
public class SystemAttribute extends BaseEntity {

    private Long id;
    private SystemAttributeKey key;
    private String value;
    private String displayName;
    private String description;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "key")
    @Enumerated(EnumType.STRING)
    public SystemAttributeKey getKey() {
        return key;
    }

    public void setKey(SystemAttributeKey key) {
        this.key = key;
    }

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
