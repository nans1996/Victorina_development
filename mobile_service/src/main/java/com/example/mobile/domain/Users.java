package com.example.mobile.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Заполните логин")
  //  @Column(name = "login",unique = true)
    private String login;

    @NotNull(message = "Заполните пароль")
    @Size(min=6, max=20, message = "Пароль должен содержать от 6 до 20 символов")
    private String password;

    @NotNull(message = "Заполните эл. почту")
    @Pattern(regexp="^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", message="Недопустимый адрес электронной почты")
    private String email;

    @NotNull(message = "Заполните имя")
    @Size(min=1, message = "Введите имя")
    private String first_name;

    @NotNull(message = "Заполните фамилию")
    @Size(min = 1, message = "Введите фамилию")
    private String last_name;

    @ManyToMany
    @JoinTable(name = "user_question",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_question", referencedColumnName = "idQuestion"))
    private  Collection<Question> questionCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_user")
    private Collection<Statistic> staticCollection;

    public Users() {
    }


    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    public Collection<Statistic> getStaticCollection() {
        return staticCollection;
    }

    public void setStaticCollection(Collection<Statistic> staticCollection) {
        this.staticCollection = staticCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users(String login, String password, String email, String first_name, String last_name) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
