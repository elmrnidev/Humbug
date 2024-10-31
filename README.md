
# 🎮 Humbug - Jeu de Puzzle en Java (2020)

## 📅 Contexte
Projet développé en 2020 dans le cadre de mes études en développement informatique a l'HE2B-ESI

## 📝 Description
Humbug est un jeu de réflexion inspiré du célèbre puzzle game où le joueur doit déplacer stratégiquement différents insectes sur un plateau pour atteindre des objectifs. Développé en Java, ce projet met en œuvre une architecture MVC robuste et une interface en ligne de commande interactive.

## ⌛ Durée du Projet
- Développement : mai 2020
- Temps de réalisation : ~2 mois

## 🎮 Fonctionnalités

- Interface en ligne de commande avec couleurs
- 48 niveaux progressifs + 1 niveau bonus
- 6 types d'insectes différents avec des mouvements uniques :
  - Araignée (Spider)
  - Escargot (Snail)
  - Bourdon (Bumblebee)
  - Sauterelle (Grasshopper)
  - Papillon (Butterfly)
  - Coccinelle (Ladybird)
- Gestion des murs et des limites du plateau
- Actions disponibles : Move, Restart, Help, Quit, Undo

## 🛠 Technologies Utilisées
- Java 11
- Maven
- JUnit 5 pour les tests
- Jackson pour la gestion des fichiers JSON
- Pattern MVC (Model-View-Controller)

## 📋 Prérequis

- Java 11 ou supérieur
- Maven

## 🚀 Installation

```bash
git clone https://github.com/[votre-username]/humbug.git
cd humbug
mvn clean install
```

## 🎯 Comment Jouer

1. Lancez le jeu via la commande : `java -jar target/Humbug-1.0.jar`
2. Choisissez un niveau (1-48 ou 100 pour le niveau bonus)
3. Utilisez les commandes suivantes :
   - M : Déplacer un insecte
   - R : Recommencer le niveau
   - H : Afficher l'aide
   - Q : Quitter le jeu
   - U : Annuler le dernier mouvement

## 🎨 Structure du Projet

```
src/
├── main/
│   └── java/
│       └── g52103/
│           └── humbug/
│               ├── controller/
│               ├── model/
│               ├── view/
│               └── util/
└── test/
    └── java/
        └── g52103/
            └── humbug/
                └── model/
```
