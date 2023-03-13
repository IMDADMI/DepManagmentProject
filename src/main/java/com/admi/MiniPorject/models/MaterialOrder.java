package com.admi.MiniPorject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MaterialOrder {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long materielOrderId;
    @Column
    Boolean isAccepted = null;
    @JoinColumn(name = "material_id")
    @ManyToOne
    private Material material;
    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person person;
    @Column
    private LocalDate dateOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o   ) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaterialOrder that = (MaterialOrder) o;
        return materielOrderId != null && Objects.equals(materielOrderId, that.materielOrderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

