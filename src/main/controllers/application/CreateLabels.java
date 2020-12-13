package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateLabels {
	
	private final Stage thisStage;
	
	private HomePage homePage;

	public CreateLabels(HomePage homePage) {
		this.homePage = homePage;
		thisStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateLabels.fxml"));
			loader.setController(this);
			thisStage.setScene(new Scene(loader.load()));
			thisStage.setTitle("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStage() {
		thisStage.showAndWait();
	}

	@FXML
	private JFXTextField nameLabel;
	@FXML
	private JFXButton cancel;

	@FXML
	private void closeLabelsCreator(ActionEvent event) {
		thisStage.close();
	}

	@FXML
	private void createLabels(ActionEvent event) {
		MailServerApp Mail = new MailServerApp();
		Folder folder = new Folder(Main.getUserName(), nameLabel.getText());
		Mail.createNewFolder(folder);
		homePage.llistLabels.getItems().add(nameLabel.getText());
		homePage.selectToMove.getItems().add(nameLabel.getText());
		thisStage.close();
	}

}
