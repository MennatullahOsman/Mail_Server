package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class Emails {

	private Pane root;
	HomePage homePage;

	public Emails() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Emails.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void show(HomePage homePage) {
		this.homePage = homePage;
		homePage.overPane.getChildren().add(root);
		Folder folderName = new Folder(Main.getUserName(), Main.getFolder());
		mail.setViewingOptions(folderName, null, null);
		arr = mail.listEmails(page_number);
		if (mail.number_of_mails() < 10) {
			Next.setDisable(true);
		}
		Previous.setDisable(true);
		viewMails(null,null);
	}

	private MailServerApp mail = new MailServerApp();
	
	private static int page_number = 1;
	
	@FXML
	private IMail[] arr;
	
	@FXML
	private VBox pnl_scroll;

	@FXML
	private JFXCheckBox cb0, cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9;

	@FXML
	private JFXButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

	@FXML
	private Label date0, date1, date2, date3, date4, date5, date6, date7, date8, date9;

	@FXML
	private Label sub0, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9;

	@FXML
	private Line line0, line1, line2, line3, line4, line5, line6, line7, line8, line9;

	@FXML
	private JFXButton Next;

	@FXML
	private JFXButton Previous;

	@FXML
	void checkIfSellected(ActionEvent event) {
		if (cb0.isSelected() || cb1.isSelected() || cb2.isSelected() || cb3.isSelected() || cb4.isSelected()
				|| cb5.isSelected() || cb6.isSelected() || cb7.isSelected() || cb8.isSelected() || cb9.isSelected()) {
			homePage.deleteFrom.setDisable(false);
			homePage.selectToMove.setDisable(false);
		} else {
			homePage.deleteFrom.setDisable(true);
			homePage.selectToMove.setDisable(true);
		}
	}

	public void delete() {
		ILinkedList delEmails = new SList();
		delEmails.add(Main.getUserName());
		delEmails.add(Main.getFolder());
		if (cb0.isSelected()) {
			delEmails.add(date0.getText());
		}
		if (cb1.isSelected()) {
			delEmails.add(date1.getText());
		}
		if (cb2.isSelected()) {
			delEmails.add(date2.getText());
		}
		if (cb3.isSelected()) {
			delEmails.add(date3.getText());
		}
		if (cb4.isSelected()) {
			delEmails.add(date4.getText());
		}
		if (cb5.isSelected()) {
			delEmails.add(date5.getText());
		}
		if (cb6.isSelected()) {
			delEmails.add(date6.getText());
		}
		if (cb7.isSelected()) {
			delEmails.add(date7.getText());
		}
		if (cb8.isSelected()) {
			delEmails.add(date8.getText());
		}
		if (cb9.isSelected()) {
			delEmails.add(date9.getText());
		}
		Folder des = new Folder(Main.getUserName(), "trash");
		delEmails = mail.getJsonObjects(delEmails);
		mail.moveEmails(delEmails, des);
		mail.deleteEmails(delEmails);
		setCheckBoxSelected(false);
		viewMails(null,null);
	}
	
	public void move(HomePage homePage) {
		if(!Main.getFolder().equals(homePage.selectToMove.getValue())) {
		ILinkedList delEmails = new SList();
		delEmails.add(Main.getUserName());
		delEmails.add(Main.getFolder());
		if (cb0.isSelected()) {
			delEmails.add(date0.getText());
		}
		if (cb1.isSelected()) {
			delEmails.add(date1.getText());
		}
		if (cb2.isSelected()) {
			delEmails.add(date2.getText());
		}
		if (cb3.isSelected()) {
			delEmails.add(date3.getText());
		}
		if (cb4.isSelected()) {
			delEmails.add(date4.getText());
		}
		if (cb5.isSelected()) {
			delEmails.add(date5.getText());
		}
		if (cb6.isSelected()) {
			delEmails.add(date6.getText());
		}
		if (cb7.isSelected()) {
			delEmails.add(date7.getText());
		}
		if (cb8.isSelected()) {
			delEmails.add(date8.getText());
		}
		if (cb9.isSelected()) {
			delEmails.add(date9.getText());
		}
		Folder des = new Folder(Main.getUserName(), homePage.selectToMove.getValue());
		delEmails = mail.getJsonObjects(delEmails);
		mail.moveEmails(delEmails, des);
		mail.deleteEmails(delEmails);
		setCheckBoxSelected(false);
		viewMails(null,null);
		}
	}
	
	public void search(Filter f) {
		viewMails(f,null);
	}
	public void sort(Sort s) {
		viewMails(null,s);
	}

	@FXML
	void seeNext(ActionEvent event) {
		page_number++;
		viewMails(null,null);
		if (mail.number_of_mails() < page_number * 10) {
			Next.setDisable(true);
		}
		Previous.setDisable(false);
	}

	@FXML
	void seePreviousBtnActon(ActionEvent event) {
		page_number--;
		if (page_number == 1) {
			Previous.setDisable(true);
		}
		viewMails(null,null);
		Next.setDisable(false);
	}

	public void viewMails(Filter filter,Sort sort) {
		Folder folderName = new Folder(Main.getUserName(), Main.getFolder());
		mail.setViewingOptions(folderName, filter, sort);
		arr = mail.listEmails(page_number);
		
		boolean emails_empty = true;
		boolean emails_Ten = false;
		setCheckBox(true);
		
		// zero
		if (arr[0] != null) {
			sub0.setText(arr[0].subject);
			date0.setText(arr[0].date);
			emails_empty = false;
		} else {
			cb0.setVisible(false);
		}
		// one
		if (arr[1] != null) {
			sub1.setText(arr[1].subject);
			date1.setText(arr[1].date);
		} else {
			cb1.setVisible(false);
		}
		// two
		if (arr[2] != null) {
			sub2.setText(arr[2].subject);
			date2.setText(arr[2].date);
		} else {
			cb2.setVisible(false);
		}
		// three
		if (arr[3] != null) {
			sub3.setText(arr[3].subject);
			date3.setText(arr[3].date);
		} else {
			cb3.setVisible(false);
		}
		// four
		if (arr[4] != null) {
			sub4.setText(arr[4].subject);
			date4.setText(arr[4].date);
		} else {
			cb4.setVisible(false);
		}
		// five
		if (arr[5] != null) {
			sub5.setText(arr[5].subject);
			date5.setText(arr[5].date);
		} else {
			cb5.setVisible(false);
		}
		// six
		if (arr[6] != null) {
			sub6.setText(arr[6].subject);
			date6.setText(arr[6].date);
		} else {
			cb6.setVisible(false);
		}
		// seven
		if (arr[7] != null) {
			sub7.setText(arr[7].subject);
			date7.setText(arr[7].date);
		} else {
			cb7.setVisible(false);
		}
		// eight
		if (arr[8] != null) {
			sub8.setText(arr[8].subject);
			date8.setText(arr[8].date);
		} else {
			cb8.setVisible(false);
		}
		// nine
		if (arr[9] != null) {
			sub9.setText(arr[9].subject);
			date9.setText(arr[9].date);
			emails_Ten = true;
		} else {
			cb9.setVisible(false);
		}
		if(!emails_empty) {
			homePage.items.setDisable(false);
			homePage.sortComboBox.setDisable(false);
			homePage.deleteFrom.setDisable(false);
			homePage.selectToMove.setDisable(false);	
		}else {
			homePage.items.setDisable(true);
			homePage.sortComboBox.setDisable(true);
			homePage.moveto.setDisable(true);
			homePage.deleteFrom.setDisable(true);
			homePage.selectToMove.setDisable(true);
			
		}
		if(emails_Ten) {
			Next.setDisable(false);
		} else {
			Next.setDisable(true);
		}
		
	}
	@FXML
    void emailviewer(ActionEvent event) {
		if (!homePage.overPane.getChildren().isEmpty()) {
			homePage.overPane.getChildren().remove(0);
		}
		if(event.getSource().toString().contains("btn0")) {
			Main.setIMail(arr[0]);
		}
        if(event.getSource().toString().contains("btn1")) {
        	Main.setIMail(arr[1]);
		}
        if(event.getSource().toString().contains("btn2")) {
        	Main.setIMail(arr[2]);
		}
        if(event.getSource().toString().contains("btn3")) {
        	Main.setIMail(arr[3]);
		}
        if(event.getSource().toString().contains("btn4")) {
        	Main.setIMail(arr[4]);
		}
        if(event.getSource().toString().contains("btn5")) {
        	Main.setIMail(arr[5]);
		}
        if(event.getSource().toString().contains("btn6")) {
        	Main.setIMail(arr[6]);
		}
        if(event.getSource().toString().contains("btn7")) {
        	Main.setIMail(arr[7]);
		}
        if(event.getSource().toString().contains("btn8")) {
        	Main.setIMail(arr[8]);
		}
        if(event.getSource().toString().contains("btn9")) {
        	Main.setIMail(arr[9]);
		}
		MailViewer mailViewer = new MailViewer();
	    mailViewer.show(homePage);	
	}
	private void setCheckBox(Boolean bool) {
		cb0.setVisible(bool);
		cb1.setVisible(bool);
		cb2.setVisible(bool);
		cb3.setVisible(bool);
		cb4.setVisible(bool);
		cb5.setVisible(bool);
		cb6.setVisible(bool);
		cb7.setVisible(bool);
		cb8.setVisible(bool);
		cb9.setVisible(bool);
	}
	
	private void setCheckBoxSelected(Boolean bool) {
		cb0.setSelected(bool);
		cb1.setSelected(bool);
		cb2.setSelected(bool);
		cb3.setSelected(bool);
		cb4.setSelected(bool);
		cb5.setSelected(bool);
		cb6.setSelected(bool);
		cb7.setSelected(bool);
		cb8.setSelected(bool);
		cb9.setSelected(bool);
	}
}
