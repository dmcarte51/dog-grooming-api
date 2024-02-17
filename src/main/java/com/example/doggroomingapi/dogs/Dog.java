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
//    @JoinColumn(name = "username")
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
    @Column(name = "breed")
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
