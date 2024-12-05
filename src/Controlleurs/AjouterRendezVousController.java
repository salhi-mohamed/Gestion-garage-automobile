package Controlleurs;

import Modeles.Gestion_Service.Voiture;
import Modeles.Personnes.Client;
import Modeles.Personnes.Receptionniste;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;

public class AjouterRendezVousController {

    @FXML
    private ComboBox<Client> comboClients;
    @FXML
    private ComboBox<Voiture> comboVoitures;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> heureComboBox;
    @FXML
    private ComboBox<String> statutComboBox;
    @FXML
    private TextField descriptionField;

    private Receptionniste receptionnisteConnecte;
    private int dernierIdRendezVous = 0;

    @FXML
    public void initialize() {
        receptionnisteConnecte = MenuPrincipaleController.receptionnisteConnecte;

        if (receptionnisteConnecte == null) {
            throw new IllegalStateException("Aucun réceptionniste connecté trouvé !");
        }

        // Charger les clients dans le ComboBox et afficher le nom et prénom uniquement
        comboClients.getItems().setAll(receptionnisteConnecte.get_liste_clients());

        // Utiliser un CellFactory pour afficher le nom et prénom du client
        comboClients.setCellFactory(param -> new javafx.scene.control.ListCell<Client>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.get_nom() + " " + item.get_prenom()); // Affiche le nom et prénom
                }
            }
        });

        comboClients.setButtonCell(new javafx.scene.control.ListCell<Client>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.get_nom() + " " + item.get_prenom());
                }
            }
        });

        // Charger les voitures dans le ComboBox et afficher la Marque et l'Immatriculation
        comboVoitures.getItems().setAll(receptionnisteConnecte.getListeVoitures());

        // Utiliser un CellFactory pour afficher la Marque et l'Immatriculation de la voiture
        comboVoitures.setCellFactory(param -> new javafx.scene.control.ListCell<Voiture>() {
            @Override
            protected void updateItem(Voiture item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getMarque() + " - " + item.getImmatriculation()); // Affiche la Marque et l'Immatriculation
                }
            }
        });

        comboVoitures.setButtonCell(new javafx.scene.control.ListCell<Voiture>() {
            @Override
            protected void updateItem(Voiture item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getMarque() + " - " + item.getImmatriculation()); // Affiche la Marque et l'Immatriculation
                }
            }
        });

        // Charger les statuts dans le ComboBox
        statutComboBox.setItems(FXCollections.observableArrayList("CONFIRME", "EN_ATTENTE", "ANNULE"));
    }

    @FXML
    public void ajouterRendezVous() {
        Client client = comboClients.getSelectionModel().getSelectedItem();
        Voiture voiture = comboVoitures.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        String description = descriptionField.getText();
        String statut = statutComboBox.getSelectionModel().getSelectedItem().toUpperCase().replaceAll("é", "e");

        if (client == null || voiture == null || date == null || description.isEmpty()  || statut == null) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }

        dernierIdRendezVous++;

        try {
            receptionnisteConnecte.creerRendezVous(dernierIdRendezVous, description, client.get_id(), voiture.get_immatriculation(), date, statut);
            showAlert("Succès", "Le rendez-vous a été ajouté avec succès !");
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace(); // Affiche la trace d'erreur pour mieux comprendre ce qui échoue
            showAlert("Erreur", "Une erreur est survenue lors de l'ajout du rendez-vous : " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) descriptionField.getScene().getWindow();
        stage.close();
    }

    public void annuler(ActionEvent actionEvent) {
    }

    public void onClientSelected(ActionEvent actionEvent) {
    }
}
