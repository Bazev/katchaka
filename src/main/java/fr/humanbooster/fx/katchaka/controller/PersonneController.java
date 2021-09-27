package fr.humanbooster.fx.katchaka.controller;


import fr.humanbooster.fx.katchaka.business.Personne;
import fr.humanbooster.fx.katchaka.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Controller
public class PersonneController {

    private PersonneService personneService;
    private VilleService villeService;
    private GenreService genreService;
    private StatutService statutService;
    private InteretService interetService;
    private final HttpSession httpSession;

    private static final String DOSSIER_IMAGES = "src/main/webapp/images/";

    public PersonneController(PersonneService personneService, VilleService villeService, GenreService genreService, StatutService statutService, InteretService interetService, HttpSession httpSession) {
        this.personneService = personneService;
        this.villeService=villeService;
        this.genreService=genreService;
        this.statutService=statutService;
        this.interetService=interetService;
        this.httpSession = httpSession;
    }

    @GetMapping("personnes")
    public ModelAndView personnesGet(@RequestParam(name = "FILTRE", defaultValue = "", required = false)String filtre,
                                     @PageableDefault(size = 10, page = 0, sort = "pseudo") Pageable pageable){
        ModelAndView mav = new ModelAndView("personnes");
        Page<Personne> pageDePersonnes = null;
        Personne personneEnSession = (Personne) httpSession.getAttribute("personne");
        if (personneEnSession == null) {
                pageDePersonnes = personneService.recupererPersonnes(filtre, pageable);
        }else {
            pageDePersonnes = personneService.recupererPersonnes(personneEnSession, filtre, pageable);
        }
        //pagesDePersonnes.getSort().getOrder();
        mav.addObject("pageDePersonnes", pageDePersonnes);
        mav.addObject("filtre", filtre);
        return mav;
    }

    @GetMapping("personne")
    public ModelAndView personneGet(@RequestParam(name = "ID", required = false)Long id) {
        ModelAndView mav = new ModelAndView("personne");
            Personne personne = null;
            if (id ==null) {
                personne = new Personne();
            }else {
               personne = personneService.recupererPersonne(id);
            }
            mav.addObject("personne", personne);
            mav.addObject("villes", villeService.recupererVilles());
            mav.addObject("genres", genreService.recupererGenres());
            mav.addObject("statuts", statutService.recupererStatuts());
            mav.addObject("interets", interetService.recupererInterets());
            return mav;
    }

    @PostMapping("personne")
    public ModelAndView personnePost(@Valid @ModelAttribute Personne personne, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mav = personneGet(personne.getId());
            mav.addObject("personne" , personne);
            return mav;
        }else {
            personneService.enregistrerPersonne(personne);
            return new ModelAndView("redirect:personnes");
        }
    }

    @GetMapping("delete")
    public  ModelAndView supprimerPersonneGet(@RequestParam(name = "ID")Long id) {
        personneService.supprimerPersonne(id);
        return new ModelAndView("redirect:personnes");
    }

  /*  @GetMapping("televersementImage")
    public ModelAndView utilisateurImageGet(@RequestParam(name = "ID", required = false)Long id) {
        Personne personne = (Personne) httpSession.getAttribute("personne");
        if (personne == null) {
            System.out.println(new Date() + " : Il n'y a pas de personne en session");
            return new ModelAndView("redirect;accueil");
        }
        personne = personneService.recupererPersonne(id);
        ModelAndView mav = new ModelAndView("televersementImage");
        mav.addObject("personne", personne);
        return mav;
    }*/

    /*@PostMapping("televersementImage")
    public ModelAndView televersementImagePost(@RequestParam(name = "ID")Long id, @RequestParam(name = "FICHIER")MultipartFile multipartFile) throws IOException {
        Personne personne = (Personne) httpSession.getAttribute("personne");
        if (personne == null) {
            System.out.println(new Date() + " : Pas de session en cours");
            return new ModelAndView("rdirect:accueil");
        }
        personne = personneService.recupererPersonne(id);

        if (personne!=null ) {
            enregisterFichier(String.valueOf(id), multipartFile);
        }
        return new ModelAndView("redirect:tableauDeBord");
    }*/

    private static void enregisterFichier(String nom, MultipartFile multipartFile) throws IOException {
        Path chemin = Paths.get(DOSSIER_IMAGES);

        if (!Files.exists(chemin)) {
            Files.createDirectories(chemin);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path cheminFichier = chemin.resolve(nom);
            Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Erreur d'écriture : " + nom, ioe);
        }


    }

}
