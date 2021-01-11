package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {


    @FXML
    private TextField tfCode;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;
    @FXML
    private TableView<Etudiant> tvEtudiant;
    @FXML
    private TableColumn<Etudiant, Integer> colCode;
    @FXML
    private TableColumn<Etudiant, String> colNom;
    @FXML
    private TableColumn<Etudiant, String> colPrenom;
    @FXML
    private TableColumn<Etudiant, Integer> colPhone;
    @FXML
    private TableColumn<Etudiant, Integer> colEmail;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

   @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsert){
            insertEtudiant();
        }
        else if (event.getSource() == btnUpdate){
            modifierEtudiant();
        }else if(event.getSource() == btnDelete){
            deleteEtudiant();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewEtudiant();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecole", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Etudiant> getEtudiantList(){
        ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM etudiant";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Etudiant etudiant;
            while(rs.next()){
                etudiant = new Etudiant(rs.getInt("code"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("telephone"),
                        rs.getString("email"));
                etudiantList.add(etudiant);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return etudiantList;
    }

    public void viewEtudiant(){
        ObservableList<Etudiant> list = getEtudiantList();

        colCode.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("email"));

        tvEtudiant.setItems(list);
    }

    private void insertEtudiant(){
        String query = "INSERT INTO etudiant VALUES (" + tfCode.getText() + ",'" + tfNom.getText() + "','" + tfPrenom.getText() + "','"
                + tfPhone.getText() + " ', '"+ tfEmail.getText() +"')";
        executeQuery(query);
        viewEtudiant();
    }
    private void modifierEtudiant(){
        String query = "UPDATE  etudiant SET nom  = '" + tfNom.getText() + "', prenom = '" + tfPrenom.getText() + "', telephone = '"+
                tfPhone.getText() + "', email = '" + tfEmail.getText() + "' WHERE code = " + tfCode.getText() + "";
        executeQuery(query);
        viewEtudiant();
    }
    private void deleteEtudiant(){
        String query = "DELETE FROM etudiant WHERE code = '" + tfCode.getText() + "' ";
        executeQuery(query);
        viewEtudiant();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
