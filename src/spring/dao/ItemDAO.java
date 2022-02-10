package spring.dao;

import java.util.List;

import spring.entity.CPU;
import spring.entity.GPU;
import spring.entity.HDD;
import spring.entity.PSU;

public interface ItemDAO {
public List<CPU> getCPUList();

public List<GPU> getGPUList();

public List<HDD> getHDDList();
	
public List<PSU> getPSUList();
	

}
