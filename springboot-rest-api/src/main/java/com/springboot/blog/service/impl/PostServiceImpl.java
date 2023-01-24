package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
    public class PostServiceImpl implements com.springboot.blog.service.PostService {
    PostRepository postRepository;
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;


    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFound("Category","id", postDto.getCategoryId()));
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int PageNo, int PageSize,String SortBy,String SortDir) {
        Sort sort = SortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(SortBy).ascending() : Sort.by(SortBy).descending();

        Pageable pageable = PageRequest.of(PageNo,PageSize,sort);
        Page<Post> allPost = postRepository.findAll(pageable);

        List<Post> postList = allPost.getContent();
        List<PostDto> content = postList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(allPost.getNumber());
        postResponse.setPageSize(allPost.getSize());
        postResponse.setTotalPages(allPost.getTotalPages());
        postResponse.setTotalElements(allPost.getTotalElements());
        postResponse.setLast(allPost.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(Long id,PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post","id",id));
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFound("Category", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post","id",id));
        postRepository.delete(post);

    }

    @Override
    public List<PostDto> getPostsByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category","id",categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map((post) -> mapToDTO(post)).collect(Collectors.toList());

    }


    private PostDto mapToDTO(Post post) {
        PostDto postDto = mapper.map(post,PostDto.class);
        return postDto;

       /** PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent()); **/

    }

    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto,Post.class);
        Post newPost = postRepository.save(post);
        return newPost;

       /**  Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());**/
    }


}
