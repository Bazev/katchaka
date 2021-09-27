package fr.humanbooster.fx.katchaka.business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Invitation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLecture;
    private Boolean estAccepte;
    @ManyToOne
    private Personne expediteur;
    @ManyToOne
    private Personne destinataire;
    @OneToOne
    private VieCommune vieCommune;

    public Invitation() {

    }
    public Invitation(Long id, Date dateEnvoi, Date dateLecture, boolean estAccepte, Personne expediteur, Personne destinataire, VieCommune vieCommune) {
        this.id = id;
        this.dateEnvoi = dateEnvoi;
        this.dateLecture = dateLecture;
        this.estAccepte = estAccepte;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.vieCommune = vieCommune;
    }

    public Invitation(Personne expediteur, Personne destinataire) {
        this();
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        expediteur.getInvitationsEnvoyees().add(this);
        destinataire.getInvitationsRecues().add(this);
    }


    public Boolean getEstAccepte() {
        return estAccepte;
    }

    public void setEstAccepte(Boolean estAccepte) {
        this.estAccepte = estAccepte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Date getDateLecture() {
        return dateLecture;
    }

    public void setDateLecture(Date dateLecture) {
        this.dateLecture = dateLecture;
    }

    public Personne getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Personne expediteur) {
        this.expediteur = expediteur;
    }

    public Personne getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Personne destinataire) {
        this.destinataire = destinataire;
    }

    public VieCommune getVieCommune() {
        return vieCommune;
    }

    public void setVieCommune(VieCommune vieCommune) {
        this.vieCommune = vieCommune;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", dateEnvoi=" + dateEnvoi +
                ", dateLecture=" + dateLecture +
                ", estAccepte=" + estAccepte +
                ", expediteur=" + expediteur +
                ", destinataire=" + destinataire +
                ", vieCommune=" + vieCommune +
                '}';
    }
}