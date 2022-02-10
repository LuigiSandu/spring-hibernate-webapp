package spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gpu")
@Setter
@Getter
@NoArgsConstructor

public class GPU {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String description;

	// ITEMLIST RELATION
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "item_list_gpu", joinColumns = @JoinColumn(name = "gpu_id"), inverseJoinColumns = @JoinColumn(name = "item_list_id"))
	private List<ItemList> itemlists;

	public GPU(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "GPU [id=" + id + ", description=" + description + "]";
	}

}
