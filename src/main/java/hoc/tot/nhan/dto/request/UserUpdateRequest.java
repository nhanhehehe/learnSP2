package hoc.tot.nhan.dto.request;

import hoc.tot.nhan.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
     String password;
     String firstname;
     String lastname;
     @DobConstraint(min = 18, message = "INVALID_YEAR")
     LocalDate dob;
     List<String> roles;
}
