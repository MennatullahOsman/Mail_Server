package application;

import java.io.IOException;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MailViewer {

	private Pane root;

	public MailViewer() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MailViewer.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void show(HomePage homePage) {
		homePage.overPane.getChildren().add(root);
	}
	 @FXML
	 private Label To;
	 
	 @FXML
	 private Label From;

	 @FXML
	 private Label Subject;

	 @FXML
	 private JFXTextArea Content;

	 @FXML
	 private JFXListView<String> Attachments;
	 
		@FXML
		public void initialize() throws IOException {
			IMail Imail = Main.getIMail();
			MailServerApp mail = new MailServerApp();
			LinkedBased receivers = Imail.receivers;
			String allRecevers = "";
			while(!receivers.isEmpty()) {
				allRecevers += receivers.dequeue();
				if(receivers.size >= 1) {
					allRecevers += ", ";
				}
			}
			To.setText(allRecevers);
			From.setText(Imail.sender);
			Subject.setText(Imail.subject);
			String[] str = mail.getContent(Main.getUserName(), Main.getFolder(), Imail.date).split("\n");
			String contentText = "";
			Boolean flag = true;
			for(int index = 0; index < str.length; index++) {
				if(flag && !str[index].equals("attachments : ")) {
					contentText += str[index] + "\n";
				} else if(str[index].equals("attachments : ")){
					flag = false;
				} else {
					Attachments.getItems().add(str[index]);
				}
			}
			Content.setText(contentText);
		}
	    
}
