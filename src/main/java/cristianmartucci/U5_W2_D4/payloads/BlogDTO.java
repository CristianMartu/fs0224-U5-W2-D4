package cristianmartucci.U5_W2_D4.payloads;

import java.util.UUID;

public record BlogDTO(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, UUID authorId) {
}
