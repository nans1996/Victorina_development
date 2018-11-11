package com.example.mobile.domain;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_statistic;
    private Date date;
    private int count_truth;

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users id_user;

    public Statistic() {
    }

    public Statistic(Date date, int count_truth, Users id_user) {
        this.date = date;
        this.count_truth = count_truth;
        this.id_user = id_user;
    }



    public Integer getId_statistic() {
        return id_statistic;
    }

    public void setId_statistic(Integer id_statistic) {
        this.id_statistic = id_statistic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount_truth() {
        return count_truth;
    }

    public void setCount_truth(int count_truth) {
        this.count_truth = count_truth;
    }

    public Users getId_user() {
        return id_user;
    }

    public void setId_user(Users id_user) {
        this.id_user = id_user;
    }
}
