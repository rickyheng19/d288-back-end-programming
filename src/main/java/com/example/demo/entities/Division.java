package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="divisions")
@Data
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long id;

    @Column(name = "division")
    private String division_name;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", nullable = false, insertable = false, updatable = false)
    private Country country;

    @Column(name ="country_id")
    private Long country_id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divisions")
    private Set<Customer> customers;

    public void setCountry(Country country) {
        setCountry_id(country.getId());
        this.country = country;
    }
}
