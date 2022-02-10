package spring.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Setter
@Getter

public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// ACCOUNT RELATION
	@OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
	private Account account;
	
	//ITEMLIST RELATION
	@OneToOne(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ItemList itemList;
	
public Cart() {
	this.itemList = new ItemList();
	itemList.setCart(this);
}
	}

	
	

