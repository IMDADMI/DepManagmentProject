package com.admi.MiniPorject.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Material {
    @Id
    @Column(name = "numero_inventaire")
    private Long numeroInventaire;
    @Column
    private String type;
    @Column
    private LocalDate dateAcquisition;

    @OneToMany(mappedBy= "material",cascade = CascadeType.ALL)
    private List<MaterialOrder> orders;
    @Column
    private LocalDate dateAffectation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Material material = (Material) o;
        return numeroInventaire != null && Objects.equals(numeroInventaire, material.numeroInventaire);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
