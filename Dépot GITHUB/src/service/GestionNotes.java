package service;

import Model.Etudiant;

import java.util.Comparator;
import java.util.List;

public class GestionNotes {

    public void trierEtudiants(List<Etudiant> etudiants) {
        etudiants.sort(Comparator.comparingDouble(Etudiant::getMoyenne).reversed());
    }
}