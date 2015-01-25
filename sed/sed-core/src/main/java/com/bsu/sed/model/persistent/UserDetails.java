package com.bsu.sed.model.persistent;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static com.bsu.sed.model.constraint.ConstraintConstants.*;

/**
 * @author gbondarchuk
 */
@Entity(name = "UserDetails")
@Table(name = "sed_user_details")
public class UserDetails extends BaseEntity {
    private Long id;
    private String phone;
    private String address;

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

    @Length(max = USER_DETAILS_PHONE_MAX_LENGTH)
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(max = USER_DETAILS_ADDRESS_MAX_LENGTH)
    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
