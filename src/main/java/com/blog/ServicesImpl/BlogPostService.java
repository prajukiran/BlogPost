package com.blog.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.BlogPostRepository;
import com.blog.model.BlogPost;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost post) {
        post.setCreatedAt(LocalDateTime.now());
        return blogPostRepository.save(post);
    }

    public BlogPost updatePost(Long id, BlogPost post) {
        Optional<BlogPost> existingPostOptional = blogPostRepository.findById(id);
        if (existingPostOptional.isPresent()) {
            BlogPost existingPost = existingPostOptional.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            return blogPostRepository.save(existingPost);
        } else {
            throw new RuntimeException("Post not found with id: " + id);
        }
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
