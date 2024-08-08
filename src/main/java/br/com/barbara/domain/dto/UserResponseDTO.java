package br.com.barbara.domain.dto;

import java.util.UUID;

public record UserResponseDTO (
    UUID id,
    String email
) {

}
