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

public class GestionFacturesController {

    @FXML
    private Button btnAjouterFacture;
    @FXML
    private Button btnModifierFacture;
    @FXML
    private Button btnSupprimerFacture;
    @FXML
    private Button btnAfficherFactures;
    @FXML
    private Button btnRetour;

    // Ajouter une facture
    @FXML
    private void ajouterFacture() {
        try {
            // Vérification de l'existence du fichier FXML
            URL fxmlLocation = getClass().getResource("/Vues/AjouterFacture.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'AjouterFacture.fxml' n'a pas été trouvé.");
                return;  // Sortir de la méthode si le fichier FXML n'est pas trouvé
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Ajouter une facture");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Afficher la pile des erreurs
            showAlert("Erreur", "Impossible de charger la page 'Ajouter Facture'.");
        }
    }

    // Modifier une facture
    @FXML
    private void modifierFacture() {
        chargerVue("/Vues/ModiferFacture.fxml", "Supprimer une Facture");

    }

    // Supprimer une facture
    @FXML
    private void supprimerFacture() {

       
    }

    // Afficher les factures
    @FXML
    private void afficherFactures() {
 try {
            // Vérification de l'existence du fichier FXML
            URL fxmlLocation = getClass().getResource("/Vues/AfficherFactures.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                showAlert("Erreur", "Le fichier FXML 'AjouterFacture.fxml' n'a pas été trouvé.");
                return;  // Sortir de la méthode si le fichier FXML n'est pas trouvé
            } else {
                System.out.println("FXML file loaded successfully.");
            }

            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Ajouter une facture");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Afficher la pile des erreurs
            showAlert("Erreur", "Impossible de charger la page 'Ajouter Facture'.");
        }    }

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

    // Méthode pour gérer l'ajout d'une pièce (exemple, à personnaliser si nécessaire)
    public void ajouterPieceFacture(ActionEvent actionEvent) {
        // Code pour ajouter une pièce à la facture
    }
}
