package fr.humanbooster.fx.katchaka.service.impl;

import fr.humanbooster.fx.katchaka.business.Genre;
import fr.humanbooster.fx.katchaka.dao.GenreDao;
import fr.humanbooster.fx.katchaka.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre ajouterGenre(String nom) {
        return genreDao.save(new Genre(nom));
    }

    @Override
    public List<Genre> recupererGenres() {
        return genreDao.findAll();
    }

    @Override
    public Genre recupererGenre(String nom) {
        return genreDao.findByNom(nom);
    }


}
