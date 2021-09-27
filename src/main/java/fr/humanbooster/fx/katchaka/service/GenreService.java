package fr.humanbooster.fx.katchaka.service;

import fr.humanbooster.fx.katchaka.business.Genre;

import java.util.List;

public interface GenreService {

    Genre ajouterGenre(String nom);
    List<Genre> recupererGenres();

    Genre recupererGenre(String nom);
}
