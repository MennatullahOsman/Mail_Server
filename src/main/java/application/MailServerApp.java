package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MailServerApp implements IApp, IApp2 {

	public DList emails = new DList();
	int counter;

	public int number_of_mails() {
		return emails.size();

	}

	@Override
	public boolean signin(String email, String password) {
		// TODO Auto-generated method stub
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/account info.json"));
			JSONArray ind = (JSONArray) obj;
			for (int i = 0; i < ind.size(); i++) {
				if (((JSONObject) ind.get(i)).get("email").equals(email)) {
					if (((JSONObject) ind.get(i)).get("password").equals(password)) {
						Main.setUserName(((JSONObject) ind.get(i)).get("username").toString());
						return true;
					}
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
		return false;
	}

	@Override
	public boolean signup(IContact contact) {
		// TODO Auto-generated method stub
		try {
			if (contact.checkVaild()) {
				contact.createAccount();
				contact.AddToAccountInfo();
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		// TODO Auto-generated method stub
		try {
			emails = folder.readIndex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (filter != null) {
			emails = filter.search(emails);

		}
		if (sort != null) {
			emails = sort.iterativeQsort(emails);
		}
	}

	@Override
	public IMail[] listEmails(int page) {
		// TODO Auto-generated method stub
		IMail[] mail = new IMail[10];
		int counter = 1;
		if(emails.size() == 0) {
			return mail;
		}
		int numOfPages = emails.size() / 10;
		if (emails.size() % 10 != 0) {
			numOfPages++;
		}
		if (page <= 1) {
			while (counter <= 10 && emails.size() >= counter) {
				SList attach = new SList();
				attach.add((String) ((JSONObject) emails.get(emails.size() - counter)).get("attachment"));
				IMail m = new IMail((String) ((JSONObject) emails.get(emails.size() - counter)).get("from"),
						(String) ((JSONObject) emails.get(emails.size() - counter)).get("to"), null,
						(String) ((JSONObject) emails.get(emails.size() - counter)).get("date"), attach,
						(String) ((JSONObject) emails.get(emails.size() - counter)).get("subject"),
						(String) ((JSONObject) emails.get(emails.size() - counter)).get("priority"));
				mail[counter - 1] = m;
				counter++;
			}
			this.counter = counter - 1;
		} else if (page >= numOfPages) {
			counter = 1;
			int n;
			if (numOfPages > 1) {
				n = emails.size() % ((numOfPages - 1) * 10);
			} else {
				n = emails.size();
			}
			for (int i = n - 1; i >= 0; i--) {
				SList attach = new SList();
				attach.add((String) ((JSONObject) emails.get(i)).get("attachment"));
				IMail m = new IMail((String) ((JSONObject) emails.get(i)).get("from"), 
						(String) ((JSONObject) emails.get(i)).get("to"), null,
						(String) ((JSONObject) emails.get(i)).get("date"), attach,
						(String) ((JSONObject) emails.get(i)).get("subject"),
						(String) ((JSONObject) emails.get(i)).get("priority"));
				mail[counter - 1] = m;
				counter++;
			}
			this.counter = counter - 1;
		} else {
			counter = 0;
			int n = emails.size() - ((page - 1) * 10) - 1;
			while (counter < 10) {
				SList attach = new SList();
				attach.add((String) ((JSONObject) emails.get(n - counter)).get("attachment"));
				IMail m = new IMail((String) ((JSONObject) emails.get(n - counter)).get("from"), 
						(String) ((JSONObject) emails.get(n - counter)).get("to"), null,
						(String) ((JSONObject) emails.get(n - counter)).get("date"), attach,
						(String) ((JSONObject) emails.get(n - counter)).get("subject"),
						(String) ((JSONObject) emails.get(n - counter)).get("priority"));
				mail[counter] = m;
				counter++;
			}
			this.counter = 10;
		}
		return mail;
	}

	public int numOfEmails() {
		// TODO Auto-generated method stub
		return this.counter;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		// TODO Auto-generated method stub
		Folder f = new Folder((String) mails.get(0), (String) mails.get(1));// constructor takes username and folder
		try {
			DList l = f.readIndex();
			
				for (int j = 2; j < mails.size(); j++) {
				  for (int i = 0; i < l.size(); i++) {
					if (((JSONObject) mails.get(j)).equals((JSONObject) l.get(i))) {
						l.remove(i);
						break;
					}
				}
			}
			f.writeIndex(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ILinkedList getJsonObjects(ILinkedList mails) {
		Folder f = new Folder((String) mails.get(0), (String) mails.get(1));// constructor takes username and folder
		DList l;
		try {
			l = f.readIndex();
			for (int i = 0; i < l.size(); i++) {
				for (int j = 2; j < mails.size(); j++) {
					if ((mails.get(j)).equals(((JSONObject) l.get(i)).get("date"))) {
						mails.set(j, (JSONObject) l.get(i));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mails;
	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		// TODO Auto-generated method stub
		try {
			DList l = des.readIndex();
			for (int i = 2; i < mails.size(); i++) {
				l.add(mails.get(i));
				File f1 = new File("./mail server/" + (String) mails.get(0) + "/" + (String) mails.get(1) + "/"
						+ (String) ((JSONObject) mails.get(i)).get("date"));
				f1.renameTo(new File("./mail server/" + des.account() + "/" + des.foldername() + "/"
						+ (String) ((JSONObject) mails.get(i)).get("date")));
			}
			des.writeIndex(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean compose(IMail email) {
		// TODO Auto-generated method stub
		return email.sendEmail();
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		date = date.replaceAll("[/:]", ",");
		return date;
	}

	@Override
	public void trashAutoDelete(String username) {
		// TODO Auto-generated method stub
		String actualTime = getDate();
		String[] t1 = actualTime.split(" ");
		String[] t2 = t1[0].split(",");
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/" + username + "/trash/index.json"));
			JSONArray jsonArray = (JSONArray) obj;
			for (int i = 0; i < jsonArray.size(); i++) {
				String d = (String) ((JSONObject) jsonArray.get(i)).get("date");
				String[] d1 = d.split(" ");
				String[] d2 = d1[0].split(",");
				if (Integer.parseInt(t2[0]) > Integer.parseInt(d2[0])) {// check years
					jsonArray.remove(i);
					File file = new File("./mail server/" + username + "/trash/" + d);
					try {
						// Deleting the directory recursively.
						delete(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (Integer.parseInt(t2[1]) - Integer.parseInt(d2[1]) > 1) {// check months
					jsonArray.remove(i);
					File file = new File("./mail server/" + username + "/trash/" + d);
					try {
						// Deleting the directory recursively.
						delete(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (Integer.parseInt(t2[1]) - Integer.parseInt(d2[1]) == 1
						&& (Integer.parseInt(t2[2]) >= Integer.parseInt(d2[2]))) {
					jsonArray.remove(i);
					File file = new File("./mail server/" + username + "/trash/" + d);
					try {
						// Deleting the directory recursively.
						delete(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			FileWriter fileWriter = new FileWriter("./mail server/" + username + "/trash/index.json");
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

	private void delete(File file) throws IOException {
		// TODO Auto-generated method stub
		for (File childFile : file.listFiles()) {

			if (childFile.isDirectory()) {
				delete(childFile);
			} else {
				if (!childFile.delete()) {
					throw new IOException();
				}
			}
		}

		if (!file.delete()) {
			throw new IOException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean createNewFolder(IFolder folder) {
		// TODO Auto-generated method stub
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/" + folder.account() + "/new folders.json"));
			JSONArray ind = (JSONArray) obj;
			for (int i = 0; i < ind.size(); i++) {
				if (((JSONObject) ind.get(i)).get("folder").equals(folder.foldername())) {
					return false;
				}
			}
			if ((Pattern.compile("[//// #%&*^:;?<>+=$]").matcher(folder.foldername()).find())) {
				return false;
			}
			JSONObject o = new JSONObject();
			o.put("folder", folder.foldername());
			ind.add(o);
			FileWriter fileWriter = new FileWriter("./mail server/" + folder.account() + "/new folders.json");
			fileWriter.write(ind.toJSONString());
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
		File newfolder = new File("./mail server/" + folder.account() + "/" + folder.foldername());
		newfolder.mkdirs();
		try {
			newfolder.createNewFile();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		File index = new File("./mail server/" + folder.account() + "/" + folder.foldername() + "/index.json");
		try {
			index.createNewFile();
			FileWriter fileWriter = new FileWriter(
					"./mail server/" + folder.account() + "/" + folder.foldername() + "/index.json");
			fileWriter.write("[]");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		return true;
	}

	public String getContent(String username, String folder, String date) throws IOException {
		BufferedReader br = new BufferedReader(
				new FileReader("./mail server/" + username + "/" + folder + "/" + date + "/content.txt"));
		String content = new String("");
		String line = null;
		while ((line = br.readLine()) != null) {
			content += line;
			content += "\n";
		}
		br.close();
		return content;
	}

	public DList ReadNewFolders(String username) {
		DList newfolders = new DList();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/" + username + "/new folders.json"));
			JSONArray ind = (JSONArray) obj;
			for (int i = 0; i < ind.size(); i++) {
				newfolders.add(((JSONObject) ind.get(i)).get("folder"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newfolders;

	}

	@Override
	public void Save(IDraft draft) {
		// TODO Auto-generated method stub
		draft.saveEmail();
	}

	@SuppressWarnings("unchecked")
	public void RenameFolder(String username, String olddir, String newdir) {
		File dir1 = new File("./mail server/" + username + "/" + olddir);
		if (dir1.isDirectory()) {
			File newDir = new File(dir1.getParent() + "\\" + newdir);
			dir1.renameTo(newDir);
		}
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./mail server/" + username + "/new folders.json"));
			JSONArray ind = (JSONArray) obj;
			for (int i = 0; i < ind.size(); i++) {
				if (((JSONObject) ind.get(i)).get("folder").equals(olddir)) {
					JSONObject e = (JSONObject) ind.get(i);
					ind.remove(i);
					e.put("folder", newdir);
					ind.add(e);
				}
			}
			FileWriter fileWriter = new FileWriter("./mail server/" + username + "/new folders.json");
			fileWriter.write(ind.toJSONString());
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
