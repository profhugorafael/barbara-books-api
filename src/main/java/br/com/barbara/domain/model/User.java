package br.com.barbara.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Book> rentedBooks;

    public User() {
    }

    public User(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(List<Book> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }
}

/*
oneToOne = hasOne + hasOne
oneToMany = hasOne + hasMany
manyToMany = hasMany + hasMany

{
    "id": 1
    "name": "pedro123"
    "rentedBooks": [
        {
            "id": 1,
            "title": "harry potter",
        }
    ]
}
*/
