package spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "credential")

public class Credential {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "cvc")
	private String CVC;
	
	//PLACEDORDER RELATION
	@OneToMany(mappedBy = "credential")
	private List<PlacedOrder> placedOrder;
	
	//ACCOUNT RELATION
	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;
	
	public Credential(String cardNumber, String cvc) {
		super();
		this.cardNumber = cardNumber;
		this.CVC = cvc;
	}
	
	
}
