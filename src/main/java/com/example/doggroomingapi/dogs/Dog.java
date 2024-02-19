package com.example.doggroomingapi.dogs;

import com.example.doggroomingapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "dogs")
@Entity
public class Dog {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to user table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    @JsonProperty("userId")
    private Long userId;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "breed")
    private String breed;

    @NotNull
    @NotEmpty
    @Column(name = "weight")
    private int weight;

    @NotNull
    @NotEmpty
    @Column(name = "is_bite_risk")
    private boolean isBiteRisk;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birthday")
    private Date birthday;
    public Dog() {
    }

    public Dog(Long id, User user, Long userId, String name, String breed, int weight, boolean isBiteRisk, Date birthday) {
        this.id = id;
        this.user = user;
        this.userId = userId;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.isBiteRisk = isBiteRisk;
        this.birthday = birthday;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean getIsBiteRisk() {
        return isBiteRisk;
    }

    public void setBiteRisk(boolean biteRisk) {
        isBiteRisk = biteRisk;
    }

    public Date getAge() {
        return birthday;
    }

    public void setAge(Date birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
