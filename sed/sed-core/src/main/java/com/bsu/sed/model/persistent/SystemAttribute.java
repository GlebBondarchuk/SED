package com.bsu.sed.model.persistent;

import com.bsu.sed.model.SystemAttributeCategory;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.constraint.ConstraintConstants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * System Application Attributes.
 *
 * @author gbondarchuk
 */
@Entity(name = "SystemAttribute")
@Table(name = "sed_system")
public class SystemAttribute extends BaseEntity {

    private Long id;
    private SystemAttributeKey key;
    private String value;
    private String displayName;
    private String description;
    private SystemAttributeCategory category;

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

    @Column(name = "key", updatable = false)
    @Enumerated(EnumType.STRING)
    public SystemAttributeKey getKey() {
        return key;
    }

    private void setKey(SystemAttributeKey key) {
        this.key = key;
    }

    @NotEmpty
    @Length(max = ConstraintConstants.SYSTEM_VALUE_MAX_LENGTH)
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NotEmpty
    @Column(name = "display_value", updatable = false)
    public String getDisplayName() {
        return displayName;
    }

    private void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @NotEmpty
    @Length(max = ConstraintConstants.SYSTEM_DESCRIPTION_MAX_LENGTH)
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "category", updatable = false)
    @Enumerated(EnumType.STRING)
    public SystemAttributeCategory getCategory() {
        return category;
    }

    private void setCategory(SystemAttributeCategory category) {
        this.category = category;
    }
}
