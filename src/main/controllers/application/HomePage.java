package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomePage {
	@FXML
	private IMail[] arr;
	MailServerApp mail = new MailServerApp();
	Emails container;
	private final Stage thisStage;

	public HomePage() {
		thisStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomePage.fxml"));
			loader.setController(this);
			thisStage.setScene(new Scene(loader.load()));
			thisStage.setResizable(false);
			thisStage.setTitle("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStage() {
		sortComboBox.getItems().add("By priority");
		sortComboBox.getItems().add("By Date");
		items.getItems().add("to");
		items.getItems().add("from");
		items.getItems().add("subject");
		items.getItems().add("year");
		items.getItems().add("month");
		items.getItems().add("day");
		items.getItems().add("priority");
		items.getItems().add("attachment");
		thisStage.showAndWait();
		
	}


	@FXML
	void compose(ActionEvent event) throws IOException {
		Compose compose = new Compose();
		compose.showStage();
	}

	@FXML
	public JFXComboBox<String> items;
	@FXML
	public JFXTextField sName;
	
	@FXML
    private Label userNameLabel;
	
	@FXML
	public JFXButton deleteFrom;
	@FXML
	public JFXButton Rename;
	@FXML
	public JFXButton Refresh;
	@FXML
	public JFXButton moveto;
	@FXML
	public JFXButton Search;
	@FXML
	public JFXComboBox<String> selectToMove;

	@FXML
	public JFXComboBox<String> llistLabels;

	@FXML
	public JFXComboBox<String> sortComboBox;
	@FXML
	public JFXButton Sort;
	
	@FXML
	private JFXButton ViewLabels;
	@FXML
	private JFXButton seeBtn;
	
	@FXML
	private JFXButton labelsCreator;
	
	@FXML
	public Pane overPane;

	@FXML
	void refreshBtnAction(ActionEvent event) {
		if (!overPane.getChildren().isEmpty()) {
			overPane.getChildren().remove(0);
		}
		Emails mailContainer = new Emails();
		container = mailContainer;
		mailContainer.show(this);
	}

	@FXML
	void MoveEmails(ActionEvent event) {
		container.move(this);
	}
	@FXML
	void selectToMoveBtnAction(ActionEvent event) {
		moveto.setDisable(false);
	}
	@FXML
	void deleteEmails(ActionEvent event) {
		container.delete();
	}
	@FXML
	void SortBtnAction(ActionEvent event) {
		Sort s;
		String y = sortComboBox.getValue();
		if (y == "By priority") {
			s = new Sort();

		} else {
			s = null;
		}
		container.sort(s);
	}

	@FXML
	void SearchBtnAction(ActionEvent event) {
		if(!sName.getText().isEmpty()) {
		String type = items.getValue();
		String key = sName.getText();
		Filter f = new Filter(type, key);
		container.search(f);
		}
	}
	@FXML
    void sortEnable(ActionEvent event) {
		Sort.setDisable(false);
    }
	@FXML
	void setPromo(ActionEvent event) {
		String Item = items.getValue();
		sName.setDisable(false);
		Search.setDisable(false);
		if (Item == "to") {
			sName.setPromptText("send to ..");

		} else if (Item == "from") {
			sName.setPromptText("from ..");

		} else if (Item == "subject") {
			sName.setPromptText("subject ..");

		} else if (Item == "month") {
			sName.setPromptText("month ..");

		} else if (Item == "day") {
			sName.setPromptText("day ..");

		} else if (Item == "year") {
			sName.setPromptText("year ..");

		} else if (Item == "priority") {
			sName.setPromptText("priority ..");

		} else if (Item == "attachment") {
			sName.setPromptText("with/without");
		}
	}


	@FXML
	void openLabelCreator(ActionEvent event) throws IOException {
		CreateLabels labels = new CreateLabels(this);
		labels.showStage();
	}

	@FXML
	void seeMoreBtnAction(ActionEvent event) {
		ViewLabels.setVisible(true);
		llistLabels.setVisible(true);
		seeBtn.setVisible(false);
		labelsCreator.setVisible(true);
	}

	@FXML
	void renameBtnActin(ActionEvent event) throws IOException {
		RenameLabel rename = new RenameLabel(this);
		rename.showStage();
	}

	@FXML
	void LeftMenuBtnsAction(ActionEvent event) {
		Refresh.setDisable(false);
		Rename.setDisable(true);
		moveto.setDisable(true);
		deleteFrom.setDisable(true);
		selectToMove.setDisable(true);
		if (!overPane.getChildren().isEmpty()) {
			overPane.getChildren().remove(0);
		}
		if(event.getSource().toString().contains("Trash")) {
		   Main.setFolder("trash");
	    }
		if(event.getSource().toString().contains("inbox")) {
			 Main.setFolder("inbox");
		}
		if(event.getSource().toString().contains("Draft")) {
			 Main.setFolder("draft");
		}
		if(event.getSource().toString().contains("Sent Mail")) {
			 Main.setFolder("sent");
		}
		Emails mailContainer = new Emails();
		container = mailContainer;
		mailContainer.show(this);
	}

	@FXML
	void LabelsViewerBtnAction(ActionEvent event) {
		Refresh.setDisable(false);
		if (llistLabels.getValue() != null) {
			moveto.setDisable(true);
			deleteFrom.setDisable(true);
			selectToMove.setDisable(true);
			Rename.setDisable(false);
			if (!overPane.getChildren().isEmpty()) {
				overPane.getChildren().remove(0);
			}
			Main.setFolder(llistLabels.getValue());
			Emails mailContainer = new Emails();
			container = mailContainer;
			mailContainer.show(this);
			Main.setFolder(llistLabels.getValue());
		}

	}

	@FXML
	public void initialize() {
		DList items = new DList();
		items = mail.ReadNewFolders(Main.getUserName());
		for (int i = 0; i < items.size(); i++) {
			llistLabels.getItems().add((String) items.get(i));
			selectToMove.getItems().add((String) items.get(i));
		}
		selectToMove.getItems().add("trash");
		userNameLabel.setText(Main.getUserName()+" !");
	}
}
