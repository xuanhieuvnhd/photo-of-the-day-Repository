package com.codegym.services.impl;

import com.codegym.model.Comment;
import com.codegym.repositories.CommentRepository;
import com.codegym.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @PersistenceContext
    private EntityManager em;


    @Override
    public Page<Comment> getAllCommentByDate(Date createdAt, Pageable pageable) {
        return commentRepository.findAllByCreateAtOrderByCreateAt(createdAt, pageable);
    }

    @Override
    public void incrementLike(Comment comment) {
        em.merge(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void insertComment(Comment comment) {
        commentRepository.save(comment);
    }
}
