package com.springboot.blog.payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "name should not be null or empty")
    private String name;
    @NotEmpty(message ="email should not be null or empty")
    @Email
    private String email;
    @NotEmpty()
    @Size(min = 10, message = "body must not be less than 10 words ")
    private String body;
}
