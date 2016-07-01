package com.example.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "meters")
public class Meter {

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, name = "inputby")
    private String inputBy;

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public int getId() {
        return id;
    }
}
