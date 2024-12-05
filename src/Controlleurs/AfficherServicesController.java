package Controlleurs;

import Modeles.Gestion_Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class AfficherServicesController {

    @FXML
    private TableView<Service> tableViewServices;
    @FXML
    private TableColumn<Service, String> colRendezVous;
    @FXML
    private TableColumn<Service, String> colTypeService;

    @FXML
    private TableColumn<Service, String> colPieceRechange;
    @FXML
    private TableColumn<Service, String> colEmploye;
    @FXML
    private TableColumn<Service, Double> colCout;
    @FXML
    private Button btnFermer;

    private List<Service> listeServices;

    // Initialisation des colonnes et données
    @FXML
    public void initialize() {
        // Configuration des colonnes
        colRendezVous.setCellValueFactory(new PropertyValueFactory<>("rendezVous"));
        colTypeService.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Utilisation des méthodes appropriées pour les fournitures et pièces de rechange
        colPieceRechange.setCellValueFactory(new PropertyValueFactory<>("piecesUtilisees"));  // Mise à jour de la colonne pour les pièces utilisées

        colEmploye.setCellValueFactory(new PropertyValueFactory<>("effecteurs"));
        colCout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        // Récupérer les services à afficher
        listeServices = MenuPrincipaleController.receptionnisteConnecte.getListeServices(); // Exemples de services

        if (listeServices == null || listeServices.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucun Service");
            alert.setHeaderText("Aucun service trouvé");
            alert.setContentText("Aucun service n'a été ajouté.");
            alert.showAndWait();
        } else {
            // Ajouter les services dans le tableau
            tableViewServices.getItems().addAll(listeServices);
        }
    }



    // Fermer la fenêtre
    @FXML
    private void fermer() {
        Stage stage = (Stage) btnFermer.getScene().getWindow();
        stage.close();
    }
}