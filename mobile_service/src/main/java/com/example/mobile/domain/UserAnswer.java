package com.example.mobile.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user_answer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Integer id;

    @JoinColumn(name = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private  Users id_user;

    @JoinColumn(name = "id_answer")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private  Answer id_answer;



    public UserAnswer() {
    }

    public UserAnswer(Users id_user, Answer id_answer) {
        this.id_user = id_user;
        this.id_answer = id_answer;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getId_user() {
        return id_user;
    }

    public void setId_user(Users id_user) {
        this.id_user = id_user;
    }

    public Answer getId_answer() {
        return id_answer;
    }

    public void setId_answer(Answer id_answer) {
        this.id_answer = id_answer;
    }
}
