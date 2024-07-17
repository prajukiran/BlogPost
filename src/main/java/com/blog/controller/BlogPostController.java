package com.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.BlogPost;
import com.blog.ServicesImpl.*;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private com.blog.ServicesImpl.BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        Optional<BlogPost> postOptional = blogPostService.getPostById(id);
        return postOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

@PostMapping
public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost post) {
    BlogPost createdPost = blogPostService.createPost(post);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
}

@PutMapping("/{id}")
public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost post) {
    BlogPost updatedPost = blogPostService.updatePost(id, post);
    return ResponseEntity.ok(updatedPost);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    blogPostService.deletePost(id);
    return ResponseEntity.noContent().build();
}
}
