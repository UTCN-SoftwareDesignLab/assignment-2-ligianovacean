package bookStore.dto;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {
    @Size(min = 8)
    public String username;
    public String password;
}
