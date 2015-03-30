package com.bsu.sed.model.persistent;

import com.bsu.sed.model.BackgroundProcessKey;

import javax.persistence.*;

/**
 * @author gbondarchuk
 */

@Entity(name = "BackgroundProcess")
@Table(name = "sed_process")
public class BackgroundProcess extends BaseEntity {
    private static final long serialVersionUID = -7640810376189420096L;

    private Long id;
    private BackgroundProcessKey process;
    private String displayName;
    private String description;
    private boolean disabled;
    private String cron;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "cron")
    public String getCron() {
        return cron;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "disabled")
    public boolean isDisabled() {
        return disabled;
    }

    @Column(name = "process")
    @Enumerated(EnumType.STRING)
    public BackgroundProcessKey getProcess() {
        return process;
    }

    @Column(name = "displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setProcess(BackgroundProcessKey process) {
        this.process = process;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
