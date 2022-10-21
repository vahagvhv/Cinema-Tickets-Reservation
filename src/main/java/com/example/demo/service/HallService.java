package com.example.demo.service;

import com.example.demo.dto.HallCreateDTO;
import com.example.demo.dto.HallDTO;

import java.util.List;
import java.util.Optional;

public interface HallService {

    Optional<HallDTO> createHallDTO(HallCreateDTO hallCreateDTO);

    public Optional<HallDTO> updateHall(HallDTO hallDTO);

    List<HallDTO> getHalls();

    Optional<HallDTO> getHallById(Integer id);

    void deleteHall(final Integer hallId);
}
