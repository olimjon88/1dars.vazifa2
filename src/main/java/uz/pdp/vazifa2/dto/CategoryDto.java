package uz.pdp.vazifa2.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Set<Integer> languageId;
}