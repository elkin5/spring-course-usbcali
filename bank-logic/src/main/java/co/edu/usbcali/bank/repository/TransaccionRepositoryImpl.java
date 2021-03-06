package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Transaccion;

@Repository
@Scope("singleton")
public class TransaccionRepositoryImpl implements TransaccionRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Transaccion save(Transaccion entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<Transaccion> findById(Long id) {
		Transaccion transaccion = entityManager.find(Transaccion.class, id);
		Optional<Transaccion> optional = Optional.ofNullable(transaccion);
		return optional;
	}

	@Override
	public List<Transaccion> findAll() {
		return entityManager.createQuery("FROM Transaccion", Transaccion.class).getResultList();
	}

	@Override
	public void delete(Transaccion entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		Optional<Transaccion> transaccionOptional = findById(id);
		transaccionOptional.ifPresent(transaccion -> {
			delete(transaccion);
		});
	}

}
