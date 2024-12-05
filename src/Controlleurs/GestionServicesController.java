package Controlleurs;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionServicesController {
    @FXML
    private Button btnAjouterFournitureService;
    public Button btnAjouterPieceService;
    @FXML
    private Button btnAjouterService;
    @FXML
    private Button btnSupprimerService;
    @FXML
    private Button btnModifierService;
    @FXML
    private Button btnAfficherServices;
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

    // Ajouter un service
    @FXML
    private void ajouterService() {
        try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/AjouterService.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'AjouterService.fxml' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Ajouter un service");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // This will print the full stack trace of the exception

            showAlert("Erreur", "Impossible de charger la page 'Ajouter Service'.");
        }    }

    // Supprimer un service
    @FXML
    private void supprimerService() {
        chargerVue("/Vues/SupprimerService.fxml", "Supprimer un Service");
    }

    // Modifier un service
    @FXML
    private void modifierService() {
        try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/ModifierService.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'ModifierService' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Modifier ");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page 'Modifier Service'.");
        }    }

    // Afficher les services
    @FXML
    private void afficherServices() {
            try {
            // Check if the FXML file exists
            URL fxmlLocation = getClass().getResource("/Vues/AfficherServices.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'AjouterService.fxml' n'a pas été trouvé.");
                return;  // Exit the method if the FXML is not found
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Proceed with loading the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Ajouter un service");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // This will print the full stack trace of the exception

            showAlert("Erreur", "Impossible de charger la page 'Ajouter Service'.");
        } 
    }






    // Retourner au menu principal
    @FXML
    private void retour() {
        try {
            URL fxmlLocation = getClass().getResource("/Vues/MenuPrincipale.fxml");
            if (fxmlLocation == null) {
                showAlert("Erreur", "Le fichier FXML 'MenuPrincipale.fxml' n'a pas été trouvé.");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            if (stage != null) {
                stage.setTitle("Menu Principal");
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page 'Menu Principal'.");
        }
    }

    // Méthode utilitaire pour charger une vue
    private void chargerVue(String cheminFXML, String titreFenetre) {
        try {
            URL fxmlLocation = getClass().getResource(cheminFXML);
            if (fxmlLocation == null) {
                showAlert("Erreur", "Le fichier FXML '" + cheminFXML + "' n'a pas été trouvé.");
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(titreFenetre);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la page '" + titreFenetre + "'.");
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

    public void ajouterPieceService(ActionEvent actionEvent) {
    }
}
