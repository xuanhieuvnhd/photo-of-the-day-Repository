package com.codegym.controllers;

import com.codegym.model.Comment;
import com.codegym.services.CommentService;
import com.codegym.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Optional;


@Controller
public class HomeController {
    @Autowired
    private CommentService commentService = new CommentServiceImpl();

    @GetMapping("/")
    public ModelAndView getHomePage(@RequestParam("s") Optional<String> s, @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = new PageRequest(page, size );
        Page<Comment> comments = commentService.getAllCommentByDate(Date.valueOf(s.get()), pageable);
        ModelAndView modelAndView = new ModelAndView("index", "listComment", comments);
        modelAndView.addObject("newComment", new Comment());
        return modelAndView;
    }
//    public ModelAndView getHomePage(@PageableDefault(value = 2)Pageable pageable) {
//        Page<Comment> comments = commentService.getAllCommentByDate()
//        ModelAndView modelAndView = new ModelAndView("index", "listComment", comments);
//        modelAndView.addObject("newComment", new Comment());
//        return modelAndView;
//    }

    @PostMapping("/")
    public String insertComment(@ModelAttribute Comment newComment) {
        commentService.insertComment(newComment);
        return "redirect:/";
    }

    @GetMapping("/increment/{id}")
    public String incrementLikes(@PathVariable Long id) {
        Comment comment = commentService.getById(id);
        comment.setLikes(comment.getLikes() + 1);
        commentService.insertComment(comment);
        return "redirect:/";
    }
}
