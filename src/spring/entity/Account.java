package spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "account")

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "account_username")
	private String username;
	
	@Column(name = "account_password")
	private String password;
	
	@Column(name = "account_email")
	private String email;

	// CART RELATION
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private Cart cart = new Cart();

	// CREDENTIALS RELATION
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Credential> credentials;

	// PLACEDORDER RELATION
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<PlacedOrder> placedOrders;

	public Account(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public void addCredentialToAccount(Credential credential) {
		if (credentials == null) {
			credentials = new ArrayList<>();
		}
		credentials.add(credential);
		credential.setAccount(this);

	}
	public void addPlacedOrder(PlacedOrder order) {
		if(placedOrders == null) {
			placedOrders = new ArrayList<>();
		}
		placedOrders.add(order);
	}  
	
	
}
