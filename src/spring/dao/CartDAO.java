package spring.dao;

import java.util.List;

import spring.entity.Credential;
import spring.util.ItemType;

public interface CartDAO {
	public void addItemtoCart(ItemType itemType, int accountId, int itemId);

	public List<String> getItemsList(int accId);

	public int[] getIdList(int accId);

	public List<String> getUpdatedItemsList(int accId, int itemId);

	public int[] getUpdatedIdList(int accId, int itemId);

	public void order(int accId, Credential credential);

	public boolean checkIfCartIsEmpty(int accId);
}
