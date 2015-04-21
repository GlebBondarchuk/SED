package com.bsu.sed.model.persistent;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gbondarchuk
 */
@Entity(name = "Notification")
@Table(name = "sed_notification")
public class Notification extends BaseEntity {
    private Long id;
    private User from;
    private User to;
    private String message;
    private Date sendDate;

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

    @ManyToOne
    @JoinColumn(name = "from_id")
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    @ManyToOne
    @JoinColumn(name = "to_id")
    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
