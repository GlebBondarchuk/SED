package com.bsu.sed.model.persistent;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Base hibernate entity.
 * Uses for extending all hibernate entities.
 *
 * @author gbondarchuk
 */
public abstract class BaseEntity implements Serializable {
    public abstract Long getId();

    public boolean equals(Object object) {
        if (!(object instanceof BaseEntity)) {
            return false;
        }

        BaseEntity rhs = (BaseEntity) object;
        if (this.getId() == null && rhs.getId() == null) {
            return (this == rhs);
        }
        return new EqualsBuilder().append(this.getId(), rhs.getId()).isEquals();
    }

    public int hashCode() {
        return this.getId() == null ? Long.valueOf(0).hashCode() : this.getId().hashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", this.getId()).toString();
    }
}
