## 1. Modélisation du système

### Objectif

Réaliser la conception complète du système avant l’implémentation, en définissant les entités métier, l’organisation en packages et les relations entre composants.

---

### Travaux réalisés

- Conception de l’architecture globale du projet
- Définition de l’organisation en packages :
  - `model`
  - `io`
  - `mapper`
  - `repository`
  - `service`
  - `validation`

- Définition des responsabilités de chaque couche

- Conception des entités métier :
  - `Etudiant`
  - `Note`
  - `Matiere`

- Introduction d’un référentiel central :
  - `MatiereRepository` (gestion des matières et coefficients)

---

### Définition des responsabilités des classes

#### Model

- **Etudiant**
  - Représente un étudiant (id, nom, notes)
  - Contient les résultats calculés :
    - moyenne
    - mention
  - Ne contient aucune logique de calcul métier

- **Note**
  - Représente une note associée à une matière
  - Relie une valeur à un objet `Matiere`

- **Matiere**
  - Représente une matière avec son coefficient

---

#### Repository

- **MatiereRepository**
  - Source unique des matières disponibles
  - Fournit :
    - accès aux matières
    - vérification d’existence
  - Utilisé par :
    - `CSVValidator`
    - `EtudiantMapper`

---

#### Validation

- **CSVValidator**
  - Valide la structure du fichier CSV
  - Vérifie :
    - présence de `id` et `nom`
    - validité des matières via `MatiereRepository`

---

#### Mapper

- **EtudiantMapper**
  - Transforme une ligne CSV en objet `Etudiant`
  - Crée les objets `Note`
  - Associe chaque note à une `Matiere`

---

#### IO

- **DataReader (interface)**
  - Contrat de lecture des données

- **CSVReader**
  - Lit le fichier CSV
  - Délègue :
    - validation → `CSVValidator`
    - transformation → `EtudiantMapper`
  - Retourne une liste d’`Etudiant`

---

#### Service

- **MoyenneService**
  - Calcule la moyenne pondérée d’un étudiant

- **MentionService**
  - Attribue une mention selon la moyenne

- **GestionNotes**
  - Trie les étudiants par moyenne décroissante

---

### Flux global du système

1. Lecture du fichier CSV (`CSVReader`)
2. Validation du format (`CSVValidator`)
3. Transformation des données (`EtudiantMapper`)
4. Construction des objets métier (`Etudiant`, `Note`, `Matiere`)
5. Calcul de la moyenne (`MoyenneService`)
6. Attribution de la mention (`MentionService`)
7. Tri des étudiants (`GestionNotes`)

---

### Lien avec les principes SOLID

- **SRP (Single Responsibility Principle)**  
  Chaque classe a un rôle unique (lecture, validation, mapping, calcul, tri, modèle)

- **OCP (Open/Closed Principle)**  
  Le système est extensible (ajout possible de nouveaux formats via `DataReader`)

- **DIP (Dependency Inversion Principle)**  
  Utilisation d’interfaces (`DataReader`) et d’un repository central (`MatiereRepository`)

- **ISP (Interface Segregation Principle)**  
  Interfaces séparées selon les besoins (lecture uniquement)

- **LSP (Liskov Substitution Principle)**  
  Remplacement possible de `CSVReader` par une autre implémentation sans modifier le reste du système

---

### Rôle de la modélisation

- Structurer le système avant implémentation
- Définir clairement les responsabilités
- Éviter les dépendances directes inutiles
- Garantir une architecture évolutive
- Préparer une base solide pour l’application des principes SOLID

---

### Rôle de MatiereRepository

- Centralise les matières et leurs coefficients
- Sert de référence unique pour le système
- Utilisé par la validation et le mapping
- Évite la duplication de logique métier
- Garantit la cohérence des données