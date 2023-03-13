package com.admi.MiniPorject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FournitureOrder{
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FournitureOrderId;
    @Column
    Boolean isAccepted = null;
//    @JsonIgnore
    @JoinColumn(name = "fourniture_id")
    @ManyToOne
    private Fourniture fourniture;

    @Column
    private Integer number;
//    @JsonIgnore
    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person person;
    @Column(name = "date_order")
    private LocalDate dateOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FournitureOrder that = (FournitureOrder) o;
        return FournitureOrderId != null && Objects.equals(FournitureOrderId, that.FournitureOrderId);
    }



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}


//    public int getColumnCount() {
//        return getClass().getDeclaredFields().length;
//    }
