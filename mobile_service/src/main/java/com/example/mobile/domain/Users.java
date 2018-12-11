package com.example.mobile.domain;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
public class Users  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Заполните логин")
    @Column(unique = true)
    private String login;

    @NotNull(message = "Заполните пароль")
    @Size(min=6, max=20, message = "Пароль должен содержать от 6 до 20 символов")
    private String password;

    @NotNull(message = "Заполните эл. почту")
    @Pattern(regexp="^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", message="Недопустимый адрес электронной почты")
    private String email;

    @NotNull(message = "Заполните имя")
    @Size(min=1, max = 50, message = "Введите имя")
    private String first_name;

    @NotNull(message = "Заполните фамилию")
    @Size(min = 1, max = 50, message = "Введите фамилию")
    private String last_name;

    @ManyToMany
    @JoinTable(name = "user_answer",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_answer", referencedColumnName = "id_answer"))
    private  Collection<Answer> answerCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_user")
    private Collection<Statistic> staticCollection;

    public Users() {
    }

    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    public Collection<Answer> getQuestionCollection() {
        return answerCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.answerCollection = answerCollection;
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

    @Override
    public  String toString(){
        return  "{\"login\": \""+login+"\", \"password\": \""+password+"\"," +
                "\"email\": \""+email+"\", \"first_name\": \""+first_name+"\", \"last_name\": \""+last_name+"\"" +
                "}";
    }

}
