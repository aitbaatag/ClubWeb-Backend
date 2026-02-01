package com.rungroop.web.controller;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> listEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable("id") Long id) {
        EventDto event = eventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/club/{clubId}")
    @PreAuthorize("@clubPermissionServiceImpl.canCreateEvent(#clubId, authentication.principal.id)")
    public ResponseEntity<EventDto> createEvent(
            @PathVariable("clubId") Long clubId,
            @Valid @RequestBody EventDto eventDto) {
        eventDto.setClubId(clubId);
        EventDto savedEvent = eventService.createEvent(eventDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping("/club/{clubId}")
    public List<EventDto> getEventsByClubId(@PathVariable("clubId") Long clubId) {
        return eventService.findEventsByClubId(clubId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("id") Long id, @Valid @RequestBody EventDto eventDto) {
        eventDto.setId(id);
        EventDto updatedEvent = eventService.updateEvent(eventDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }
}

