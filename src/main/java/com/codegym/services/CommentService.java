package com.codegym.services;

import com.codegym.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;


public interface CommentService {
    Page<Comment> getAllCommentByDate(Date createdAt, Pageable pageable);

    void incrementLike(Comment comment);

    Comment getById(Long id);

    void insertComment(Comment comment);
}
