package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RenameLabel {
	private final Stage thisStage;
	private HomePage homePage;

	public RenameLabel(HomePage homePage) {
		this.homePage = homePage;
		thisStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Rename.fxml"));
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
	private void RenameLabels(ActionEvent event) {
		MailServerApp mail = new MailServerApp();
		String current_name = homePage.llistLabels.getValue();
		mail.RenameFolder(Main.getUserName(), current_name, nameLabel.getText());
		homePage.llistLabels.getItems().remove(current_name);
		homePage.llistLabels.getItems().add(nameLabel.getText());
		thisStage.close();
	}

	@FXML
	private void renameClose(ActionEvent event) {
		thisStage.close();
	}

}
