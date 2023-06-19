package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        return cityRepository.findAll().stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {

        City city = new City();
        city.setName(dto.getName());
        city = cityRepository.save(city);
        return new CityDTO(city);
    }


}
