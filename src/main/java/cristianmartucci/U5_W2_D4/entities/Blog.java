package cristianmartucci.U5_W2_D4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Blog {
    @Id
    @GeneratedValue
    private UUID id;

    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Blog(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, Author author) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.author = author;
    }
}
