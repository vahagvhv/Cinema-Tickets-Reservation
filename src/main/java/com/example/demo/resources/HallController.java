package com.example.demo.resources;

import com.example.demo.dto.HallCreateDTO;
import com.example.demo.dto.HallDTO;
import com.example.demo.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping
    public ResponseEntity addHall(final @RequestBody(required = true) HallCreateDTO hallCreateDTO) {

        final Optional<HallDTO> hallDTO = hallService.createHallDTO(hallCreateDTO);
        if (hallDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hall creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(hallDTO);
    }

    @PutMapping
    public ResponseEntity updateHall(final @RequestBody(required = true) HallDTO hallDTO) {
        final Optional<HallDTO> response = hallService.updateHall(hallDTO);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hall update failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(final @PathVariable(required = true) Integer id) {

        final Optional<HallDTO> hallById = hallService.getHallById(id);
        if (hallById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Hall with given id not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hallById);
    }

    @GetMapping("/all")
    public ResponseEntity getAllHalls() {

        final List<HallDTO> halls = hallService.getHalls();
        if (halls.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Halls not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(halls);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHall(final @PathVariable(required = true) Integer id) {
        hallService.deleteHall(id);
        return ResponseEntity.status(HttpStatus.OK).body("Hall successfully deleted");
    }
}


