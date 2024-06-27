package cristianmartucci.U5_W2_D4.controllers;

import cristianmartucci.U5_W2_D4.entities.Author;
import cristianmartucci.U5_W2_D4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    private Page<Author> getAllAuthor(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return this.authorService.getAllAuthor(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author saveAuthor(@RequestBody Author author){
        return this.authorService.saveAuthor(author);
    }

    @GetMapping("/{authorId}")
    private Author findById(@PathVariable UUID authorId){
        return this.authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    private Author updateAuthor(@PathVariable UUID authorId, @RequestBody Author author){
        return this.authorService.updateAuthor(authorId, author);
    }

    @DeleteMapping("/{authorId}")
    private void deleteAuthor(@PathVariable UUID authorId){
        this.authorService.deleteAuthor(authorId);
    }
}
