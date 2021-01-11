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


public class specialiteController implements Initializable{

    @FXML
    private TextField  tfCodeS;                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ;
    @FXML
    private TextField tfNomS;

    private TableView<Specialite> tvSpecialite;
    @FXML
    private TableColumn<Specialite, Integer> colCodeS;
    @FXML
    private TableColumn<Specialite, String> colNomeS;

    @FXML
    private Button btnInsertS;
    @FXML
    private Button btnUpdateS;
    @FXML
    private Button btnDeleteS;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsertS){
            insertSpecialite();
        }
        else if (event.getSource() == btnUpdateS){
            modifierSpecialite();
        }else if(event.getSource() == btnDeleteS){
            deleteSpecialite();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewSpecialite();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Africa/Casablanca", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Specialite> getSpecialiteList(){
        ObservableList<Specialite> specialiteList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM specialite";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Specialite specialite;
            while(rs.next()){
                specialite = new Specialite(rs.getInt("code"), rs.getString("nom"));

                specialiteList.add(specialite);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return specialiteList;
    }

    public void viewSpecialite(){
        ObservableList<Specialite> list = getSpecialiteList();

        colCodeS.setCellValueFactory(new PropertyValueFactory<Specialite, Integer>("id"));
        colNomeS.setCellValueFactory(new PropertyValueFactory<Specialite, String>("nom"));

        tvSpecialite.setItems(list);
    }

    private void insertSpecialite(){
        String query = "INSERT INTO specialite VALUES (" + tfCodeS.getText() + ",'" + tfNomS.getText() +"')";
        executeQuery(query);
        viewSpecialite();
    }
    private void modifierSpecialite(){
        String query = "UPDATE  specialite SET nom  = '" + tfNomS.getText() + "";
        executeQuery(query);
        viewSpecialite();
    }
    private void deleteSpecialite(){
        String query = "DELETE FROM specialite WHERE code = '" + tfNomS.getText() + "' ";
        executeQuery(query);
        viewSpecialite();
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