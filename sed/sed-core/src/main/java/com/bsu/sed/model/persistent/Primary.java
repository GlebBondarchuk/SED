package com.bsu.sed.model.persistent;

import com.bsu.sed.model.ContentKey;

import javax.persistence.*;

/**
 * @author gbondarchuk
 */
@Entity(name = "Primary")
@Table(name = "sed_primary")
public class Primary extends BaseEntity {
    private Long id;
    private Content content;
    private ContentKey key;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Column(name="key")
    @Enumerated(EnumType.STRING)
    public ContentKey getKey() {
        return key;
    }

    public void setKey(ContentKey contentKey) {
        this.key = contentKey;
    }
}
