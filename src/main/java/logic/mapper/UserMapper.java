package logic.mapper;

import logic.dto.UserDto;
import logic.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email((user.getEmail()))
                .build();
    }
}
