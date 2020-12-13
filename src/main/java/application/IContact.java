package application;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IContact {
	/**
	 * check if the user name isn't used or containing numbers only.
	 * @throws FileNotFoundException 
	 */
	public boolean checkVaild() throws IOException;
	/**
	 * create new account.
	 */
	public void createAccount();
	/**
	 * add information about the new account to text file. 
	 */
	public void AddToAccountInfo();
}
