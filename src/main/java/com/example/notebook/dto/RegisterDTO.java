package com.example.notebook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {

  @NotBlank
  @Size(message = "first name must to be min 5 max 30 length", min = 5, max = 30)
  String firstName;

  @NotBlank
  @Size(message = "last name must to be min 5 max 30 length", min = 5, max = 30)
  String lastName;

  @Email(message = "enter valid e-mail")
  @NotBlank(message = "e-mail should not be blank")
  String email;

  @Size(message = "Password must to be min 5 and max 30 length", min = 5, max = 30)
  char[] password;

  @NotBlank(message = "login should not be blank")
  String login;
  @Pattern(message = "Can register only by Ukrainian number!", regexp = "^\\+380\\d{9}$\n")
  String phone;
  @Size(message = "Password must to be min 5 and max 30 length", min = 5, max = 30)
  char[] repeatedPassword;
}
