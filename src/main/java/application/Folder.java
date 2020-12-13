package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Folder implements IFolder {
	private String username;
	private String folder;


	/**
	 * constructor.
	 * 
	 * @param username
	 * @param folder
	 */
	public Folder(String username, String folder) {
		this.username = username;
		this.folder = folder;
	}

	@Override
	public DList readIndex() throws IOException {
		// TODO Auto-generated method stub
		DList index = new DList();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/" + username + "/" + folder + "/index.json"));
			JSONArray ind = (JSONArray) obj;
			for (int i = 0; i < ind.size(); i++) {
				index.add((JSONObject) ind.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void writeIndex(DList l) throws IOException {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < l.size(); i++) {
			jsonArray.add((JSONObject) l.get(i));
		}
		FileWriter fileWriter = new FileWriter("./mail server/" + username + "/" + folder + "/index.json");
		fileWriter.write(jsonArray.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}

	@Override
	public String account() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String foldername() {
		// TODO Auto-generated method stub
		return folder;
	}

}
