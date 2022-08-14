package com.springboot.allinone.controller;

import com.springboot.allinone.entity.Tutorial;
import com.springboot.allinone.exception.ResourceNotFoundException;
import com.springboot.allinone.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepository;

    @PostMapping("/tutorials")
    public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @GetMapping("/tutorials/{id}")
    public Tutorial getTutorialById(@PathVariable("id") long id) throws Exception {
        return tutorialRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("Not found Tutorial with id = " + id));

    }

    @GetMapping("/tutorials")
    public List<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) {
            tutorialRepository.findAll().forEach(tutorials::add);
        } else {
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }

        return tutorials;
    }
}
