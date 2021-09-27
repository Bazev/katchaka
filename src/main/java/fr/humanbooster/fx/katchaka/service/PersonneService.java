package fr.humanbooster.fx.katchaka.service;

import fr.humanbooster.fx.katchaka.business.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;



public interface PersonneService {

    Personne enregistrerPersonne(Personne personne);

    List<Personne> recupererPersonnes();

    List<Personne> recupererPersonnes(Ville ville, Genre genre, Statut statut);

    List<Personne> recupererPersonnesQuadra(List<Ville> villes, Genre genre, Statut statut, Date dateDebut, Date dateFin, Interet interet);

    List<Personne> recupererPersonnesParNombreInteret();

    List<Personne> recupererPersonnesParVille(List<Ville> villes);

    Page<Personne> recupererPersonnes(String filtre, Pageable pageable);

    Personne recupererPersonne(Long id);

    boolean supprimerPersonne(Long id);

    Personne recupererPersonne(String email, String motDePasse);

    Page<Personne> recupererPersonnes(Personne personneEnSession, String filtre, Pageable pageable);

}
