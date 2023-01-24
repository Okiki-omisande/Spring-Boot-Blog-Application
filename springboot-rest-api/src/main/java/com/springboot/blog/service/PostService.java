package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int PageNo, int PageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePostById(Long id, PostDto postDto);
    void deletePostById(Long id);
    List<PostDto> getPostsByCategoryId(Long categoryId);
}
