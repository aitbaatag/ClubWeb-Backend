package com.rungroop.web.services.impl;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.EventRepository;
import com.rungroop.web.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private final ClubRepository clubRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return mapToEventDto(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        Event saved = eventRepository.save(event);
        return mapToEventDto(saved);
    }

    @Override
    public List<EventDto> findEventsByClubId(Long clubId) {
        List<Event> events = eventRepository.findAll().stream()
                .filter(event -> event.getClub().getId().equals(clubId))
                .collect(Collectors.toList());
        return events.stream().map(this::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        if (eventDto.getId() == null) {
            throw new IllegalArgumentException("Event ID cannot be null for update operation");
        }
        Event existingEvent = eventRepository.findById(eventDto.getId())
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventDto.getId()));

        existingEvent.setName(eventDto.getName());
        existingEvent.setLocation(eventDto.getLocation());
        existingEvent.setDescription(eventDto.getDescription());
        existingEvent.setImageUrl(eventDto.getImageUrl());
        existingEvent.setStartDtateTime(eventDto.getStartDtateTime());
        existingEvent.setEndDtateTime(eventDto.getEndDtateTime());

        if (eventDto.getClubId() != null) {
            Club club = clubRepository.findById(eventDto.getClubId())
                    .orElseThrow(() -> new RuntimeException("Club not found with id: " + eventDto.getClubId()));
            existingEvent.setClub(club);
        }

        Event updatedEvent = eventRepository.save(existingEvent);
        return mapToEventDto(updatedEvent);
    }

    private Event mapToEvent(EventDto eventDto) {
        Club club = null;
        if (eventDto.getClubId() != null) {
            club = clubRepository.findById(eventDto.getClubId())
                    .orElseThrow(() -> new RuntimeException("Club not found with id: " + eventDto.getClubId()));
        }

        return Event.builder()
                .name(eventDto.getName())
                .location(eventDto.getLocation())
                .description(eventDto.getDescription())
                .imageUrl(eventDto.getImageUrl())
                .startDtateTime(eventDto.getStartDtateTime())
                .endDtateTime(eventDto.getEndDtateTime())
                .club(club)
                .build();
    }

    private EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .location(event.getLocation())
                .description(event.getDescription())
                .imageUrl(event.getImageUrl())
                .startDtateTime(event.getStartDtateTime())
                .endDtateTime(event.getEndDtateTime())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .clubId(event.getClub() != null ? event.getClub().getId() : null)
                .build();
    }
}

