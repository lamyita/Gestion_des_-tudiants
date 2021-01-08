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
    private TextField tfId;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfPages;
    @FXML
    private TableView<Etudiant> tvBooks;
    @FXML
    private TableColumn<Etudiant, Integer> colId;
    @FXML
    private TableColumn<Etudiant, String> colTitle;
    @FXML
    private TableColumn<Etudiant, String> colAuthor;
    @FXML
    private TableColumn<Etudiant, Integer> colYear;
    @FXML
    private TableColumn<Etudiant, Integer> colPages;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

   @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsert){
            insertRecord();
        }
        else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBooks();
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

    public ObservableList<Etudiant> getBooksList(){
        ObservableList<Etudiant> bookList = FXCollections.observableArrayList();
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
                bookList.add(etudiant);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }

    public void showBooks(){
        ObservableList<Etudiant> list = getBooksList();

        colId.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        colYear.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("phone"));
        colPages.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("email"));

        tvBooks.setItems(list);
    }

    private void insertRecord(){
        String query = "INSERT INTO etudiant VALUES (" + tfId.getText() + ",'" + tfTitle.getText() + "','" + tfAuthor.getText() + "','"
                + tfYear.getText() + " ', '"+ tfPages.getText() +"')";
        executeQuery(query);
        showBooks();
    }
    private void updateRecord(){
        String query = "UPDATE  etudiant SET nom  = '" + tfTitle.getText() + "', prenom = '" + tfAuthor.getText() + "', telephone = '"+
                tfYear.getText() + "', email = '" + tfPages.getText() + "' WHERE code = " + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    private void deleteButton(){
        String query = "DELETE FROM etudiant WHERE code = '" + tfId.getText() + "' ";
        executeQuery(query);
        showBooks();
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
