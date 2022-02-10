package spring.service;

import java.util.List;

import spring.entity.Account;
import spring.entity.Credential;
import spring.util.ItemType;

public interface AccountService {
	// sign in method
	public void addNewAccount(Account account);

	// verifies username when making new account
	public boolean checkUsername(String username);

	// verifies email when making new account
	public boolean checkEmail(String email);

	// verifies password when logging in
	public boolean checkUserAndPass(String username, String password);

	public Account getAccountForUser(String username);

	public void addItemtoCart(ItemType itemType, int accountId, int itemId);

	public List<String> getItemsList(int accId);

	public int[] getIdList(int accId);

	public List<String> getUpdatedItemsList(int accId, int itemId);

	public int[] getUpdatedIdList(int accId, int itemId);

	public void order(int accId, Credential credential);

	public void updateUsername(int accId, String username);

	public void updatePassword(int accId, String password);

	public void updateEmail(int accId, String email);

	public boolean checkIfCartIsEmpty(int accId);

}
