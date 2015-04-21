package com.bsu.sed.model.persistent;

import com.bsu.sed.model.Role;

import javax.persistence.*;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Entity(name = "Navigation")
@Table(name = "sed_navigation")
public class Navigation extends BaseEntity {
    private Long id;
    private List<Navigation> children;
    private Navigation parent;
    private String text;
    private String url;
    private boolean authorizedOnly;
    private int listNumber;
    private int order;
    private String lang;
    private boolean navbar;

    public Navigation(Navigation parent) {
        this.parent = parent;
        this.text = parent.text;
        this.url = parent.url;
        this.authorizedOnly = parent.authorizedOnly;
        this.listNumber = parent.listNumber;
        this.order = parent.order;
        this.lang = parent.lang;
        this.navbar = parent.navbar;
    }

    public Navigation() {
    }

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    public Navigation getParent() {
        return parent;
    }

    public void setParent(Navigation parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    @OrderBy("listNumber")
    public List<Navigation> getChildren() {
        return children;
    }

    public void setChildren(List<Navigation> children) {
        this.children = children;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "authorized_only")
    public boolean isAuthorizedOnly() {
        return authorizedOnly;
    }

    public void setAuthorizedOnly(boolean authorizedOnly) {
        this.authorizedOnly = authorizedOnly;
    }

    @Column(name = "list_number")
    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    @Column(name = "list_order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Column(name = "navbar")
    public boolean isNavbar() {
        return navbar;
    }

    public void setNavbar(boolean navbar) {
        this.navbar = navbar;
    }

    @Column(name = "lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
