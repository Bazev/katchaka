package fr.humanbooster.fx.katchaka.service.impl;

import fr.humanbooster.fx.katchaka.business.Interet;
import fr.humanbooster.fx.katchaka.dao.InteretDao;
import fr.humanbooster.fx.katchaka.service.InteretService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteretServiceImpl implements InteretService {
    private InteretDao interetDao;

    public InteretServiceImpl(InteretDao interetDao) {
        this.interetDao = interetDao;
    }

    @Override
    public Interet ajouterInteret(String nom) {
        return interetDao.save(new Interet(nom));
    }

    @Override
    public List<Interet> recupererInterets() {
        return interetDao.findAll();
    }

    @Override
    public List<Interet> recupererInterets(String nom) {
        return interetDao.findByNomContaining(nom);
    }

    @Override
    public Interet recupererInteret(String nom) {
        return interetDao.findByNom(nom);
    }


}
