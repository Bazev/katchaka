package fr.humanbooster.fx.katchaka.dao;

import fr.humanbooster.fx.katchaka.business.Genre;
import fr.humanbooster.fx.katchaka.business.Personne;
import fr.humanbooster.fx.katchaka.business.Statut;
import fr.humanbooster.fx.katchaka.business.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreDao extends JpaRepository<Genre, Long> {

    Genre findByNom(String nom);
}
