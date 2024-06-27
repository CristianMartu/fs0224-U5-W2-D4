package cristianmartucci.U5_W2_D4.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public record ErrorsDTO(String message, LocalDateTime time) {
}
