package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Compose {

	private final Stage thisStage;

	public Compose() {
		thisStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Compose.fxml"));
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
	private JFXTextField to;

	@FXML
	private JFXTextField subject;

	@FXML
	private JFXListView<String> attachlist;
	@FXML
	private JFXTextArea content;
	@FXML
	private JFXCheckBox cb1;

	@FXML
	private JFXCheckBox cb2;

	@FXML
	private JFXCheckBox cb3;

	@FXML
	private JFXCheckBox cb4;
	SList attach = new SList();

	@FXML
	void sendEmails(ActionEvent event) {
		String Priority = "";
		if (cb1.isSelected()) {
			Priority = "1";
		}
		if (cb2.isSelected()) {
			Priority = "2";
		}
		if (cb3.isSelected()) {
			Priority = "3";
		}
		if (cb4.isSelected()) {
			Priority = "4";
		}
		MailServerApp mail = new MailServerApp();
		IMail sent = new IMail(Main.getUserName(), to.getText(), content.getText(), mail.getDate(), attach, subject.getText(),
				Priority);
		mail.compose(sent);
	}

	@FXML
	void attachfile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"),
				new ExtensionFilter("Text Files", "*txt"),
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
				new ExtensionFilter("Audio Files", "*.wav", "*.mp4","*.mp3", "*.aac"), new ExtensionFilter("PDF Files", "pdf"));
		List<File> selectedfiles = fc.showOpenMultipleDialog(this.thisStage);
		if (selectedfiles != null) {
			for (int i = 0; i < selectedfiles.size(); i++) {
				attachlist.getItems().add(selectedfiles.get(i).getAbsolutePath());
				attach.add(selectedfiles.get(i).getAbsolutePath());
			}
		} else {
			System.out.println("file not exist");
		}
	}

	@FXML
	void SaveInDraft(ActionEvent event) {
		String Priority = "";
		if (cb1.isSelected()) {
			Priority = "1";
		}
		if (cb2.isSelected()) {
			Priority = "2";
		}
		if (cb3.isSelected()) {
			Priority = "3";
		}
		if (cb4.isSelected()) {
			Priority = "4";
		}
		MailServerApp mail = new MailServerApp();
		IDraft draft = new IDraft(Main.getUserName(), to.getText(), content.getText(), mail.getDate(), attach, subject.getText(),
				Priority);
		mail.Save(draft);
	}

}
