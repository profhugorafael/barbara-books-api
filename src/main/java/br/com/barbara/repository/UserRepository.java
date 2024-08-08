package br.com.barbara.repository;

import br.com.barbara.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public boolean isEmailAlreadyRegistered(String email) {
        return find("email", email)
                .firstResultOptional()
                .isPresent();
    }
}