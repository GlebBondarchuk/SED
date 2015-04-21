package com.bsu.sed.model.persistent;

import javax.persistence.*;

/**
 * @author gbondarchuk
 */
@Entity(name = "Gallery")
@Table(name = "sed_gallery")
public class Gallery extends BaseEntity {
    private Long id;
    private String description;
    private Document document;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
