package com.admi.MiniPorject.models;
import com.admi.MiniPorject.models.enums.Grade;
import com.admi.MiniPorject.models.enums.Type;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;
    @Column
    private Integer numeroBureau;
    @Column
    private String nom;

    @Column
    //cette annotation pour stocker les object de type enum
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<MaterialOrder> MaterialOrders;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<FournitureOrder> FournitureOrders;

    @Column
    private String prenom;
    @Column
    private String cin;
    @Column
    private String email;
    @Column
    private String telephone;
    @Column
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return personId != null && Objects.equals(personId, person.personId);
    }



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
/*



 */