package com.example.demo.controllers;

import com.example.demo.models.Session;
import com.example.demo.repositories.SessionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable long id) {
        return sessionRepository.getReferenceById(id);
    }

    @PostMapping
    public Session create(@RequestBody Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Session session) {
        try {
            Session existingSession = sessionRepository.getReferenceById(id);
            BeanUtils.copyProperties(session, existingSession, "session_id");
            sessionRepository.saveAndFlush(existingSession);
        } catch (Exception e) {
            System.out.printf("Error: {}", e.getMessage());
        }


    }
}
