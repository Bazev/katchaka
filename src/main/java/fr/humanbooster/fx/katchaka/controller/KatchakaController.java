package fr.humanbooster.fx.katchaka.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import fr.humanbooster.fx.katchaka.business.Interet;
import fr.humanbooster.fx.katchaka.business.Invitation;
import fr.humanbooster.fx.katchaka.business.Personne;
import fr.humanbooster.fx.katchaka.business.Ville;
import fr.humanbooster.fx.katchaka.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// On indique à Spring que cette classe fait partie de la couche contrôleur
@Controller
public class KatchakaController {

	// Le contrôleur a besoin de services
	// autrement dit il délègue des traitements à un ou plusieurs services
	private final VilleService villeService;
	private final StatutService statutService;
	private final InteretService interetService;
	private final GenreService genreService;
	private final PersonneService personneService;
	private final HttpSession httpSession;
	private final InvitationService invitationService;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar;

	// Ce constructeur va provoquer l'injection de dépendances
	public KatchakaController(VilleService villeService, StatutService statutService, InteretService interetService, GenreService genreService, PersonneService personneService, HttpSession httpSession, InvitationService invitationService) {
		this.villeService = villeService;
		this.statutService = statutService;
		this.interetService = interetService;
		this.genreService = genreService;
		this.personneService = personneService;
		this.httpSession = httpSession;
		this.invitationService = invitationService;
	}

	@GetMapping({"/", "index", "connexion"}) //Sur cette ligne on précise des URL
	public ModelAndView accueil() {
		// On crée un objet de type ModelAndView qui est directement renvoyé par la méthode
		return new ModelAndView("index"); // Sur cette ligne on précise le nom d'une vue
	}

	@PostMapping("connexion")
	public ModelAndView connexionPost(@RequestParam(name = "email")String email,
									  @RequestParam(name = "motDePasse")String motDePasse) {
		Personne personne = personneService.recupererPersonne(email, motDePasse);
		if (personne == null){
			return accueil();
		}else {
			httpSession.setAttribute("personne", personne);
			return tableauDeBordGet();
		}
	}

	@GetMapping("tableauDeBord")
	public ModelAndView tableauDeBordGet(){
		ModelAndView mav = new ModelAndView("tableauDeBord");
		Personne personne = (Personne) httpSession.getAttribute("personne");
		if (personne==null) {
			return accueil();
		}
		mav.addObject("invitationsRecues", invitationService.recupererInvitationsRecuesSansReponse(personne));
		mav.addObject("invitationsEnvoyees", invitationService.recupererInvitationsEnvoyeesSansReponse(personne));
		return mav;

	}

	@GetMapping("deconnexion")
	public ModelAndView deconnexionPost() {
		httpSession.removeAttribute("personne");
		return accueil();
	}

	@GetMapping("reponseInvitation")
	public ModelAndView reponseInvitationGet(@RequestParam(name = "ID")Long idInvitation, @RequestParam("EST_ACCEPTE") boolean estAcceptee) {
		System.out.println(new Date() + "Nouvelle requete HTTP de type GET sur l'url reponseInvitation, parametre estAccepteee" );
		Personne destinataire = (Personne) httpSession.getAttribute("personne");
		Invitation invitation = invitationService.recupererInvitation(destinataire, idInvitation);
		if (invitation == null) {
			invitationService.mettreAJourInvitation(invitation, estAcceptee);
			return new ModelAndView("redirect:tableauDeBord");
		}else {
			return accueil();
		}
	}

