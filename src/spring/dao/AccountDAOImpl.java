package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.entity.Account;

@Repository
@SuppressWarnings("unchecked")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addNewAccount(Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.save(account);
	}

	@Override
	public boolean checkUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Account> accounts = session.createQuery("from Account").getResultList();
		for (Account acc : accounts) {
			if (acc.getUsername().equals(username))
				return true;
		}
		return false;
	}

	@Override
	public boolean checkEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		List<Account> accounts = session.createQuery("from Account").getResultList();
		for (Account acc : accounts) {
			if (acc.getEmail().equals(email))
				return true;
		}
		return false;
	}

	@Override
	public boolean checkUserAndPass(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Account account = new Account();
		List<Account> accounts = session.createQuery("from Account").getResultList();
		//1.Check if the username exists in the database.
		boolean usernameFound = false;
		for (Account acc : accounts) {
			if (acc.getUsername().equals(username)) {
				//2.Get the account from the database coressponding to the username.
				account = session.get(Account.class, acc.getId());
				usernameFound = true;
				break;
			}
		}
		if(account.getPassword()!=null) {
			//3.Check if the passwords match.
			if (account.getPassword().equals(password) && usernameFound) {
				return true;
			}
		}
		

		return false;
	}

	@Override
	public Account getAccountForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Account> accounts = session.createQuery("from Account").getResultList();
		for (Account acc : accounts) {
			if (acc.getUsername().equals(username))
				return acc;
		}
		return null;
	}

	@Override
	public void updateUsername(int accId, String username) {
		Session session = sessionFactory.getCurrentSession();
		Account acc = session.get(Account.class, accId);
		acc.setUsername(username);
	}

	@Override
	public void updatePassword(int accId, String password) {
		Session session = sessionFactory.getCurrentSession();
		Account acc = session.get(Account.class, accId);
		acc.setPassword(password);
	}

	@Override
	public void updateEmail(int accId, String email) {
		Session session = sessionFactory.getCurrentSession();
		Account acc = session.get(Account.class, accId);
		acc.setEmail(email);
	}
}
