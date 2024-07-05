package logic.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {
    public long id;

    public String username;

    public String email;

    public boolean isPaid;

    public String firstName;

    public String lastName;
}
