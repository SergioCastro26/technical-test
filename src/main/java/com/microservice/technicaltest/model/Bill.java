package com.microservice.technicaltest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;

    public Double totalAmount;

    public String desc;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idUser")
    public User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
