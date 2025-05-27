package com.api.ecommerce.users.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data //getters y setters con lombok
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;  
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Address address;
    

    private boolean isActive;

}
