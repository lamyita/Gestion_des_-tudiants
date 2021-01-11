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

// tab Eudiants elements-----------------------------------------------------------
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
    private Button btnInsertEtudiant;
    @FXML
    private Button btnUpdateEtudiant;
    @FXML
    private Button btnDeleteEtudiant;
// tab Modules elements--------------------------------------------------
    @FXML
    private TextField tfCodeModule;
    @FXML
    private TextField tfNomModule;
    @FXML
    private TableView<Module> tvModule;
    @FXML
    private TableColumn<Module, Integer> colCodeModule;
    @FXML
    private TableColumn<Module, String> colNomModule;
    @FXML
    private Button btnInsertModule;
    @FXML
    private Button btnUpdateModule;
    @FXML
    private Button btnDeleteModule;
//-------------------------------------------------------------------------------
//tab Specialtes elements--------------------------------------------------------
@FXML
private TextField tfCodeSpecialite;
@FXML
private TextField tfNomSpecialite;
@FXML
private TableView<Specialite> tvSpecialite;
@FXML
private TableColumn<Specialite, Integer> colCodeSpecialite;
@FXML
private TableColumn<Specialite, String> colNomSpecialite;
@FXML
private Button btnInsertSpecialite;
@FXML
private Button btnUpdateSpecialite;
@FXML
private Button btnDeleteSpecialite;
// Handle btns Methods____________________________________________________________
   @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsertEtudiant){
            insertEtudiant();
        }else if(event.getSource()== btnUpdateEtudiant){
            modifierEtudiant();
        }else if(event.getSource()== btnDeleteEtudiant){
            deleteEtudiant();
        }else if(event.getSource()== btnInsertModule){
            insertModule();
        }else if(event.getSource()== btnUpdateModule){
            updateModule();
        }else if(event.getSource()== btnDeleteModule){
            deleteModule();
        }else if(event.getSource()== btnInsertSpecialite){
            insertSpecialite();
        }else if(event.getSource()== btnUpdateSpecialite){
            updateSpecialite();
        }else if(event.getSource()== btnDeleteSpecialite){
            deleteSpecialite();
        }
        else{
            try {
                event.getSource();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
//______________________----------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewData();
    }
//_____________________________Connection DataBase Method-------------------------
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
//----------------------------------------
    //_________________Get Data From Table Etudiant to list of class E------------

    public ObservableList<Etudiant> getEtudiantList(){
        ObservableList<Etudiant> etudiantsList = FXCollections.observableArrayList();
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
                etudiantsList.add(etudiant);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return etudiantsList;
    }
//-------------------------------------------------------------------------------
    //_________________Get Data From Table Etudiant to list of class E------------

    public ObservableList<Specialite> getSpecialiteList(){
        ObservableList<Specialite> specialitesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM specialte";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Specialite specialite;
            while(rs.next()){
                specialite = new Specialite(rs.getInt("id_specialte"), rs.getString("nom_specialte"));
                specialitesList.add(specialite);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return specialitesList;
    }
    //-------------------------------------------------------------------------------
    //_________________Get Data From Table Module to list of class M------------
    public ObservableList<Module> getModuleList(){
        ObservableList<Module> modulesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM module";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Module module;
            while(rs.next()){
                module = new Module(rs.getInt("idmodule"), rs.getString("nom_module"));
                modulesList.add(module);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return modulesList;
    }
//----------------------------------------------------------------------------------

    // view data in screen-----------------------------------------------------------
    public void viewData(){
       //Transfert data from class Etudiant  to table Etudiant
        ObservableList<Etudiant> listEtudiant = getEtudiantList();
        colCode.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("email"));
        tvEtudiant.setItems(listEtudiant);

        //Transfert data from class Module  to table Module
        ObservableList<Module> listModule = getModuleList();
        colCodeModule.setCellValueFactory(new PropertyValueFactory<Module, Integer>("id"));
        colNomModule.setCellValueFactory(new PropertyValueFactory<Module, String>("nom"));
        tvModule.setItems(listModule);

        //Transfert data from class Specialite  to table Specialte
        ObservableList<Specialite> listSpecialite = getSpecialiteList();
        colCodeSpecialite.setCellValueFactory(new PropertyValueFactory<Specialite, Integer>("id"));
        colNomSpecialite.setCellValueFactory(new PropertyValueFactory<Specialite, String>("nom"));
        tvSpecialite.setItems(listSpecialite);

    }

    // All Methods for Apply CRUD with Table module-----------------------------
    private void insertEtudiant(){
        String query = "INSERT INTO etudiant VALUES (" + tfCode.getText() + ",'" + tfNom.getText() + "','" + tfPrenom.getText() + "','"
                + tfPhone.getText() + " ', '"+ tfEmail.getText() +"')";
        executeQuery(query);
        viewData();
    }
    private void modifierEtudiant(){
        String query = "UPDATE  etudiant SET nom  = '" + tfNom.getText() + "', prenom = '" + tfPrenom.getText() + "', telephone = '"+
                tfPhone.getText() + "', email = '" + tfEmail.getText() + "' WHERE code = " + tfCode.getText() + "";
        executeQuery(query);
        viewData();
    }
    private void deleteEtudiant(){
        String query = "DELETE FROM etudiant WHERE code = '" + tfCode.getText() + "' ";
        executeQuery(query);
        viewData();
    }
//-------------------------------------------------------------------------------
    // All Methods for Apply CRUD with Table module
    private void insertSpecialite(){
        String query = "INSERT INTO specialte VALUES (" + tfCodeSpecialite.getText() + ",'" + tfNomSpecialite.getText() + "')";
        executeQuery(query);
        viewData();
    }
    private void updateSpecialite(){
        String query = "UPDATE  specialte SET nom_specialte  = '" + tfNomSpecialite.getText() + "'  WHERE id_specialte = '" + tfCodeSpecialite.getText() + "' ";
        executeQuery(query);
        viewData();
    }
    private void deleteSpecialite(){
        String query = "DELETE FROM specialte WHERE id_specialte = '" + tfCodeSpecialite.getText() + "' ";
        executeQuery(query);
        viewData();
    }
//------------------------------------------------------------------------------
 //-------------------------------------
private void insertModule(){
    String query = "INSERT INTO module VALUES (" + tfCodeModule.getText() + ",'" + tfNomModule.getText() + "')";
    executeQuery(query);
    viewData();
}
    private void updateModule(){
        String query = "UPDATE  module SET nom_module  = '" + tfNomModule.getText() + "'  WHERE idmodule = '" + tfCodeModule.getText() + "' ";
        executeQuery(query);
        viewData();
    }
    private void deleteModule(){
        String query = "DELETE FROM module WHERE idmodule = '" + tfCodeModule.getText() + "' ";
        executeQuery(query);
        viewData();
    }
 //--------------------------------
    //Exuctions des Requetes
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
