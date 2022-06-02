package com.example.student;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="login_info")
@NamedQuery(name="Login.findAll", query="SELECT m FROM Login m")
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "login_id", nullable = false)
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public Login(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Login(Integer id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public Login() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


