package com.example.demo.service;

import com.example.demo.dao.HallDAO;
import com.example.demo.dto.HallCreateDTO;
import com.example.demo.dto.HallDTO;
import com.example.demo.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallDAO hallDAO;

    @Autowired
    private Converter converter;

    @Override
    public Optional<HallDTO> getHallById(final Integer id) {
        final Optional<Hall> hall = hallDAO.findById(id);
        if (hall.isEmpty()) {
            return Optional.empty();
        }

        final HallDTO hallDTO = converter.hallToHallDTO(hall.get());
        return Optional.of(hallDTO);
    }

    @Override
    public Optional<HallDTO> createHallDTO(HallCreateDTO hallCreateDTO) {
        final Hall hall = converter.hallCreateDTOToHall(hallCreateDTO);
        final Hall newHall = hallDAO.save(hall);
        if (newHall.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(converter.hallToHallDTO(newHall));
    }

    public Optional<HallDTO> updateHall(final HallDTO hallDTO) {

        if (!hallDAO.existById(hallDTO.getId())) {
            return Optional.empty();
        }

        final Hall hall = converter.hallDTOToHall(hallDTO);
        hallDAO.save(hall);
        final HallDTO response = converter.hallToHallDTO(hall);
        return Optional.of(response);
    }

    @Override
    public List<HallDTO> getHalls() {
        final List<Hall> hallsList = hallDAO.findAll();
        if (hallsList.isEmpty()) {
            return new ArrayList<>();
        }

        final List<HallDTO> hallDTOList = new ArrayList<>();
        for (Hall hall : hallsList) {
            hallDTOList.add(converter.hallToHallDTO(hall));
        }
        return hallDTOList;
    }

    public void deleteHall(final Integer hallId) {
        hallDAO.deleteHall(hallId);
    }
}