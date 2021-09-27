package fr.humanbooster.fx.katchaka.dao;

import fr.humanbooster.fx.katchaka.business.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PersonneDao extends JpaRepository<Personne, Long> {

    List<Personne> findByVilleAndGenreAndStatut(Ville ville, Genre genre, Statut statut);

    List<Personne> findByVilleAndGenreAndStatutAndDateDeNaissanceBetween(Ville ville, Genre genre, Statut statut, Date dateDebut, Date dateFin);

    List<Personne> findByVilleInAndGenreAndStatutAndDateDeNaissanceBetweenAndInteretsContains(List<Ville> villes, Genre genre, Statut statut, Date dateDebut, Date dateFin, Interet interet);

    @Query("FROM Personne ORDER BY interets.size DESC ")
    List<Personne>findPersonnesByInteretsDesc();

    @Query("FROM Personne p WHERE p.ville = ?1 ")
    List<Personne>findByVille(List<Ville> villes);

    Page<Personne> findByPseudoContaining(String filtre, Pageable pageable);

    Personne findByEmailAndMotDePasse(String email, String motDePasse);


    Page<Personne> findByPseudoContainingAndIdNot(String filtre, Pageable pageable, Long id);
}
