package com.bsu.sed.model.persistent;

import com.bsu.sed.model.TextKey;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gbondarchuk
 */
@Entity(name = "Text")
@Table(name = "sed_text")
public class Text extends BaseEntity {
    private Long id;
    private String text;
    private Date updateDate;
    private TextKey key;

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


    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "text_key")
    @Enumerated(EnumType.STRING)
    public TextKey getKey() {
        return key;
    }

    public void setKey(TextKey key) {
        this.key = key;
    }
}
