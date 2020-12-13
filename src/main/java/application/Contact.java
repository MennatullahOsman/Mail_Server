
package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Contact implements IContact{
	private String username;
	private String password;
	private String email;
/**
 * constructor.
 */
public Contact(String mail, String name, String pass) {
	password = pass;
	username = name;
	email = mail;
}

	@Override
	public boolean checkVaild() throws IOException {
		// TODO Auto-generated method stub
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/account info.json"));
			JSONArray ind = (JSONArray) obj;
			for (int i = 0; i < ind.size(); i++) {
				if(((JSONObject) ind.get(i)).get("email").equals(email)
						|| ((JSONObject) ind.get(i)).get("username").equals(username)) {
					return false;
				}
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// special characters is forbidden.
		if((Pattern.compile("[////# % & * ^ : ; ? < > + = $]").matcher(email).find())) {
			return false;
		}
		if (Character.isDigit(email.charAt(0))) {
			return false;
		}
		return true;
	}

	@Override
	public void createAccount() {
		// TODO Auto-generated method stub
		File f1 = new File("./mail server/"+ username + "/inbox");
		f1.mkdirs();
		try {
			f1.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		File f1index = new File("./mail server/"+ username + "/inbox/index.json");
		try {
			f1index.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("./mail server/"+ username + "/inbox/index.json");
			fileWriter.write("[]");
			fileWriter.flush();  
		    fileWriter.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		File f2 = new File("./mail server/"+ username + "/trash");
		f2.mkdirs();
		try {
			f2.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		File f2index = new File("./mail server/"+ username + "/trash/index.json");
		try {
			f2index.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			fileWriter = new FileWriter("./mail server/"+ username + "/trash/index.json");
			fileWriter.write("[]");
			fileWriter.flush();  
		    fileWriter.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		File f3 = new File("./mail server/"+ username + "/sent");
		f3.mkdirs();
		try {
			f3.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		File f3index = new File("./mail server/"+ username + "/sent/index.json");
		try {
			f3index.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			fileWriter = new FileWriter("./mail server/"+ username + "/sent/index.json");
			fileWriter.write("[]");
			fileWriter.flush();  
		    fileWriter.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		File f4 = new File("./mail server/"+ username + "/draft");
		f4.mkdirs();
		try {
			f4.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		File f4index = new File("./mail server/"+ username + "/draft/index.json");
		try {
			f4index.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			fileWriter = new FileWriter("./mail server/"+ username + "/draft/index.json");
			fileWriter.write("[]");
			fileWriter.flush();  
		    fileWriter.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		File folders = new File("./mail server/"+ username + "/new folders.json");
		try {
			folders.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			fileWriter = new FileWriter("./mail server/"+ username + "/new folders.json");
			fileWriter.write("[]");
			fileWriter.flush();  
		    fileWriter.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void AddToAccountInfo(){
		try {
			// TODO Auto-generated method stub
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/account info.json"));
			JSONArray jsonArray = (JSONArray) obj;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", email);
			jsonObject.put("password", password);
			jsonObject.put("username", username);
			jsonArray.add(jsonObject);
			FileWriter fileWriter = new FileWriter("./mail server/account info.json");
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
}




