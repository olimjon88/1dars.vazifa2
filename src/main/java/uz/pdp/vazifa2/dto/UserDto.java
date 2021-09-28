package uz.pdp.vazifa2.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @NotNull
    private String email;

    @NotNull
    private String password;

}
