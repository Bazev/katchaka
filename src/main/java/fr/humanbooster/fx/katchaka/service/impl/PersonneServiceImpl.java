package fr.humanbooster.fx.katchaka.service.impl;

import fr.humanbooster.fx.katchaka.business.*;
import fr.humanbooster.fx.katchaka.dao.PersonneDao;
import fr.humanbooster.fx.katchaka.service.PersonneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonneServiceImpl implements PersonneService {

    private PersonneDao personneDao;

    public PersonneServiceImpl(PersonneDao personneDao) {
        this.personneDao = personneDao;
    }

    @Override
    public Personne enregistrerPersonne(Personne personne) {
        return personneDao.save(personne);
    }

    @Override
    public List<Personne> recupererPersonnes() {
        return personneDao.findAll();
    }

    @Override
    public List<Personne> recupererPersonnes(Ville ville, Genre genre, Statut statut) {
        return personneDao.findByVilleAndGenreAndStatut(ville,genre, statut);
    }

    @Override
    public List<Personne> recupererPersonnesQuadra(List<Ville> villes, Genre genre, Statut statut, Date dateDebut, Date dateFin, Interet interet) {
        return personneDao.findByVilleInAndGenreAndStatutAndDateDeNaissanceBetweenAndInteretsContains(villes, genre, statut, dateDebut, dateFin, interet);
    }

    @Override
    public List<Personne> recupererPersonnesParNombreInteret() {
        return personneDao.findPersonnesByInteretsDesc();
    }

    @Override
    public List<Personne> recupererPersonnesParVille(List<Ville> villes) {
        return personneDao.findByVille(villes);
    }

    @Override
    public Page<Personne> recupererPersonnes(String filtre, Pageable pageable) {
        return personneDao.findByPseudoContaining(filtre, pageable);
    }

    @Override
    public Personne recupererPersonne(Long id) {
        return personneDao.findById(id).orElse(null);
    }

    @Override
    public boolean supprimerPersonne(Long id) {
         Personne personne = recupererPersonne(id);
         if (personne == null) {
             return false;
         }else {
             personneDao.delete(personne);
             if (recupererPersonne(id)==null) {
                 return true;
             }else {
                 return false;
             }
         }
    }

    @Override
    public Personne recupererPersonne(String email, String motDePasse) {
        return personneDao.findByEmailAndMotDePasse(email, motDePasse);
    }

    @Override
    public Page<Personne> recupererPersonnes(Personne personneEnSession, String filtre, Pageable pageable) {
        return personneDao.findByPseudoContainingAndIdNot(filtre, pageable, personneEnSession.getId());
    }

}
