package com.se2.hanuairline.model.user;

import com.se2.hanuairline.model.Ticket;
import com.se2.hanuairline.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile extends DateAudit implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Size(max = 40)
    private String id_card_number;

    @NotBlank
    @Size(max = 15)
    private String credit_card_number;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    private String phoneNumber;

    public Profile(Long id, User user, @NotBlank @Size(max = 40) String id_card_number, @NotBlank @Size(max = 15) String credit_card_number, @NotBlank @Size(max = 40) String phoneNumber) {
        this.id = id;
        this.user = user;
        this.id_card_number = id_card_number;
        this.credit_card_number = credit_card_number;
        this.phoneNumber = phoneNumber;
    }

    public Profile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public void setCredit_card_number(String credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

