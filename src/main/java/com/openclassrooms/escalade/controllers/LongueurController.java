package com.openclassrooms.escalade.controllers;

import com.openclassrooms.escalade.entities.Longueur;
import com.openclassrooms.escalade.repositories.LongueurRepository;
import com.openclassrooms.escalade.services.LongueurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LongueurController {

    private final LongueurService longueurService;

    public LongueurController(LongueurService longueurService) {
        this.longueurService = longueurService;
    }

    @GetMapping("/longueurs")
    @ResponseBody
    public List<Longueur> getAllLongueurs() {
        return longueurService.findAll();
    }

    @GetMapping("/longueur/{id}")
    @ResponseBody
    public Longueur getLongueur(@PathVariable Long id) {
        return longueurService.findOne(id);
    }

    @PostMapping("/longueur")
    @ResponseBody
    public Longueur createLongueur(@RequestBody Longueur longueur) {
        return longueurService.create(longueur);
    }

    @PutMapping("/longueur")
    @ResponseBody
    public Longueur updateLongueur(@RequestBody Longueur longueur) {
        return longueurService.update(longueur);
    }

    @DeleteMapping("/longueur/{id}")
    public void deleteLongueur(@PathVariable Long id) {
        longueurService.delete(id);
    }
}
