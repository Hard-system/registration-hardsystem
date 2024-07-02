package business.mapper;

import business.dto.UserDto;
import business.model.User;

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
