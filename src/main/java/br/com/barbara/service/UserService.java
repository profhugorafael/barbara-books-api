package br.com.barbara.service;

import br.com.barbara.domain.dto.UserRequestDTO;
import br.com.barbara.domain.dto.UserResponseDTO;
import br.com.barbara.domain.model.User;
import br.com.barbara.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<UserResponseDTO> getAll() {
        return userRepository.listAll()
                .stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO createUser(UserRequestDTO userDto) {
        if(userRepository.isEmailAlreadyRegistered(userDto.email())) {
            return null;
        }

        User userToPersist = new User();
        userToPersist.setEmail(userDto.email());
        userToPersist.setPassword(userDto.password());

        userRepository.persist(userToPersist);

        User userSaved = userRepository.listAll()
                .stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(userToPersist.getEmail()))
                .findFirst()
                .get();

        return new UserResponseDTO(userSaved.getId(), userSaved.getEmail());
    }


}
