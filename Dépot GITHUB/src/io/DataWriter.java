package io;

import Model.Etudiant;
import java.util.List;

public interface DataWriter {
    void ecrire(String fichier, List<Etudiant> etudiants);
}