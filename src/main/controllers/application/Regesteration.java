package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Regesteration {
	private final Stage thisStage;

	public Regesteration() {
		thisStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Regesteration.fxml"));
			loader.setController(this);
			thisStage.setScene(new Scene(loader.load()));
			thisStage.setResizable(false);
			thisStage.setTitle("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStage() {
		this.thisStage.showAndWait();

	}
	 @FXML
	    private Label state;

	    @FXML
	    private JFXButton mini;

	    @FXML
	    private Pane signUpPane;

	    @FXML
	    private JFXTextField username;

	    @FXML
	    private JFXPasswordField pass;

	    @FXML
	    private JFXPasswordField c_pass;

	    @FXML
	    private Pane LogInPane;

	    @FXML
	    private JFXPasswordField password;

	    @FXML
	    private JFXTextField email;

	    @FXML
	    private JFXButton log;

	    @FXML
	    void Login(ActionEvent event) {
	    	MailServerApp mail = new MailServerApp();
	    	if(mail.signin(email.getText().toString(), password.getText().toString())) {
	    		Main.setCheckClose(true);
	    		thisStage.close();
	    	}
	    }

	    @FXML
	    void close(ActionEvent event) {
	    	thisStage.close();
	    }

	    @FXML
	    void gotoSignIn(ActionEvent event) {
	    	LogInPane.setVisible(true);
	    	signUpPane.setVisible(false);
	    }

	    @FXML
	    void gotoSignUp(ActionEvent event) {
	    	LogInPane.setVisible(false);
	    	signUpPane.setVisible(true);
	    }

	    @FXML
	    void minimize(ActionEvent event) {

	    }

	    @FXML
	    void signUp(ActionEvent event) throws IOException {
	    	MailServerApp mail = new MailServerApp();
			Contact user = new Contact(username.getText() + "@lionmail.com", username.getText(), pass.getText());
			if (mail.signup(user)&& pass.getText().equals(c_pass.getText())) {
				Main.setUserName(username.getText());
				Main.setCheckClose(true);
	    		thisStage.close();

			} else {
				if (!pass.getText().equals(c_pass.getText()) && !user.checkVaild()) {
					Alert alert1 = new Alert(AlertType.ERROR);
					alert1.setContentText("Please, enter valid name & ensure that the two passwords are equal");
					alert1.showAndWait();
				} else if (!pass.getText().equals(c_pass.getText())) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setContentText("Please, ensure that the two passwords are equal");
					alert2.showAndWait();
				} else if (!user.checkVaild()) {
					Alert alert3 = new Alert(AlertType.ERROR);
					alert3.setContentText("Invalid name");
					alert3.showAndWait();
				}

			}
	    }
	   
}
