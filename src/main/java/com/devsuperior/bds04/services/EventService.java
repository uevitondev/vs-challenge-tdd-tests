package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable) {
        return eventRepository.findAll(pageable).map(event -> new EventDTO(event));
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {

        Event event = new Event();
        event.setId(null);
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());
        event.setDate(dto.getDate());

        City city = cityRepository.findById(dto.getCityId()).get();
        event.setCity(city);
        event = eventRepository.save(event);

        return new EventDTO(event);
    }


}
