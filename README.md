# TP Spring Security : OAuth2 Client & OpenID Connect

Ce projet démontre l'implémentation d'une authentification déléguée dans une application Spring Boot
en utilisant le protocole OAuth2 et la norme OpenID Connect (OIDC).

L'application ne gère pas les mots de passe des utilisateurs elle-même. Elle délègue l'authentification
à Google, qui agit en tant que fournisseur d'identité (Identity Provider). Une fois l'utilisateur authentifié, 
Google délivre un ID Token contenant les informations de profil (nom, email, photo) que l'application affiche.

## 1. Page d'accueil: 

Au démarrage, l'application est accessible sur http://localhost:8081. L'utilisateur voit une page d'accueil publique.
Aucune authentification n'est requise pour accéder à cette vue.

![WhatsApp Image 2026-03-29 at 12 55 17](https://github.com/user-attachments/assets/580ec46a-8f65-4bf7-9cca-3bd87a82f9c6)

Cette page contient un bouton d'action pour tenter d'accéder à une ressource protégée.

2. Redirection vers le fournisseur (Google)
Lorsque l'utilisateur clique sur le bouton "Accéder à mon profil protégé", Spring Security intercepte la requête. Comme
l'utilisateur n'est pas connecté, il redirige automatiquement vers la page de connexion de Google.

L'utilisateur reconnaît l'application "OAuth - client" et sélectionne son compte Google pour s'authentifier.

### Choix du compte Google:

![WhatsApp Image 2026-03-29 at 12 55 37](https://github.com/user-attachments/assets/67e08e1c-690b-403c-ba0d-689bf438f4a8)

### Google demande alors la confirmation de l'autorisation d'accès aux informations de base (profil et email) demandées par l'application via les scopes OIDC.

![WhatsApp Image 2026-03-29 at 12 55 57](https://github.com/user-attachments/assets/2e3a290e-afb5-4951-9004-367dc8eeeedb)


3. Affichage du Profil Utilisateur
   
Après validation, Google redirige l'utilisateur vers l'application avec un code d'autorisation. Spring Security échange ce code contre un Access Token et un ID Token.

Le contrôleur extrait alors les informations du token (nom, email, image) et les affiche sur la page protégée /profile.

### Profil Utilisateur:

![WhatsApp Image 2026-03-29 at 12 56 18](https://github.com/user-attachments/assets/477b5461-b55f-4b37-9383-de5a8d1ddc8e)


On remarque que les données affichées proviennent directement du compte Google de l'utilisateur (Farah Sahraoui), prouvant que l'authentification a réussi.

4. Déconnexion (Logout)
L'utilisateur peut se déconnecter en cliquant sur le lien "Se déconnecter". Spring Security affiche une page de confirmation pour valider l'action.

### Confirmation de déconnexion:

![WhatsApp Image 2026-03-29 at 12 56 35](https://github.com/user-attachments/assets/802cbb80-c4a8-4f7f-8685-6d679555ede8)


Une fois confirmée, la session HTTP est invalidée, les cookies sont supprimés et l'utilisateur est redirigé vers la page d'accueil.

## Technologies utilisées

Java 17/21

Spring Boot (Web, Security)

Spring Security OAuth2 Client

Thymeleaf (Moteur de templates)

Google Identity Platform (Fournisseur OAuth2)
