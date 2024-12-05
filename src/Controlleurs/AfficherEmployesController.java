package Controlleurs;

import Modeles.Gestion_Service.Voiture;
import Modeles.Personnes.*;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AfficherEmployesController {

    @FXML
    private TableView<Employe> tableEmployes;
    @FXML
    private TableColumn<Employe, Integer> colID;
    @FXML
    private TableColumn<Employe, String> colNom;
    @FXML
    private TableColumn<Employe, String> colPrenom;
    @FXML
    private TableColumn<Employe, String> colType;
    @FXML
    private TableColumn<Employe, String> colSpecialite;
    @FXML
    private TableColumn<Employe, String> colExpertise;
    @FXML
    private TableColumn<Employe, String> colSalaire;
    @FXML
    private TableColumn<Employe, String> colActions;

    @FXML
    private TableView<Voiture> tableVoitures;
    @FXML
    private TableColumn<Voiture, String> colModele;
    @FXML
    private TableColumn<Voiture, String> colMarque;
    @FXML
    private TableColumn<Voiture, String> colImmatriculation;

    @FXML
    private TableView<Employe> tableEquipe;
    @FXML
    private TableColumn<Employe, String> colEquipeNom;
    @FXML
    private TableColumn<Employe, String> colEquipePrenom;
    @FXML
    private TableColumn<Employe, String> colEquipeRole;

    @FXML
    public void initialize() {
        Receptionniste receptionniste = MenuPrincipaleController.receptionnisteConnecte;

        if (receptionniste != null) {
            initialiserColonnes();
            afficherEmployes(receptionniste);

            tableEmployes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                afficherVoituresEmploye(newValue);
            });
        }
    }

    private void afficherEmployes(Receptionniste receptionniste) {
        tableEmployes.getItems().clear();
        tableEmployes.getItems().addAll(receptionniste.getListeEmployes());
    }

    private void afficherVoituresEmploye(Employe employe) {
        tableVoitures.getItems().clear();
        tableEquipe.getItems().clear();

        if (employe == null) {
            return;
        }

        if (employe instanceof Mecanicien) {
            Mecanicien mecanicien = (Mecanicien) employe;
            tableVoitures.getItems().addAll(mecanicien.get_historique_voitures());
        } else if (employe instanceof Laveur) {
            Laveur laveur = (Laveur) employe;
            tableVoitures.getItems().addAll(laveur.getVoitures());
        } else if (employe instanceof Chef) {
            Chef chef = (Chef) employe;
            tableEquipe.getItems().addAll(chef.getEquipe());
        }
    }

    private void initialiserColonnes() {
        colID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().get_id()).asObject());
        colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_nom()));
        colPrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_prenom()));

        colType.setCellValueFactory(cellData -> {
            Employe employe = cellData.getValue();
            if (employe instanceof Mecanicien) return new SimpleStringProperty("Mécanicien");
            if (employe instanceof Laveur) return new SimpleStringProperty("Laveur");
            if (employe instanceof Chef) return new SimpleStringProperty("Chef");
            return new SimpleStringProperty("Inconnu");
        });

        colSpecialite.setCellValueFactory(cellData -> {
            Employe employe = cellData.getValue();
            if (employe instanceof Laveur) {
                Laveur laveur = (Laveur) employe;
                StringBuilder specialite = new StringBuilder();
                if (laveur.get_specialise_interieur()) specialite.append("Intérieur ");
                if (laveur.get_specialise_exterieur()) specialite.append("Extérieur ");
                return new SimpleStringProperty(specialite.toString().trim());
            }
            if (employe instanceof Mecanicien) {
                return new SimpleStringProperty(((Mecanicien) employe).get_specialite());
            }
            return new SimpleStringProperty("-");
        });

        colExpertise.setCellValueFactory(cellData -> {
            Employe employe = cellData.getValue();
            if (employe instanceof Mecanicien) {
                Mecanicien mecanicien = (Mecanicien) employe;
                return new SimpleStringProperty(mecanicien.get_expertise().toString());
            }
            return new SimpleStringProperty("-");
        });

        colSalaire.setCellValueFactory(cellData -> {
            Employe employe = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f", employe.getSalaire()));
        });

       colActions.setCellFactory(column -> new TableCell<Employe, String>() {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            HBox actionBox = new HBox(10);
            Button btnModifier = new Button("Modifier");
            Button btnSupprimer = new Button("Supprimer");

            // Style du bouton Modifier
            btnModifier.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-size: 14px;");
            btnModifier.setOnAction(e -> {
                Employe employe = getTableView().getItems().get(getIndex());
                modifierEmploye(employe);
            });

            // Style du bouton Supprimer
            btnSupprimer.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px; -fx-font-size: 14px;");
            btnSupprimer.setOnAction(e -> {
                Employe employe = getTableView().getItems().get(getIndex());
                supprimerEmploye(employe);
            });

            actionBox.getChildren().addAll(btnModifier, btnSupprimer);
            setGraphic(actionBox);
        }
    }
});

        colEquipeNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_nom()));
        colEquipePrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_prenom()));
        colEquipeRole.setCellValueFactory(cellData -> {
            Employe employe = cellData.getValue();
            if (employe instanceof Chef) return new SimpleStringProperty("Chef");
            if (employe instanceof Mecanicien) return new SimpleStringProperty("Mécanicien");
            if (employe instanceof Laveur) return new SimpleStringProperty("Laveur");
            return new SimpleStringProperty("Inconnu");
        });

        TableColumn<Employe, String> colActionsEquipe = new TableColumn<>("Actions");
        colActionsEquipe.setCellFactory(column -> new TableCell<Employe, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Button btnSupprimerEquipe = new Button("Supprimer");

                    btnSupprimerEquipe.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-padding: 5 10; -fx-font-size: 14px;");
                    btnSupprimerEquipe.setOnAction(e -> {
                        Employe employe = getTableView().getItems().get(getIndex());
                        supprimerEmployeDeLEquipe(employe);
                    });
                    setGraphic(btnSupprimerEquipe);
                }
            }
        });
        tableEquipe.getColumns().add(colActionsEquipe);
    }

    private void modifierEmploye(Employe employe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vues/ModifierEmploye.fxml"));
            Parent root = loader.load();

            ModifierEmployeController controller = loader.getController();
            controller.setEmployeAModifier(employe);

            Stage stage = new Stage();
            stage.setTitle("Modifier Employé");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            tableEmployes.refresh(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void supprimerEmploye(Employe employe) {
        Receptionniste receptionniste = MenuPrincipaleController.receptionnisteConnecte;
        if (receptionniste != null) {
            receptionniste.getListeEmployes().remove(employe);
            tableEmployes.getItems().remove(employe);
        }
    }

    private void supprimerEmployeDeLEquipe(Employe employe) {
        Chef chef = (Chef) tableEmployes.getSelectionModel().getSelectedItem();
        if (chef != null) {
            chef.getEquipe().remove(employe);
            tableEquipe.getItems().remove(employe);
        }
    }

    @FXML
    public void onEmployeSelected() {
        Employe selectedEmploye = tableEmployes.getSelectionModel().getSelectedItem();
        if (selectedEmploye != null) {
            afficherVoituresEmploye(selectedEmploye);
        }
    }

    @FXML
    private void fermerFenetre() {
        Stage stage = (Stage) tableEmployes.getScene().getWindow();
        stage.close();
    }
}
//