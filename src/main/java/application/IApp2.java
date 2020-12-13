package application; 
import java.io.IOException;
public interface IApp2 {
/**
 * get date.
 * @return date
 */
	public String getDate();
	/**
	 * delete mails from trash after 30 days.
	 * @param username
	 */
	public void trashAutoDelete(String username);
/**
 * creates new folder to filter mails.
 * @param folder
 * @return 
 */
	public boolean createNewFolder(IFolder folder);
	/**
	 * get number of mails in each page.
	 * @return
	 */
	public int numOfEmails();
	/**
	 * read content from text file
	 * @param username
	 * @param folder
	 * @param date
	 * @return content
	 * @throws IOException
	 */
	public String getContent(String username, String folder, String date) throws IOException;
	/**
	 * get jsonObjects of the chosen emails
	 * @param mails contain the unique dates
	 * @return IListList of jsonObjects of the chosen dates
	 */
	public ILinkedList getJsonObjects(ILinkedList mails);
	/**
	 * get names of new folders created.
	 * @param username
	 * @return DList of new folders'name
	 */
	public DList ReadNewFolders(String username);
	/**
	 * save mail in draft folder
	 * @param draft
	 */
	public void Save(IDraft draft);
	/**
	 * rename folder and add new name to new folders.json
	 * @param username
	 * @param dir
	 * @param newdir
	 */
	public void RenameFolder (String username,String dir, String newdir); 
}