	// Cette méthode serva invoquée dès que Spring a injecté tous les objets
	@PostConstruct
	private void init() {

		if (villeService.recupererVilles().isEmpty()) {
			villeService.ajouterVille("Lyon");
			villeService.ajouterVille("Macon");
			villeService.ajouterVille("Grenoble");
			villeService.ajouterVille("Annecy");
			villeService.ajouterVille("Melun");
			villeService.ajouterVille("Autun");
			villeService.ajouterVille("Paris");
		}
		if (statutService.recupererStatuts().isEmpty()) {
			statutService.ajouterStatut("Veuf");
			statutService.ajouterStatut("Célibataire");
			statutService.ajouterStatut("Séparé");
			statutService.ajouterStatut("Divorcé");
		}
		if (interetService.recupererInterets().isEmpty()){
			interetService.ajouterInteret("Moto GP");
			interetService.ajouterInteret("Formule 1");
			interetService.ajouterInteret("Manga");
			interetService.ajouterInteret("Escalade");
			interetService.ajouterInteret("Guitar éléectrique");
			interetService.ajouterInteret("Musculation");
			interetService.ajouterInteret("Course");
			interetService.ajouterInteret("Boxe thailandaise");
			interetService.ajouterInteret("Histoire");
			interetService.ajouterInteret("Oenologie");
			interetService.ajouterInteret("Jeux vidéo");
			interetService.ajouterInteret("Poney");
			interetService.ajouterInteret("Jardinage");
			interetService.ajouterInteret("Astro-physique");
		}
		if (genreService.recupererGenres().isEmpty()) {
			genreService.ajouterGenre("Homme");
			genreService.ajouterGenre("Femme");
		}
		if (personneService.recupererPersonnes().isEmpty()) {
			Personne evan = new Personne();
			evan.setPseudo("evan");
			evan.setGenre(genreService.recupererGenre("Homme"));
			evan.setGenreRecherche(genreService.recupererGenre("Femme"));
			evan.setVille(villeService.recupererVilleParNom("Macon"));
			evan.setStatut(statutService.recupererStatut("Célibataire"));
			List<Interet> interetsEvan = new ArrayList<>();
			interetsEvan.add(interetService.recupererInteret("Moto GP"));
			interetsEvan.add(interetService.recupererInteret("Moto GP"));
			interetsEvan.add(interetService.recupererInteret("Jeux vidéo"));
			interetsEvan.add(interetService.recupererInteret("Jardinage"));
			evan.setInterets(interetsEvan);
			try {
				Date dEvan = format.parse("1987-09-26");
				evan.setDateDeNaissance(dEvan);
			}
			catch (Exception e){

			}
			personneService.enregistrerPersonne(evan);

			Personne lucien = new Personne();
			lucien.setPseudo("lucien");
			lucien.setGenre(genreService.recupererGenre("Homme"));
			lucien.setGenreRecherche(genreService.recupererGenre("Femme"));
			lucien.setVille(villeService.recupererVilleParNom("Grenoble"));
			lucien.setStatut(statutService.recupererStatut("Célibataire"));
			List<Interet> interetsLucien = new ArrayList<>();
			interetsLucien.add(interetService.recupererInteret("Escalade"));
			interetsLucien.add(interetService.recupererInteret("Moto GP"));
			lucien.setInterets(interetsLucien);
			try {
				Date dLucien = format.parse("1976-01-25");
				lucien.setDateDeNaissance(dLucien);
			}
			catch (Exception e){
			}
			personneService.enregistrerPersonne(lucien);

			Personne lucile = new Personne();
			lucile.setPseudo("Lulu");
			lucile.setGenre(genreService.recupererGenre("Femme"));
			lucile.setGenreRecherche(genreService.recupererGenre("Homme"));
			lucile.setVille(villeService.recupererVilleParNom("Macon"));
			lucile.setStatut(statutService.recupererStatut("Célibataire"));
			List<Interet> interetListLucile = new ArrayList<>();
			interetListLucile.add(interetService.recupererInteret("Formule 1"));
			interetListLucile.add(interetService.recupererInteret("Escalade"));
			lucile.setInterets(interetListLucile);
			try {
				Date dLucile = format.parse("1988-01-01");
				lucile.setDateDeNaissance(dLucile);
			}
			catch (Exception e) {
			}
			personneService.enregistrerPersonne(lucile);



			Personne tomate = new Personne();
			tomate.setPseudo("tomate");
			tomate.setGenre(genreService.recupererGenre("Femme"));
			tomate.setGenreRecherche(genreService.recupererGenre("Homme"));
			tomate.setVille(villeService.recupererVilleParNom("Macon"));
			tomate.setStatut(statutService.recupererStatut("Célibataire"));
			List<Interet> interetsTomate = new ArrayList<>();
			interetsTomate.add(interetService.recupererInteret("Musculation"));
			tomate.setInterets(interetsTomate);
			try {
				Date dTomate = format.parse("1991-05-05");
				tomate.setDateDeNaissance(dTomate);
			}
			catch (Exception e){

			}
			personneService.enregistrerPersonne(tomate);
		}
		List<Personne> femmesCelibatairesDeMacon = personneService.recupererPersonnes(
				villeService.recupererVilleParNom("Macon"),
				genreService.recupererGenre("Femme"),
				statutService.recupererStatut("Célibataire"));
		System.out.println("Femmes Célibataires de Macon : "+femmesCelibatairesDeMacon);


		// Dates pour les quadra
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -49);
		Date dateDebut = calendar.getTime();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -40);
		Date dateFin = calendar.getTime();
		List<Ville> villes = new ArrayList<>();
		villes.add(villeService.recupererVille("Paris"));
		villes.add(villeService.recupererVille("Grenoble"));

		List<Personne> hommesQuadragenairesCelibatairesDeParisOuGrenobleAimantEscalade = personneService.recupererPersonnesQuadra(
				villes,
				genreService.recupererGenre("Homme"),
				statutService.recupererStatut("Célibataire"),
				dateDebut,
				dateFin,
				interetService.recupererInteret("Escalade"));

		System.out.println("Hommes Quadra Célibataires de Paris ou Grenoble aimant l'escalade : "+hommesQuadragenairesCelibatairesDeParisOuGrenobleAimantEscalade);

		// lister les personnes triées sur leur nombre d'intérets décroissant :
		List<Personne> PersonnesTriesNombreInteretsDecroissant = personneService.recupererPersonnesParNombreInteret();
		System.out.println("Personnes triées sur leur nombre d'intérets décroissant : "+PersonnesTriesNombreInteretsDecroissant);

		//ajouter une méthode annotée @Query permettant d'obtenir tous les habitants d'une ville donnée en paramètre
		List<Ville> villesEnParam = new ArrayList<>();
		villesEnParam.add(villeService.recupererVille("Grenoble"));
		List<Personne> PersonnesVilleParam = personneService.recupererPersonnesParVille(
				villesEnParam);
		System.out.println("Personnes de Grenoble : "+PersonnesVilleParam);

		for (int i = 1; i <= 100; i++) {
			Personne joaquim = new Personne();
			joaquim.setPseudo("Joaquim" + i);
			try {
				Date djoaqim = format.parse("1991-05-05");
				joaquim.setDateDeNaissance(djoaqim);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			joaquim.setGenre(genreService.recupererGenre("Homme"));
			joaquim.setGenreRecherche(genreService.recupererGenre("Femme"));
			joaquim.setVille(villeService.recupererVille("Paris"));
			joaquim.setStatut(statutService.recupererStatut("Célibataire"));
			List<Interet> interetsJoaquim = new ArrayList<>();
			interetsJoaquim.add(interetService.recupererInteret("Escalade"));
			interetsJoaquim.add(interetService.recupererInteret("Manga"));
			joaquim.setInterets(interetsJoaquim);
			personneService.enregistrerPersonne(joaquim);
		}
	}


}
