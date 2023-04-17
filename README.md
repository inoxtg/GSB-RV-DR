# GSB-RV-DR

## Application de consultation et lecture des rapports de visite pour les délégués régionnaux

        --JDK
            -- 19
        --mariadb-java-client
            -- 3.1.0
        --slf4j-simple
            -- 1.7.32
        --javafx-controls
            -- 19-ea+7
        --javafx-fxml
            -- 19-ea+7

## Entités
    
    --Praticien
        private int numero;
        private String nom;
        private String ville;
        private double coefNotoriete;
        private LocalDate dateDerniereVisite;
        private int dernierCoefConfiance;
        private String adresse;
        private String codePostal;
        private String prenom;

    --Visiteur
        private String matricule;
        private String nom;
        private String prenom;

    --RapportVisite 
        private int numero;
        private LocalDate dateVisite;
        private LocalDate dateRedaction;
        private String bilan;
        private String motif;
        private int coefConfiance;
        private boolean lu;
        private Visiteur leVisiteur;
        private Praticien lePraticien;

## Utilitaires

    """
        Les 3 Classes utilitaires ont un rôle de comparateur, elles implementent l'interface Comparator
    """
    
    --ComparateurCoefConfiance
        Entries : Praticien 1(Pra1) et Praticien 2(Pra2)
        Elle compare le coeficient de confiance des deux praticiens passés en entrée
        Return : 
            0 si Pra1 = Pra2
            1 si Pra1 > Pra2
           -1 si Pra1 < Pra2

    --ComparateurCoefNotoriete
        Entries : Praticien 1(Pra1) et Praticien 2(Pra2)
        Elle compare le coeficient de notoriété des deux praticiens passés en entrée
        Return : 
            0 si Pra1 = Pra2
            1 si Pra1 > Pra2
           -1 si Pra1 < Pra2

    --ComparateurDateVisite
        Entries : Praticien 1(Pra1) et Praticien 2(Pra2)
        Elle compare la date de la dernière visite des deux praticiens passés en entrée
        Return : 
            0 si Pra1 = Pra2
            1 si Pra1 > Pra2 (après dans le temps) 
           -1 si Pra1 < Pra2 (avant dans le temps)

## Vues

    """
        5 vues sont nécessaires au fonctionnement de l'application :
        Elles correspondent à des dialog ou modal.
    """

    --VueConnexion
        Vue de connexion, elle permet de faire apparaitre une VBox avec deux text fields :
            Matricule 
            Mot de passe (hidden)
                        !!!
                        Necessaire à l'affichage des panneaux Rapport et Praticien, bloqués à l'initialisation de l'application.
                        !!!
        Indique : rien

    --VueConnexionEchouée
        Vue lorsque le matricule et le mot de passe saisie précédement (VueConnexion) ne correspondent pas avec un DR de la BDD
        Indique : "L'identifiant ou le mot de passe que vous avez saisie est incorrecte"

    --VueConnexionVide
        Vue affichée lorsque l'on ne saisie rien dans l'un des text fields de matricule ou mot de passe.
        Indique : "Votre identifiant ou votre mot de passe est manquant"

    --VueRapportIncomplet
        Vue affichée lorsque l'on oublie de saisir soit :
            L'année
            Le mois
            Le Visiteur
        Lors de la selection d'un utilisateur pour affichers ses rapports de visite.
        Indique : "La séléction que vous avez saisie est incomplète"
        
