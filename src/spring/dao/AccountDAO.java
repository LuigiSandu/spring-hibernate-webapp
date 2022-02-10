package spring.dao;


import spring.entity.Account;

public interface AccountDAO {

	public void addNewAccount(Account account);

	public boolean checkUsername(String username);

	public boolean checkEmail(String email);

	public boolean checkUserAndPass(String username, String password);

	public Account getAccountForUser(String username);

	public void updateUsername(int accId, String username);

	public void updatePassword(int accId, String password);

	public void updateEmail(int accId, String email);

}
