package spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "item_list")
public class ItemList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//CART RELATION
	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	//PLACEDORDER RELATION
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placed_order_id")
	private PlacedOrder placedOrder;
	
	// CPUS RELATION
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "item_list_cpu", joinColumns = @JoinColumn(name = "item_list_id"), inverseJoinColumns = @JoinColumn(name = "cpu_id"))
	private List<CPU> cpus ;

	// GPUS RELATION
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "item_list_gpu", joinColumns = @JoinColumn(name = "item_list_id"), inverseJoinColumns = @JoinColumn(name = "gpu_id"))
	private List<GPU> gpus;

	// HDD RELATION
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "item_list_hdd", joinColumns = @JoinColumn(name = "item_list_id"), inverseJoinColumns = @JoinColumn(name = "hdd_id"))
	private List<HDD> hdds;

	// PSUS RELATION
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "item_list_psu", joinColumns = @JoinColumn(name = "item_list_id"), inverseJoinColumns = @JoinColumn(name = "psu_id"))
	private List<PSU> psus;


	
	public void addCPUToCart(CPU cpu) {
		if(cpus == null) {
			cpus = new ArrayList<>();
		}
		cpus.add(cpu);
	}
	public void addGPUToCart(GPU gpu) {
		if(gpus == null) {
			gpus = new ArrayList<>();
		}
		gpus.add(gpu);
	}
	public void addHDDToCart(HDD hdd) {
		if(hdds == null) {
			hdds = new ArrayList<>();
		}
		hdds.add(hdd);
	}
	public void addPSUToCart(PSU psu) {
		if(psus == null) {
			psus = new ArrayList<>();
		}
		psus.add(psu);
	}

}
