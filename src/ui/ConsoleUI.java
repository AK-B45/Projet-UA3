package ui;

import Model.Etudiant;
import Model.Note;
import service.MoyenneService;
import service.MentionService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private final List<Etudiant> etudiants;
    private final MoyenneService moyenneService;
    private final MentionService mentionService;

    public ConsoleUI(List<Etudiant> etudiants,
                     MoyenneService moyenneService,
                     MentionService mentionService) {
        this.etudiants = etudiants;
        this.moyenneService = moyenneService;
        this.mentionService = mentionService;
    }

    public void demarrer() {

        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = sc.nextInt();

            switch (choix) {
                case 1 -> afficherTous();
                case 2 -> afficherEtudiant(sc);
                case 3 -> modifierNote(sc);
                case 0 -> System.out.println("Fin.");
                default -> System.out.println("Choix invalide");
            }

        } while (choix != 0);
    }

    private void afficherMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Afficher tous les étudiants");
        System.out.println("2. Détail d’un étudiant");
        System.out.println("3. Modifier une note");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }

    // AFFICHAGE COMPLET AVEC NOTES + FORMAT
    private void afficherTous() {

        System.out.println("\n===== LISTE DES ETUDIANTS =====");

        for (Etudiant e : etudiants) {

            StringBuilder notesStr = new StringBuilder();

            for (Note n : e.getNotes()) {
                notesStr.append(n.getMatiere().getNom())
                        .append(":")
                        .append(formatDouble(n.getValeur()))
                        .append("  ");
            }

            System.out.println(
                    e.getId() + " | " +
                            e.getNom() + " | " +
                            notesStr +
                            "| Moyenne: " + formatDouble(e.getMoyenne()) +
                            " | Mention: " + e.getMention()
            );
        }
    }

    private void afficherEtudiant(Scanner sc) {

        System.out.print("ID étudiant : ");
        int id = sc.nextInt();

        Optional<Etudiant> opt = etudiants.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        if (opt.isEmpty()) {
            System.out.println("Étudiant introuvable.");
            return;
        }

        Etudiant e = opt.get();

        System.out.println("\n==============================");
        System.out.println("ID      : " + e.getId());
        System.out.println("Nom     : " + e.getNom());
        System.out.println("Notes   :");

        for (Note n : e.getNotes()) {
            System.out.println("  - " +
                    n.getMatiere().getNom() +
                    " : " +
                    formatDouble(n.getValeur()));
        }

        System.out.println("Moyenne : " + formatDouble(e.getMoyenne()));
        System.out.println("Mention : " + e.getMention());
        System.out.println("==============================");
    }

    //  MODIFICATION + RECALCUL
    private void modifierNote(Scanner sc) {

        System.out.print("ID étudiant : ");
        int id = sc.nextInt();

        Optional<Etudiant> opt = etudiants.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        if (opt.isEmpty()) {
            System.out.println("Étudiant introuvable.");
            return;
        }

        Etudiant e = opt.get();

        System.out.print("Nom matière : ");
        String matiere = sc.next();

        Note noteTrouvee = null;

        for (Note n : e.getNotes()) {
            if (n.getMatiere().getNom().equalsIgnoreCase(matiere)) {
                noteTrouvee = n;
                break;
            }
        }

        if (noteTrouvee == null) {
            System.out.println("Matière introuvable.");
            return;
        }

        double valeur;

        while (true) {
            System.out.print("Nouvelle note (0 - 20) : ");

            if (!sc.hasNextDouble()) {
                System.out.println("Entrée invalide (nombre requis).");
                sc.next(); // nettoyer
                continue;
            }

            valeur = sc.nextDouble();

            if (valeur < 0 || valeur > 20) {
                System.out.println("La note doit être entre 0 et 20.");
                continue;
            }

            break;
        }

        noteTrouvee.setValeur(valeur);

        // recalcul
        e.setMoyenne(moyenneService.calculer(e));
        e.setMention(mentionService.attribuer(e.getMoyenne()));

        System.out.println("Note mise à jour.");
    }
    private String formatDouble(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }
}