package fr.humanbooster.fx.katchaka.controller;

import fr.humanbooster.fx.katchaka.service.InteretService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InteretController {
    private InteretService interetService;

    public InteretController( InteretService interetService) {
        this.interetService = interetService;
    }

    @GetMapping("interets")
    public ModelAndView interetsGet(@RequestParam(name = "filtre", defaultValue = "", required = false)String filtre) {
        ModelAndView mav = new ModelAndView("interets");
        mav.addObject("interets", interetService.recupererInterets(filtre));
        mav.addObject("filtre", filtre);
        return mav;
    }

    @GetMapping("interet")
    public ModelAndView interetGet() {
        return new ModelAndView("interet");
    }

    @PostMapping(value = "ajouterInteret" )
    public ModelAndView interetPost(@RequestParam(name = "interet")String nom) {
        interetService.ajouterInteret(nom);
        return new ModelAndView("redirect:interet");
    }
}
