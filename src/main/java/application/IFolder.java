package application;

import java.io.IOException;

public interface IFolder {
/**
 * read index and store it in double linked list.
 * @param username
 * @param folder
 * @return DList of JSONObject
 * @throws IOException
 */
	public DList readIndex() throws IOException;
	/**
	 * rewrite index file after change.
	 * @param l
	 * @throws IOException
	 */
	public void writeIndex(DList l) throws IOException;
	/**
	 * account name
	 * @return username
	 */
	public String account();
	/**
	 *folder name 
	 * @return folder
	 */
	public String foldername();
}
