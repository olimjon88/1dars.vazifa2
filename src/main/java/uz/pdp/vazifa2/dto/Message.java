package uz.pdp.vazifa2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String message;
    private boolean status;
}
