package cristianmartucci.U5_W2_D4.payloads;

import java.time.LocalDate;

public record AuthorDTO(String nome, String cognome, String email, LocalDate dataDiNascita){
}