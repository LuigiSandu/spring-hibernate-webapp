package spring.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "placed_order")
public class PlacedOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// ACCOUNT RELATIONSHIP
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;

	// CREDENTIAL RELATIONSHIP
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id")
	private Credential credential;
	
	//ITEMLIST RELATION
	@OneToOne(mappedBy = "placedOrder", cascade = CascadeType.ALL)
	private ItemList itemList;
	

}
