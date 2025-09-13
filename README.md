MedPath Backend

MedPath Backend est une application Spring Boot conçue pour automatiser et simplifier le flux de travail médical. Elle vise à connecter médecins, laboratoires et coursiers pour gérer le processus de soins, depuis la consultation jusqu’à la livraison des résultats d’analyse aux patients.

Fonctionnalités principales

Gestion des patients, médecins et laboratoires.

Suivi des consultations et ordonnances.

Gestion des analyses médicales, y compris la planification et la livraison des résultats.

Notifications automatisées pour les patients et médecins.

Gestion des coursiers pour la livraison des résultats d’analyse.

Flux de travail complet pour le processus de soins médical et analytique.

Technologies utilisées

Backend : Spring Boot

Base de données : MongoDB

Sécurité : JWT Authentication

API REST : Spring Web

ORM : Spring Data MongoDB

Autres outils : Lombok, Spring Boot Test

Architecture

L’application est construite avec une architecture RESTful et suit les principes SOLID pour un code maintenable et évolutif. Elle est composée de :

Controllers : exposent les endpoints REST

Services : logique métier

Repositories : accès aux données

Models/Entities : structures de données pour les patients, médecins, analyses, coursiers, etc.
