package cz.prague.js.home.invoice.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String vorname;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
