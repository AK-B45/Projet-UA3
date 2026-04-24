import Model.Etudiant;
import io.CSVReader;
import io.DataReader;
import mapper.EtudiantMapper;
import repository.MatiereRepository;
import service.GestionNotes;
import service.MentionService;
import service.MoyenneService;
import validation.CSVValidator;

/**
 * Point d’entrée du programme.
 * Orchestre tout le traitement des étudiants.
 */
void main() {

    String fichierEntree = "notes.csv";

    // Infrastructure
    MatiereRepository repo = new MatiereRepository();
    CSVValidator validator = new CSVValidator(repo);
    EtudiantMapper mapper = new EtudiantMapper(repo);
    DataReader reader = new CSVReader(validator, mapper);

    // Lecture
    List<Etudiant> etudiants = reader.lire(fichierEntree);

    // Services métier
    MoyenneService moyenneService = new MoyenneService();
    MentionService mentionService = new MentionService();

    for (Etudiant e : etudiants) {
        e.setMoyenne(moyenneService.calculer(e));
        e.setMention(mentionService.attribuer(e.getMoyenne()));
    }

    // Tri
    GestionNotes gestion = new GestionNotes();
    gestion.trierEtudiants(etudiants);

    IO.println("Traitement terminé !");
}