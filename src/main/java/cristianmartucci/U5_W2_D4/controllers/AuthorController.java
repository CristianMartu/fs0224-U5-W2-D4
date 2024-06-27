package cristianmartucci.U5_W2_D4.controllers;

import cristianmartucci.U5_W2_D4.entities.Author;
import cristianmartucci.U5_W2_D4.exceptions.BadRequestException;
import cristianmartucci.U5_W2_D4.payloads.AuthorDTO;
import cristianmartucci.U5_W2_D4.payloads.AuthorResponseDTO;
import cristianmartucci.U5_W2_D4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private AuthorResponseDTO saveAuthor(@RequestBody @Validated AuthorDTO author, BindingResult validationResult){
        if (validationResult.hasErrors()){
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new AuthorResponseDTO(this.authorService.saveAuthor(author).getId());
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

    @PatchMapping("/{authorId}/avatar")
    public Author uploadAvatar(@PathVariable UUID authorId, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.authorService.patchAuthor(authorId, this.authorService.uploadAvatar(image));
    }
}
