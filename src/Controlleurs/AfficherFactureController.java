package Controlleurs;

import Modeles.Gestion_Service.Facture;
import Modeles.Personnes.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.List;

public class AfficherFactureController {

    @FXML
    private TableView<Facture> tableViewFactures;

    @FXML
    private TableColumn<Facture, Integer> colNumeroFacture;

    @FXML
    private TableColumn<Facture, String> colClient;

    @FXML
    private TableColumn<Facture, String> colDateFacture;

    @FXML
    private TableColumn<Facture, Double> colMontantTotal;

    @FXML
    private TableColumn<Facture, String> colAvecRemise;

    private List<Facture> listeFactures;

    public void initialize() {
        // Récupérer la liste des factures
        listeFactures = MenuPrincipaleController.receptionnisteConnecte.getListeFactures();

        if (listeFactures == null || listeFactures.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucune Facture");
            alert.setHeaderText("Aucune facture trouvée");
            alert.setContentText("Aucune facture n'a été ajoutée.");
            alert.showAndWait();
        } else {
            // Configuration des colonnes du TableView
            colNumeroFacture.setCellValueFactory(new PropertyValueFactory<>("numeroFacture"));

            // Configurer l'affichage du nom et prénom du client
            colClient.setCellValueFactory(cellData -> {
                Client client = cellData.getValue().getClient();
                String nomPrenom = client.get_nom() + " " + client.get_prenom();
                return new javafx.beans.property.SimpleStringProperty(nomPrenom);
            });

            // Configurer l'affichage de la date au format dd/MM/yyyy
            colDateFacture.setCellValueFactory(cellData -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return new javafx.beans.property.SimpleStringProperty(
                        dateFormat.format(cellData.getValue().getDateFacture())
                );
            });

            // Mise à jour pour utiliser la méthode totalFacture() sans changer la logique
            colMontantTotal.setCellValueFactory(cellData -> {
                Facture facture = cellData.getValue();
                double total = facture.totalFacture();  // Utilise la méthode totalFacture()
                return new javafx.beans.property.SimpleDoubleProperty(total).asObject();
            });

            colAvecRemise.setCellValueFactory(cellData -> 
                    new javafx.beans.property.SimpleStringProperty(
                            cellData.getValue().isAvecRemise() ? "Oui" : "Non"
                    ));

            // Ajouter les données au TableView
            ObservableList<Facture> observableFactures = FXCollections.observableArrayList(listeFactures);
            tableViewFactures.setItems(observableFactures);
        }
    }

    @FXML
    public void retour() {
        // Ferme la fenêtre actuelle
        Stage stage = (Stage) tableViewFactures.getScene().getWindow();
        stage.close();
    }
}
