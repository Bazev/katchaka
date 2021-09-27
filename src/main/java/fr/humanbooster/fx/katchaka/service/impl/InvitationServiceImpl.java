package fr.humanbooster.fx.katchaka.service.impl;

import fr.humanbooster.fx.katchaka.business.Invitation;
import fr.humanbooster.fx.katchaka.business.Personne;
import fr.humanbooster.fx.katchaka.dao.InvitationDAO;
import fr.humanbooster.fx.katchaka.service.InvitationService;
import fr.humanbooster.fx.katchaka.service.PersonneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    private final InvitationDAO invitationDAO;
    private final PersonneService personneService;

    public InvitationServiceImpl(InvitationDAO invitationDAO, PersonneService personneService) {
        this.invitationDAO = invitationDAO;
        this.personneService = personneService;
    }

    @Override
    public Invitation ajouterInvitation(Long idDestinataire, Long idExpediteur) {
        Invitation invitation = new Invitation(personneService.recupererPersonne(idExpediteur),
                personneService.recupererPersonne(idDestinataire));
        return invitationDAO.save(invitation);
    }

    @Override
    public Invitation mettreAJourInvitation(Invitation invitation, Boolean estAcceptee) {
        if (invitation ==null) {
            return null;
        }else {
            if (invitation.getEstAccepte()==null) {
                invitation.setEstAccepte(estAcceptee);
                invitationDAO.save(invitation);
            }
            return invitation;
        }
    }

    @Override
    public Invitation recupererInvitation(Personne destinataire, Long idInvitation) {
        return invitationDAO.findByDestinataireAndId(destinataire, idInvitation);
    }

    @Override
    public List<Invitation> recupererInvitationsRecuesSansReponse(Personne personne) {
        return invitationDAO.findByDestinataireAndEstAccepteIsNull(personne);
    }

    @Override
    public List<Invitation> recupererInvitationsEnvoyeesSansReponse(Personne personne) {
        return invitationDAO.findByExpediteurAndEstAccepteIsNull(personne);
    }
}
