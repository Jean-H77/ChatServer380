package org.server.model;

public record RegistrationDetails(
        String email,
        String password,
        String username,
        String profileImage,
        String dateOfBirth
) {
}
