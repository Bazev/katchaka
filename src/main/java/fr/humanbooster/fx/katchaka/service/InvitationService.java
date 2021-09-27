package fr.humanbooster.fx.katchaka.service;

import fr.humanbooster.fx.katchaka.business.Invitation;
import fr.humanbooster.fx.katchaka.business.Personne;

import java.util.List;

public interface InvitationService {

    Invitation ajouterInvitation(Long idDestinataire, Long idExpediteur);


    Invitation mettreAJourInvitation(Invitation invitation, Boolean estAcceptee);

    Invitation recupererInvitation(Personne destinataire, Long idInvitation);

    List<Invitation> recupererInvitationsRecuesSansReponse(Personne personne);

    List<Invitation> recupererInvitationsEnvoyeesSansReponse(Personne personne);

}
