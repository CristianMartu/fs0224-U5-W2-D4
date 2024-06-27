package cristianmartucci.U5_W2_D4.controllers;

import cristianmartucci.U5_W2_D4.entities.Blog;
import cristianmartucci.U5_W2_D4.payloads.BlogDTO;
import cristianmartucci.U5_W2_D4.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    private Page<Blog> getAllBlog(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogService.getAllBlog(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Blog saveBlog(@RequestBody BlogDTO blog){
        return this.blogService.saveBlog(blog);
    }

    @GetMapping("/{blogId}")
    private Blog findById(@PathVariable UUID blogId){
        return this.blogService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    private Blog updateBlog(@PathVariable UUID blogId, @RequestBody BlogDTO blog){
        return this.blogService.updateBlog(blogId, blog);
    }

    @DeleteMapping("/{blogId}")
    private void deleteBlog(@PathVariable UUID blogId){
        this.blogService.deleteBlog(blogId);
    }
}
