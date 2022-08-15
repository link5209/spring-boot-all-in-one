package com.springboot.allinone.controller;

import com.springboot.allinone.entity.Comment;
import com.springboot.allinone.exception.ResourceNotFoundException;
import com.springboot.allinone.repository.CommentRepository;
import com.springboot.allinone.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/tutorials/{tutorialId}/comments")
    public Comment createComment(@PathVariable(value = "tutorialId") Long tutorialId,
         @RequestBody Comment commentRequest) {
        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
        return comment;
    }
}
