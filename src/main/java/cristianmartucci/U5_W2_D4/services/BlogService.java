package cristianmartucci.U5_W2_D4.services;

import cristianmartucci.U5_W2_D4.entities.Author;
import cristianmartucci.U5_W2_D4.entities.Blog;
import cristianmartucci.U5_W2_D4.entities.BlogPayload;
import cristianmartucci.U5_W2_D4.exceptions.NotFoundException;
import cristianmartucci.U5_W2_D4.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    public AuthorService authorService;

    @Autowired
    public BlogRepository blogRepository;

    public Page<Blog> getAllBlog(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return blogRepository.findAll(pageable);
    }

    public Blog saveBlog(BlogPayload blogPayload){
        Author author = authorService.findById(blogPayload.getAuthorId());

        Blog blog = new Blog(blogPayload.getCategoria(), blogPayload.getTitolo(), blogPayload.getCover(),
                blogPayload.getContenuto(), blogPayload.getTempoDiLettura(), author);

        return this.blogRepository.save(blog);
    }

    public Blog findById(UUID id){
        return this.blogRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blog updateBlog(UUID id, BlogPayload updateBlog){
        Blog blog = this.findById(id);

        blog.setCategoria(updateBlog.getCategoria());
        blog.setTitolo(updateBlog.getTitolo());
        blog.setCover(updateBlog.getCover());
        blog.setContenuto(updateBlog.getContenuto());
        blog.setTempoDiLettura(updateBlog.getTempoDiLettura());

        blog.setAuthor(authorService.findById(updateBlog.getAuthorId()));
        
        return this.blogRepository.save(blog);
    }

    public void deleteBlog(UUID id){
        this.blogRepository.delete(this.findById(id));
    }

}
