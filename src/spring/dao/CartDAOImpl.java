package spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Account;
import spring.entity.CPU;
import spring.entity.Cart;
import spring.entity.Credential;
import spring.entity.GPU;
import spring.entity.HDD;
import spring.entity.ItemList;
import spring.entity.PSU;
import spring.entity.PlacedOrder;
import spring.util.ItemType;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// left: account id; right: list of items for account id
	private HashMap<Integer, List<String>> items = new HashMap<>();

	// row: id of each item in the database, used for displaying images in view;
	// column: account id
	private int[][] ids = new int[1000][1000];

	@Override
	public void addItemtoCart(ItemType itemType, int accountId, int itemId) {
		Session session = sessionFactory.getCurrentSession();
		ItemList itemList = session.get(ItemList.class, accountId);
		switch (itemType) {
		case CPU: {
			// getting the cpu item from the db and adding it to the itemList
			CPU cpu = getCPU(itemId);
			itemList.addCPUToCart(cpu);

			// 1.make a temp list of items 2. If the hashmap list with the accountId key is
			// not null,
			// get it and add it to the temp list 3. Add the new element in the temp list
			// 4. add the updated temp list back into the hashmap with the account key

			List<String> tempItems = new ArrayList<>();
			try {
				if (items.get(accountId) != null)
					tempItems = items.get(accountId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempItems.add(cpu.getDescription());
			items.put(accountId, tempItems);
			// 5.for each templist entry, add/ the item id from the db
			ids[tempItems.size()][accountId] = itemId;
			break;
		}
		case GPU: {
			GPU gpu = getGPU(itemId);
			itemList.addGPUToCart(gpu);

			List<String> tempItems = new ArrayList<>();
			try {
				if (items.get(accountId) != null)
					tempItems = items.get(accountId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempItems.add(gpu.getDescription());
			items.put(accountId, tempItems);
			ids[tempItems.size()][accountId] = itemId;
			break;
		}
		case HDD: {
			HDD hdd = getHDD(itemId);
			itemList.addHDDToCart(hdd);

			List<String> tempItems = new ArrayList<>();
			try {
				if (items.get(accountId) != null)
					tempItems = items.get(accountId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempItems.add(hdd.getDescription());
			items.put(accountId, tempItems);
			ids[tempItems.size()][accountId] = itemId;
			break;
		}
		case PSU: {
			PSU psu = getPSU(itemId);
			itemList.addPSUToCart(psu);

			List<String> tempItems = new ArrayList<>();
			try {
				if (items.get(accountId) != null)
					tempItems = items.get(accountId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempItems.add(psu.getDescription());
			items.put(accountId, tempItems);
			ids[tempItems.size()][accountId] = itemId;
			break;
		}
		}

	}

	@Override
	public List<String> getItemsList(int accId) {
		List<String> tempItems = items.get(accId);
		return tempItems;
	}

	@Override
	public int[] getIdList(int accId) {
		int[] tempIds = new int[1000];
		List<String> tempList = items.get(accId);
		try {
			for (int i = 1; i <= tempList.size(); i++)
				tempIds[i] = ids[i][accId];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempIds;
	}

	@Override
	public List<String> getUpdatedItemsList(int accId, int itemId) {
		//1.Get the list for the account.
		List<String> tempList = items.get(accId);
		//2.Delete item from the database.
		deleteItemFromDB(tempList.get(itemId - 1), accId);
		//3.Remove item from the hashmap list
		tempList.remove(itemId - 1);
		//4.Put the tempList back into the hashMap.
		items.put(accId, tempList);
		return tempList;
	}

	@Override
	public int[] getUpdatedIdList(int accId, int itemId) {
		// get current length of list
		int length = items.get(accId).size();
		// shift the elements.
		for (int i = itemId; i <= length; i++) {
			ids[i][accId] = ids[i + 1][accId];
		}
		// return new array
		int[] tempIds = new int[1000];
		tempIds = getIdList(accId);

		return tempIds;
	}

	@Override
	public void order(int accId, Credential credential) {
		Session session = sessionFactory.getCurrentSession();
		//1.Get new Itemlist for oder.
		ItemList itemList = createNewItemListForOrder(accId);
		//2.Empty the hashmap list coressponding to cart.
		items.get(accId).clear();
		Account acc = session.get(Account.class, accId);
		//3.Make new placedorder and set acc, itemlist and credential
		PlacedOrder placedOrder = new PlacedOrder();
		placedOrder.setAccount(acc);
		placedOrder.setItemList(itemList);
		placedOrder.setCredential(credential);
		acc.addCredentialToAccount(credential);
		itemList.setPlacedOrder(placedOrder);
		session.saveOrUpdate(placedOrder);
	}

	@Override
	public boolean checkIfCartIsEmpty(int accId) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = session.get(Cart.class, accId);
		ItemList itemList = cart.getItemList();
		if (itemList.getCpus().isEmpty() && itemList.getGpus().isEmpty() && itemList.getHdds().isEmpty()
				&& itemList.getPsus().isEmpty())
			return true;
		return false;
	}

	@Transactional
	public ItemList createNewItemListForOrder(int accId) {
		Session session = sessionFactory.getCurrentSession();
		//1.Make new itemlist for order and copy all the cart items inside of it.
		ItemList itemList = session.get(ItemList.class, accId);
		ItemList tempItemList = new ItemList();
		for (CPU cpu : itemList.getCpus())
			tempItemList.addCPUToCart(cpu);
		for (GPU gpu : itemList.getGpus())
			tempItemList.addGPUToCart(gpu);
		for (HDD hdd : itemList.getHdds())
			tempItemList.addHDDToCart(hdd);
		for (PSU psu : itemList.getPsus())
			tempItemList.addPSUToCart(psu);
		//2.Empty cart
		itemList.getCpus().clear();
		itemList.getGpus().clear();
		itemList.getHdds().clear();
		itemList.getPsus().clear();
		return tempItemList;
	}

	@Transactional
	public void deleteItemFromDB(String item, int accId) {
		Session session = sessionFactory.getCurrentSession();
		ItemList itemList = session.get(ItemList.class, accId);
		//1.Check type of item
		if (item.substring(0, 3).equals("CPU")) {
			//2.Iterate over a temp list and delete the item.
			List<CPU> cpus = itemList.getCpus();
			for (CPU cpu : cpus) {
				if (cpu.getDescription().equals(item)) {
					itemList.getCpus().remove(cpu);
					break;
				}
			}
		}
		if (item.substring(0, 3).equals("GPU")) {
			List<GPU> gpus = itemList.getGpus();
			for (GPU gpu : gpus) {
				if (gpu.getDescription().equals(item)) {
					itemList.getGpus().remove(gpu);
					break;
				}
			}
		}
		if (item.substring(0, 3).equals("HDD")) {
			List<HDD> hdds = itemList.getHdds();
			for (HDD hdd : hdds) {
				if (hdd.getDescription().equals(item)) {
					itemList.getHdds().remove(hdd);
					break;
				}
			}
		}
		if (item.substring(0, 3).equals("PSU")) {
			List<PSU> psus = itemList.getPsus();
			for (PSU psu : psus) {
				if (psu.getDescription().equals(item)) {
					itemList.getPsus().remove(psu);
					break;
				}
			}
		}

	}

	@Transactional
	private CPU getCPU(int id) {
		Session session = sessionFactory.getCurrentSession();
		CPU cpu = session.get(CPU.class, id);
		return cpu;
	}

	@Transactional
	private GPU getGPU(int id) {
		Session session = sessionFactory.getCurrentSession();
		GPU gpu = session.get(GPU.class, id);
		return gpu;
	}

	@Transactional
	private HDD getHDD(int id) {
		Session session = sessionFactory.getCurrentSession();
		HDD hdd = session.get(HDD.class, id);
		return hdd;
	}

	@Transactional
	private PSU getPSU(int id) {
		Session session = sessionFactory.getCurrentSession();
		PSU psu = session.get(PSU.class, id);
		return psu;
	}
}
