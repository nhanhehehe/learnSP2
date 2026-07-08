package hoc.tot.nhan.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
     String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
     String password;
    @Email(message = "EMAIL_INVALID")
     String email;
     String firstName;
    @NotBlank(message = "this must not be blank")
     String lastName;
     LocalDate dob;
}
