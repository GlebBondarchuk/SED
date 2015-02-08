package com.bsu.sed.model.persistent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Entity(name = "People")
@Table(name = "sed_people")
public class People extends BaseEntity {
    private Long id;
    private User user;
    private String position;
    private String address;
    private List<Content> contents = new ArrayList<>();

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sed_people_content",
            joinColumns = {@JoinColumn(name = "people_id")},
            inverseJoinColumns = {@JoinColumn(name = "content_id")})
    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
