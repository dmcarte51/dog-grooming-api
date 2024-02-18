package com.example.doggroomingapi.dogs;

import com.example.doggroomingapi.user.User;
import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "dogs")
@Entity
public class Dog {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to user table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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

    @NotNull
    @NotEmpty
    @Column(name = "age")
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }
}
