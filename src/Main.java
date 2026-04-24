import io.CSVReader;
import io.DataReader;

import mapper.EtudiantMapper;
import validation.CSVValidator;
import repository.MatiereRepository;

import Model.Etudiant;

import service.MoyenneService;
import service.MentionService;
import service.GestionNotes;

import java.util.List;

/**
 * Point d'entrée du programme.
 *
 * Rôle :
 * - Orchestrer l'ensemble du flux de traitement
 * - Instancier les dépendances
 * - Exécuter les étapes dans l'ordre :
 *   lecture → calcul → tri → affichage
 *
 * Important :
 * Cette classe ne contient aucune logique métier,
 * uniquement de l'orchestration (respect SRP)
 */
public class Main {

    public static void main(String[] args) {

        // Fichier d'entrée CSV contenant les données des étudiants
        String fichierEntree = "notes.csv";

        // =========================
        // 1. INFRASTRUCTURE
        // =========================

        // Repository des matières (source de vérité des coefficients)
        MatiereRepository repo = new MatiereRepository();

        // Validation du fichier CSV (structure + cohérence)
        CSVValidator validator = new CSVValidator(repo);

        // Mapping des données CSV vers objets Etudiant
        EtudiantMapper mapper = new EtudiantMapper(repo);

        // Lecteur de données (abstraction via interface DataReader)
        DataReader reader = new CSVReader(validator, mapper);

        // =========================
        // 2. LECTURE DES DONNÉES
        // =========================

        List<Etudiant> etudiants = reader.lire(fichierEntree);

        // =========================
        // 3. TRAITEMENT MÉTIER
        // =========================

        // Service de calcul de moyenne
        MoyenneService moyenneService = new MoyenneService();

        // Service d'attribution des mentions
        MentionService mentionService = new MentionService();

        // Calcul des résultats pour chaque étudiant
        for (Etudiant e : etudiants) {

            // Calcul de la moyenne
            e.setMoyenne(moyenneService.calculer(e));

            // Attribution de la mention
            e.setMention(mentionService.attribuer(e.getMoyenne()));
        }

        // =========================
        // 4. TRI DES RÉSULTATS
        // =========================

        GestionNotes gestion = new GestionNotes();
        gestion.trierEtudiants(etudiants);

        // =========================
        // 5. FIN DU PROGRAMME
        // =========================

        System.out.println("Traitement terminé !");
    }
}