package br.com.auth.jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;

    private double price;

    private String description;
}
