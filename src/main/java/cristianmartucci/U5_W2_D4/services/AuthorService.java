package cristianmartucci.U5_W2_D4.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cristianmartucci.U5_W2_D4.entities.Author;
import cristianmartucci.U5_W2_D4.exceptions.NotFoundException;
import cristianmartucci.U5_W2_D4.payloads.AuthorDTO;
import cristianmartucci.U5_W2_D4.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Page<Author> getAllAuthor(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return authorRepository.findAll(pageable);
    }

    public Author saveAuthor(AuthorDTO author){

        Author newAuthor = new Author(author.nome(), author.cognome(), author.email(), author.dataDiNascita());

        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + newAuthor.getNome() + "+" + newAuthor.getCognome());
        return this.authorRepository.save(newAuthor);
    }

    public Author findById(UUID authorId){
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author updateAuthor(UUID authorId, Author updateAuthor){
        Author author = this.findById(authorId);
        author.setNome(updateAuthor.getNome());
        author.setCognome(updateAuthor.getCognome());
        author.setEmail(updateAuthor.getEmail());
        author.setDataDiNascita(updateAuthor.getDataDiNascita());
        author.setAvatar("https://ui-avatars.com/api/?name=" + updateAuthor.getNome() + "+" + updateAuthor.getCognome());
        return this.authorRepository.save(author);
    }

    public void deleteAuthor(UUID authorId){
        this.authorRepository.delete(this.findById(authorId));
    }

    public String uploadAvatar(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

    public Author patchAuthor(UUID authorId, String url){
        Author author = findById(authorId);
        author.setAvatar(url);
        return authorRepository.save(author);
    }
}
