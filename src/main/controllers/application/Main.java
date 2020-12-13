package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private static String folder;
	private static String UserName = "";
	private static Boolean checkClose = false;
	private static IMail mail = new IMail(null, null, null, null, null, null, null);
	public void start(Stage primaryStage) throws IOException {
		Regesteration regesteration = new Regesteration();
		regesteration.showStage();
		if(checkClose) {
			HomePage homePage = new HomePage();
			homePage.showStage();
		}
	}
	public static void setIMail (IMail mail) {
		Main.mail = mail;
	}
	public static void setCheckClose(Boolean checkClose) {
		Main.checkClose = checkClose;
	}
	public static void setUserName(String UserName) {
		Main.UserName = UserName;
	}

	public static IMail getIMail() {
		return mail;

	}
	public static Boolean getCheckClose() {
		return checkClose;

	}
	public static String getFolder() {
		return folder;

	}

	public static String getUserName() {
		return UserName;

	}

	public static void setFolder(String folder) {
		Main.folder = folder;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
