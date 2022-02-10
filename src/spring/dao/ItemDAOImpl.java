package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.CPU;
import spring.entity.GPU;
import spring.entity.HDD;
import spring.entity.PSU;

@Repository
@SuppressWarnings("unchecked")
public class ItemDAOImpl implements ItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<CPU> getCPUList() {
		Session session = sessionFactory.getCurrentSession();
		List<CPU> cpus = session.createQuery("from CPU").getResultList();
		return cpus;
	}

	@Override
	@Transactional
	public List<GPU> getGPUList() {
		Session session = sessionFactory.getCurrentSession();
		List<GPU> gpus = session.createQuery("from GPU").getResultList();
		return gpus;
	}
	@Override
	@Transactional
	public List<HDD> getHDDList() {
		Session session = sessionFactory.getCurrentSession();
		List<HDD> hdds = session.createQuery("from HDD").getResultList();
		return hdds;
	}

	@Override
	@Transactional
	public List<PSU> getPSUList() {
		Session session = sessionFactory.getCurrentSession();
		List<PSU> psus = session.createQuery("from PSU").getResultList();
		return psus;
	}
}
