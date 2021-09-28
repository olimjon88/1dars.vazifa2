package uz.pdp.vazifa2.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {
    @NotNull
    private String text;

    @NotNull
    private Integer taskId;

    @NotNull
    private Integer userId;
}
