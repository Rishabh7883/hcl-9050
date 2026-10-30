package com.springcorefundamentals.jwtdemo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcorefundamentals.jwtdemo.entity.Post;
import com.springcorefundamentals.jwtdemo.repository.PostRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/posts")

@AllArgsConstructor
public class PostController {
    private final PostRepository posts;

    @GetMapping
    public List<Post> all(){
        return posts.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping
    public Post create(@Valid @RequestBody Post req, Authentication auth){
        return posts.save(new Post(req.getTitle(),req.getContent(),auth.getName()));
    }
}
