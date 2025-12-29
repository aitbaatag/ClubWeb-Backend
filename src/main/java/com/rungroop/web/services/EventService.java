package com.rungroop.web.services;

import com.rungroop.web.dto.EventDto;

import java.util.List;

public interface EventService {
    List<EventDto> findAllEvents();
    EventDto createEvent(EventDto eventDto);
    EventDto updateEvent(EventDto eventDto);
    EventDto findById(Long id);
    void deleteEvent(Long id);
    List<EventDto> findEventsByClubId(Long clubId);
}

