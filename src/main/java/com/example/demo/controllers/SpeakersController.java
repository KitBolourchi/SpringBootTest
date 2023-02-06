package com.example.demo.controllers;

import com.example.demo.models.Session;
import com.example.demo.models.Speaker;
import com.example.demo.repositories.SessionRepository;

import com.example.demo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ve/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getReferenceById(id);
    }

    @PostMapping
    public Speaker create(@RequestBody Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.getReferenceById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        speakerRepository.saveAndFlush(existingSpeaker);
    }
    
}
