package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = modelMapper;
    }


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToCommentEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapToCommentDto(savedComment);

    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {
        Post postWithPostId = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        List<Comment>  commentList = commentRepository.findByPostId(postWithPostId.getId());
        return commentList.stream().map(comment -> mapToCommentDto(comment)).collect(Collectors.toList());


    }
    @Override
    public CommentDto getCommentByCommentId(Long postId, Long commentId) {
        Post postWithPostId = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        if (!comment.getPost().getId().equals(postWithPostId.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");

        }

        return mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post postWithPostId = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        if (!comment.getPost().getId().equals(postWithPostId.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }


        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment savedComment = commentRepository.save(comment);
        return mapToCommentDto(savedComment);
    }

    @Override
    public String deleteComment(Long postId, Long commentId) {
        Post postWithPostId = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFound("post","id", postId));
        if (!comment.getPost().getId().equals(postWithPostId.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }

        commentRepository.delete(comment);
        return String.format("Comment with commentId: %d on Post with PostId: %d is successfully deleted",commentId,postId);
    }


    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
        /** commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody()); **/


    }

    private Comment mapToCommentEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto,Comment.class);
        return comment;
       /** comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody()); **/
    }
}
