[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/5o1RIPRF)
# 📱 TD : Application Mobile de Gestion de Tâches

## 🎯 Objectif du TD

L’objectif de ce TD est de concevoir et développer une application mobile permettant de gérer des **tâches** (to-do list) à l’aide des principes de base du développement mobile.  
Chaque étudiant devra proposer une application fonctionnelle avec une interface utilisateur claire et fluide.

---

## 🧩 Description du projet

L’application doit permettre à l’utilisateur de **créer**, **visualiser**, **modifier** et **supprimer** des tâches.  
Chaque tâche est définie par les attributs suivants :

| Champ | Type | Description |
|-------|------|--------------|
| **libellé** | `String` | Titre court de la tâche |
| **description** | `String` | Détails supplémentaires sur la tâche |
| **statut** | `Enum` ou `String` | Exemple : `À faire`, `En cours`, `Terminée` |
| **type** | `String` | Exemple : `Personnel`, `Travail`, `Étude`, etc. |
| **date de création** | `Date` | Générée automatiquement à la création |
| **date de mise à jour** | `Date` | Actualisée lors de chaque modification |
| **date de rendu** | `Date` | Date limite fixée par l’utilisateur |

---

## 🧭 Fonctionnalités attendues

L’application doit comporter les fonctionnalités suivantes :

### 1. 📋 Lister les tâches
- Afficher la liste des tâches existantes  
- Possibilité de filtrer ou trier (optionnel : par statut, date de rendu…)

### 2. ➕ Créer une tâche
- Formulaire de création (libellé, description, type, date de rendu, etc.)  
- Le statut par défaut peut être "À faire"  
- La date de création est ajoutée automatiquement  

### 3. 🗑️ Supprimer une tâche
- Permettre de supprimer une tâche depuis la liste ou les détails  

### 4. ✏️ Modifier une tâche
- Permettre la mise à jour du libellé, de la description, du type, du statut et de la date de rendu  
- Mettre à jour automatiquement la **date de mise à jour**

### 5. 🔍 Afficher les détails d’une tâche
- Écran affichant toutes les informations d’une tâche  
- Accès depuis la liste en cliquant sur un élément  

### 6. 🔄 Changer le statut ou la date de rendu
- L’utilisateur doit pouvoir facilement changer le **statut** ou la **date de rendu** depuis la vue détaillée ou la liste  

---

## 💡 Suggestions (Optionnelles)
- Ajouter une **barre de recherche**  
- Permettre le **tri ou filtrage** (par type, statut ou date limite)  
- Ajouter un **thème clair/sombre**  

---

## 🎨 Interface attendue

L’interface doit être :
- Simple et intuitive  
- Cohérente entre les différents écrans  
- Réalisée avec **Jetpack Compose**

### Écrans minimum à prévoir :
1. **Liste des tâches**
2. **Formulaire de création / modification**
3. **Vue détaillée d’une tâche**

---

## ⚙️ Contraintes techniques

- Langage : **Kotlin**
- UI : **Jetpack Compose**
- Stockage : Mémoiez locale
- Respecter les bonnes pratiques de nommage et de structure de code


---
