package fr.humanbooster.fx.katchaka.controller;


import fr.humanbooster.fx.katchaka.service.VilleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VilleController {

    private VilleService villeService;

    public VilleController(VilleService villeService){
        this.villeService = villeService;
    }

    @GetMapping("/ville")
    public ModelAndView villeGet() {
        return new ModelAndView("ville");
    }

    @PostMapping(value = "ajouterVille")
    public ModelAndView villePost(@RequestParam(name = "ville")String nom) {
        villeService.ajouterVille(nom);
        return new ModelAndView("redirect:index");
    }
    // Méthode qui traite une requête HTTP dont la méthode est GET et l'URL villes
    // Cette méthode est invoquée lorsqu'un internaute se rend
    // sur l'URL http://localhost:8080 car elle prend en charge
    // l'URL /
    @GetMapping({"/","villes"})
    public ModelAndView villesGet(@RequestParam(name = "FILTRE", defaultValue = "", required = false)String filtre) {
        ModelAndView mav = new ModelAndView("villes");
        mav.addObject("villes", villeService.recupererVilles(filtre));
        mav.addObject("filtre", filtre);
        return mav;
    }
}
