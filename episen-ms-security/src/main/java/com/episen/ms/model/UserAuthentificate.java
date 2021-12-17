package com.episen.ms.model;


import javax.persistence.*;

@Entity
@Table(name="users")
public class UserAuthentificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userauth", nullable = false)
    private String userauth;

    @Column(name = "password", nullable = false)
    private String password;

    public String getUserAuth() {
        return userauth;
    }

    public void setUserauth(String userauth) {
        this.userauth = userauth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
