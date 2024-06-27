package cristianmartucci.U5_W2_D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(@NotEmpty(message = "Nome obbligatorio") String nome,
                        @NotEmpty(message = "Cognome obbligatorio") String cognome,
                        @NotEmpty(message = "Email obbligatoria")
                        @Email(message = "Email non valida") String email,
                        LocalDate dataDiNascita){
}