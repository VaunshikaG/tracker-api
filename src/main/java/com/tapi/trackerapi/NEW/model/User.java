package com.tapi.trackerapi.NEW.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userId", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Category> categories = new HashSet<Category>();

    @Column(name = "firstName", nullable = false, length = 10)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 10)
    private String lastName;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "token", nullable = false)
    private String token;

    public User() {
        super();
    }

    public User(Long userId, String firstName, String lastName, String email, String password, String token) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token = token;
    }


    public Set<Category> getCategories() { return categories; }

    public void setCategories(Set<Category> categories) { this.categories = categories; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public Long getId() { return userId; }

    public void setId(Long id) { this.userId = userId; }

}

