package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.dto.SpotDto;
import com.openclassrooms.escalade.dto.SpotSaveDto;
import com.openclassrooms.escalade.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/spots")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    @ResponseBody
    public List<SpotDto> getAllSpots() {
        return spotService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SpotDto getSpot(@PathVariable Long id) {
        return spotService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public SpotDto createSpot(@RequestBody SpotSaveDto spot) {
        return spotService.create(spot);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public SpotDto updateSpot(@RequestBody SpotSaveDto spot,
                              @PathVariable Long id) {
        return spotService.update(spot, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        try {
            spotService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
