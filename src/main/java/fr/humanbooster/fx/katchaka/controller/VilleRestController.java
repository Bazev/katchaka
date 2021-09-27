package fr.humanbooster.fx.katchaka.controller;


import fr.humanbooster.fx.katchaka.business.Ville;
import fr.humanbooster.fx.katchaka.service.VilleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class VilleRestController {

    private final VilleService villeService;

    public VilleRestController(VilleService villeService) {
        this.villeService = villeService;
    }


    @GetMapping("villes")
    public List<Ville> recupererVilles() {
       return villeService.recupererVilles();
    }

    @PostMapping("villes/{nom}")
    public Ville ajouterVille(@PathVariable String nom) {
        return villeService.ajouterVille(nom);
    }

    @PutMapping("villes/{id}/{nom}")
    public Ville modifierVille(@PathVariable Long id, @PathVariable String nom) {
        Ville ville = villeService.recupererVille(id);
        if (ville != null) {
            ville.setNom(nom);
            return villeService.enregistrerVille(ville);
        }
        return null;
    }

    @DeleteMapping("villes/{id}")
    public boolean supprimerVille(@PathVariable Long id) {
        return villeService.supprimerVille(id);
    }

}
