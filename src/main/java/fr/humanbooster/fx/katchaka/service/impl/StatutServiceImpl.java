package fr.humanbooster.fx.katchaka.service.impl;

import fr.humanbooster.fx.katchaka.business.Statut;
import fr.humanbooster.fx.katchaka.dao.StatutDao;
import fr.humanbooster.fx.katchaka.service.StatutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutServiceImpl implements StatutService {
    private StatutDao statutDao;

    public StatutServiceImpl(StatutDao statutDao) {
        super();
        this.statutDao = statutDao;
    }


    @Override
    public Statut ajouterStatut(String nom) {
        return statutDao.save(new Statut(nom));
    }

    @Override
    public List<Statut> recupererStatuts() {
        return statutDao.findAll();
    }

    @Override
    public Statut recupererStatut(String nom) {
        return statutDao.findByNom(nom);
    }

    @Override
    public Statut recupererStatut(Long id) {
        return statutDao.findById(id).orElse(null);
    }


}
