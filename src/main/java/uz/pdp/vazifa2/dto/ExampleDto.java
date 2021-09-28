package uz.pdp.vazifa2.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleDto {
    @NotNull
    private String text;

    @NotNull
    private Integer taskId;
}
