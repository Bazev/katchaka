package fr.humanbooster.fx.katchaka.dao;

import fr.humanbooster.fx.katchaka.business.Interet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteretDao extends JpaRepository<Interet, Long> {

    Interet findByNom(String nom);

    List<Interet> findByNomContaining(String nom);
}
