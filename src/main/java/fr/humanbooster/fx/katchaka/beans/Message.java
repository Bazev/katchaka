package fr.humanbooster.fx.katchaka.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Message {

    private List<String> destinataires;
    private String contenu;
    private String priorite;
    private boolean autoriseReponse;

    private static final String PRIORITE_PAR_DEFAUT = "high";
    private static final boolean AUTORISE_REPONSE_PAR_DEFAUT = true;

    public Message() {
        priorite = PRIORITE_PAR_DEFAUT;
        autoriseReponse = AUTORISE_REPONSE_PAR_DEFAUT;
        destinataires = new ArrayList<>();
    }

    @JsonProperty("receivers")
    public List<String> getDestinataires() {
        return destinataires;
    }

    public void setDestinataires(List<String> destinataires) {
        this.destinataires = destinataires;
    }

    @JsonProperty("message")
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @JsonProperty("priority")
    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    @JsonProperty("senderForResponse")
    public boolean isAutoriseReponse() {
        return autoriseReponse;
    }

    public void setReponsePossible(boolean autoriseReponse) {
        this.autoriseReponse = autoriseReponse;
    }

    @Override
    public String toString() {
        return "Message [contenu=" + contenu + ", priorite=" + priorite + ", autoriseReponse=" + autoriseReponse + "]";
    }
}
