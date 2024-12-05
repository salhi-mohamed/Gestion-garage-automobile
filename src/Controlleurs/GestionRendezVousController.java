package Controlleurs;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionRendezVousController {

    @FXML
    private Button btnAjouterRendezVous;
    @FXML
    private Button btnSupprimerRendezVous;
    @FXML
    private Button btnModifierRendezVous;
    @FXML
    private Button btnAfficherRendezVous;

    @FXML
    private Button btnRetour;

    // Ajouter un rendez-vous
    @FXML
    private void ajouterRendezVous() {
        try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/AjouterRendezVous.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'AjouterRendezVous.fxml' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Ajouter un RendezVous");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page 'Ajouter RendezVous'.");
        }    }

    // Supprimer un rendez-vous
    @FXML
    private void supprimerRendezVous() {
        try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/SupprimerRendezVous.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'SupprimerRendezVous.fxml' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("SupprimerRendezVous");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page ' SupprimerRendezVous'.");
        }    }

    // Modifier un rendez-vous
    @FXML
    private void modifierRendezVous() {
        try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/ModifierRendezVous.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'ModifierRendezVous' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("ModifierRendezVous ");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page 'Modifier RendezVous'.");
        }     }

    // Afficher les rendez-vous
    @FXML
    private void afficherRendezVous() {
        try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/AfficherRendezVous.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'AfficherRendezVous' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("AfficherRendezVous ");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
                        e.printStackTrace();  // Afficher la pile des erreurs

            showAlert("Erreur", "Impossible de charger la page 'AfficherRendezVous'.");
        }     }

    // Retour au menu principal
    @FXML
    private void retour() {
        try {
            // Charger le fichier FXML du menu principal
            URL fxmlLocation = getClass().getResource("/Vues/MenuPrincipale.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'MenuPrincipale.fxml' n'a pas été trouvé.");
                return;
            }

            // Charger la scène du menu principal
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());

            // Obtenir le Stage (fenêtre actuelle)
            Stage stage = (Stage) btnRetour.getScene().getWindow();

            // S'assurer que le Stage est valide
            if (stage != null) {
                stage.setTitle("Menu Principal");
                stage.setScene(scene); // Changer la scène pour le menu principal
                stage.show();          // Afficher la nouvelle scène (menu principal)
            } else {
                System.out.println("Stage est nul. Impossible de changer la scène.");
            }
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page 'Menu Principal'.");
        }
    }

    // Méthode générique pour charger une vue
    private void chargerVue(String cheminFXML, String titre) {
        try {
            // Vérifier si le fichier FXML existe
            URL fxmlLocation = getClass().getResource(cheminFXML);
            if (fxmlLocation == null) {
                System.out.println("FXML file not found: " + cheminFXML);
                showAlert("Erreur", "Le fichier FXML '" + cheminFXML + "' n'a pas été trouvé.");
                return;
            }

            // Charger la scène
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Affiche les détails de l'exception dans la console
            showAlert("Erreur", "Impossible de charger la page '" + titre + "'.\n" + e.getMessage());
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
