package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import javax.swing.text.TableView;
import java.awt.*;

public class Controller {
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;

    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private javafx.scene.control.TableView<Etudiant> tableView;
    @FXML
    private TableColumn<Etudiant, Integer> columnId;
    @FXML
    private TableColumn<Etudiant, String> columnNom;
    @FXML
    private TableColumn<Etudiant, String> columnPrenom;
    @FXML
    private TableColumn<Etudiant, String> columnPhone;
    @FXML
    private TableColumn<Etudiant, String> columnEmail;



}
