package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.AccountDAO;
import spring.dao.CartDAO;
import spring.entity.Account;
import spring.entity.Credential;
import spring.util.ItemType;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private CartDAO cartDAO;

	@Override
	@Transactional
	public void addNewAccount(Account account) {
		accountDAO.addNewAccount(account);
	}

	@Override
	@Transactional
	public boolean checkUsername(String username) {
		return accountDAO.checkUsername(username);
	}

	@Override
	@Transactional
	public boolean checkEmail(String email) {
		return accountDAO.checkEmail(email);
	}

	@Override
	@Transactional
	public boolean checkUserAndPass(String username, String password) {
		return accountDAO.checkUserAndPass(username,password);
	}

	@Override
	@Transactional
	public Account getAccountForUser(String username) {
		return accountDAO.getAccountForUser(username);
	}

	@Override
	@Transactional
	public void addItemtoCart(ItemType itemType, int accountId, int itemId) {
		cartDAO.addItemtoCart(itemType, accountId, itemId);
		
	}

	@Override
	public List<String> getItemsList(int accId) {
		return cartDAO.getItemsList(accId);
	}

	@Override
	public int[] getIdList(int accId) {
		return cartDAO.getIdList(accId);
	}

	@Override
	@Transactional
	public List<String> getUpdatedItemsList(int accId, int itemId) {
		return cartDAO.getUpdatedItemsList(accId, itemId);
	}

	@Override
	@Transactional
	public int[] getUpdatedIdList(int accId, int itemId) {
		return cartDAO.getUpdatedIdList(accId, itemId);
	}

	@Override
	@Transactional
	public void order(int accId, Credential credential) {
		cartDAO.order(accId, credential);
	}

	@Override
	@Transactional
	public void updateUsername(int accId, String username) {
		accountDAO.updateUsername(accId, username);
		
	}

	@Override
	@Transactional
	public void updatePassword(int accId, String password) {
		accountDAO.updatePassword(accId, password);
		
	}

	@Override
	@Transactional
	public void updateEmail(int accId, String email) {
		accountDAO.updateEmail(accId, email);
		
	}

	@Override
	@Transactional
	public boolean checkIfCartIsEmpty(int accId) {
		return cartDAO.checkIfCartIsEmpty(accId);
	}

	

	

}
