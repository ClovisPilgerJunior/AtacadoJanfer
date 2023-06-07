package presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.DatabaseConnection;
import model.MainProgramApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private void initialize() {
        // Adiciona um listener de evento de teclado ao campo de senha
        passwordPasswordField.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButtonOnAction(new ActionEvent());
        }
    }

    @FXML
    private void loginButtonOnAction(ActionEvent e){
        if(!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
           // loginMessageLabel.setText("Tentando fazer o login");
           validateLogin();
        } else {
            loginMessageLabel.setText("Por favor preencha o login e senha");
        }

    }

    @FXML
    private void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection conectDB = connectNow.getConnection();

        String veryfiLogin = "SELECT count(1) FROM contausuario WHERE usuario = '" + usernameTextField.getText() + "' AND senha = '" + passwordPasswordField.getText() + "'";

        try {

            Statement statement = conectDB.createStatement();
            ResultSet quereyResult = statement.executeQuery(veryfiLogin);

            while(quereyResult.next()){
                if (quereyResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Bem-Vindo, " + usernameTextField.getText() +"!");

                    // Atualização da coluna "ultimoLogin"
                    String updateLogin = "UPDATE contausuario SET ultimoLogin = current_timestamp WHERE usuario = '"
                            + usernameTextField.getText() + "'";

                    try {
                        Statement updateStatement = conectDB.createStatement();
                        updateStatement.executeUpdate(updateLogin);

                        // Chame o método start() da classe MainProgramApplication para iniciar a aplicação principal
                        MainProgramApplication mainApp = new MainProgramApplication();
                        mainApp.start(new Stage());

                        // Feche a janela de login
                        Stage loginStage = (Stage) loginButton.getScene().getWindow();
                        loginStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    loginMessageLabel.setText("Login inválido. Tente Novamente");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
