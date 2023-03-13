package com.admi.MiniPorject.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Fourniture {

    @Id
    @Column(name = "fourniture_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fournitureId;
    @Column
    private String type;
    @Column
    private Integer nombre;
    @OneToMany(mappedBy="fourniture",cascade = CascadeType.ALL)
    private List<FournitureOrder> orders;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fourniture that = (Fourniture) o;
        return fournitureId != null && Objects.equals(fournitureId, that.fournitureId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
