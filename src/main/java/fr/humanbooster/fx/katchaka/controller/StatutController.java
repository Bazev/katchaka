package fr.humanbooster.fx.katchaka.controller;

import fr.humanbooster.fx.katchaka.service.StatutService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class StatutController {

    private StatutService statutService;

    public StatutController(StatutService statutService ) {
        this.statutService = statutService;
    }

     @GetMapping("statut")
     public ModelAndView statutGet(@RequestParam(name = "ID")Long id) {
         System.out.println(new Date()+ " : + nouvelle requete GET");
        ModelAndView mav = new ModelAndView("statut");
        mav.addObject("statut", statutService.recupererStatut(id));
        return mav;
     }

     @GetMapping("statuts")
    public ModelAndView statutsGet() {
        ModelAndView mav = new ModelAndView("statuts");
        mav.addObject("statuts", statutService.recupererStatuts());
        return mav;
     }

}
