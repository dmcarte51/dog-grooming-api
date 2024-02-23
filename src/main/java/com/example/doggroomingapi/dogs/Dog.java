package com.example.doggroomingapi.dogs;

import com.example.doggroomingapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
