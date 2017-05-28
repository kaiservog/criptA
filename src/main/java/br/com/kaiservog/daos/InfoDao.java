package br.com.kaiservog.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kaiservog.models.Info;

@Repository
public class InfoDao {

	@PersistenceContext
	private EntityManager manager;

	public Info search(String service) {
		return manager.createQuery("select i from Info i where i.service = :service", Info.class)
				.setParameter("service", service).getSingleResult();
	}

	public void updateOrSave(Info info) {
		Info infoDb = null;
		try {
			infoDb = manager.createQuery("select i from Info i where i.service = :service", Info.class)
					.setParameter("service", info.getService()).getSingleResult();
		} catch (NoResultException e) {
			infoDb = info;
		}

		infoDb.setService(info.getService());
		infoDb.setValue(info.getValue());

		manager.persist(infoDb);
	}
	
	

}
