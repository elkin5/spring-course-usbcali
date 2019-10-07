package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoUsuario;

@Repository
@Scope("singleton")
public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public TipoUsuario save(TipoUsuario entity) {
		entityManager.persist(entity);
		return entity;		
	}

	@Override
	public Optional<TipoUsuario> findById(Long id) {
		return Optional.ofNullable(entityManager.find(TipoUsuario.class, id));
	}

	@Override
	public List<TipoUsuario> findAll() {
		return entityManager.createQuery("FROM TipoUsuario").getResultList();
	}

	@Override
	public void delete(TipoUsuario entity) {
		entityManager.remove(entity);		
	}

	@Override
	public void deleteById(Long id) {
		findById(id).ifPresent(tipoUsua->delete(tipoUsua));
		
	}

}
