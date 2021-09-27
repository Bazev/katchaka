package fr.humanbooster.fx.katchaka.service;

import java.util.List;

import fr.humanbooster.fx.katchaka.business.Ville;

public interface VilleService {

	Ville ajouterVille(String nom);
	
	List<Ville> recupererVilles();

	List<Ville> recupererVilles(String nom);

    Ville recupererVille(String nom);

	Ville recupererVilleParNom(String nom);

	Ville recupererVille(Long id);

	Ville enregistrerVille(Ville ville);

	boolean supprimerVille(Long id);
}
