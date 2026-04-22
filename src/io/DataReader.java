package io;

import Model.Etudiant;
import java.util.List;

public interface DataReader {
    List<Etudiant> lire(String fichier);
}