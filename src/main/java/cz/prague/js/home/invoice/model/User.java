package cz.prague.js.home.invoice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@Document
public class User {
    private String id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String readablePassword;
    private Set<Authority> authorities = new HashSet<>();

}
