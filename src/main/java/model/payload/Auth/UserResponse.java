package model.payload.Auth;

import model.entities.User.UserType;

public record UserResponse(Long id, String name, String email, UserType type) {

}
