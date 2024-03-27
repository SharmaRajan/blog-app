package com.codewithrajan.blog.payloads;

import com.codewithrajan.blog.entities.Category;
import com.codewithrajan.blog.entities.Comment;
import com.codewithrajan.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer postId;

    private String title;

    private String content;

    private String imageName = "default.png";

    private Date addedDate;

    // to resolve recursion we changed Category to CategoryDTO
    // and User to UserDTO

    private CategoryDTO category;

    private UserDTO user;

    private List<CommentDTO> comments;

}
