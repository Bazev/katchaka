package fr.humanbooster.fx.katchaka.controller;

import fr.humanbooster.fx.katchaka.business.Personne;
import fr.humanbooster.fx.katchaka.service.InvitationService;
import fr.humanbooster.fx.katchaka.service.PersonneService;
import fr.humanbooster.fx.katchaka.utils.ProgramSendSms;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class InvitationController {

    private final PersonneService personneService;
    private final InvitationService invitationService;
    private final HttpSession httpSession;




    public InvitationController(PersonneService personneService, InvitationService invitationService, HttpSession httpSession) {
        this.personneService = personneService;
        this.invitationService = invitationService;
        this.httpSession = httpSession;
    }

    @GetMapping("inviterPersonne")
    public ModelAndView invitationGet(@RequestParam(name = "ID")Long idDestinataire) {
        Personne expediteur = (Personne) httpSession.getAttribute("personne");
        if (expediteur==null) {
            return new ModelAndView("redirect:connexion");
        } else {
            ProgramSendSms programSendSms = new ProgramSendSms();
            Long idExpediteur = expediteur.getId();
            programSendSms.sendSms("0665088677");
            expediteur.getInvitationsEnvoyees().add(invitationService.ajouterInvitation(idDestinataire, idExpediteur));
            return new ModelAndView("redirect:tableauDeBord");
        }
    }

}
