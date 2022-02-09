package com.myform.test.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "agreement")
    private Boolean agreement;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "form_sectors",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private Set<Sector> sectors = new HashSet<>();

    public Form(String username, Boolean agreement) {
        this.username = username;
        this.agreement = agreement;
    }

}
