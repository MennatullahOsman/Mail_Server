package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IDraft {

	String sender = "";
	LinkedBased receivers = new LinkedBased();
	String content = "";
	String date = "";
	SList attachments = new SList();
	String subject = "";
	String priority = "";
	String to = "";
	String attach = new String("");

	/**
	 * constructor.
	 */
	public IDraft(String from, String to, String content, String date, SList attachments, String subject,
			String priority) {
		this.sender = from;
		this.content = content;
		this.date = date;
		this.subject = subject;
		this.priority = priority;
		this.to = to;
		this.attachments = attachments;
	}

	/**
	 * create file for the email in the sender's draft.
	 * 
	 * @return
	 */
	public boolean saveEmail() {
		// TODO Auto-generated method stub
		int count = 0;
		refreshIndex(sender, "draft");
		createEmailFiles(sender, "draft");
		writeContent(sender, "draft");
		if (attachments != null) {
			copyAttachements(sender, "draft");
		}
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * copy attachments to email file.
	 * 
	 * @param username.
	 */
	private void copyAttachements(String username, String folder) {
		// TODO Auto-generated method stub
		for (int index = 0; index < attachments.size(); index++) {
			File attach = new File((String) attachments.get(index));
			String[] str = ((String) attachments.get(index)).split("\\\\");
			File dest = new File("./mail server/" + username + "/" + folder + "/" + date + "/" + str[str.length - 1]);
			try {
				Files.copy(attach.toPath(), dest.toPath());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * add the content to the text file.
	 * 
	 * @param username
	 */
	private void writeContent(String username, String folder) {
		// TODO Auto-generated method stub
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("./mail server/" + username + "/" + folder + "/" + date + "/content.txt"));
			writer.append(content);
			writer.newLine();
			writer.append("attachments : " + attach);
			writer.newLine();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * add information about email at "draft" index.
	 * 
	 * @param folder
	 */
	@SuppressWarnings("unchecked")
	private void refreshIndex(String username, String folder) {
		// TODO Auto-generated method stub
		if (attachments != null) {
			for (int i = 0; i < attachments.size(); i++) {
				String[] str = ((String) attachments.get(i)).split("\\\\");
				attach += str[str.length - 1] + "  ";
			}
		} else {
			attach = null;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("from", sender);
		jsonObject.put("to", to);
		jsonObject.put("subject", subject);
		jsonObject.put("date", date);
		jsonObject.put("priority", priority);
		jsonObject.put("attachment", attach);
		try {
			// TODO Auto-generated method stub
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/" + username + "/" + folder + "/index.json"));
			JSONArray jsonArray = (JSONArray) obj;
			jsonArray.add(jsonObject);
			FileWriter fileWriter = new FileWriter("./mail server/" + username + "/" + folder + "/index.json");
			fileWriter.write(jsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * create Email Files
	 * 
	 * @param username
	 */
	private void createEmailFiles(String username, String folder) {
		File f1 = new File("./mail server/" + username + "/" + folder + "/" + date);
		f1.mkdirs();
		try {
			f1.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File f1index = new File("./mail server/" + username + "/" + folder + "/" + date + "/content.txt");
		try {
			f1index.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}