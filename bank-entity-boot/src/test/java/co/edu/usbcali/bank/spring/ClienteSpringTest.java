package co.edu.usbcali.bank.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteSpringTest {

	@PersistenceContext
	EntityManager entityManager;

	private final static Long clieId = 4565L;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(entityManager, "El objeto entityManager esta nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "El cliente con id " + clieId + " ya existe");
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida ss 123");
		cliente.setEmail("homeroJay@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 55 5555");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "Objeto null");
		cliente.setTipoDocumento(tipoDocumento);

		entityManager.persist(cliente);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(entityManager, "Objeto null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(entityManager, "Objeto null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		cliente.setActivo("N");

		entityManager.merge(cliente);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(entityManager, "Objeto null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		entityManager.remove(cliente);
	}

	private final static Logger log = LoggerFactory.getLogger(ClienteSpringTest.class);

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(entityManager, "Objeto null");
		String jpql = "SELECT cli FROM Cliente cli";
		Query query = entityManager.createQuery(jpql);
		List<Cliente> losClientes = query.getResultList();
		assertNotNull(losClientes, "Objeto losClientes null");
		assertFalse(losClientes.isEmpty());
		
		losClientes.forEach(cliente -> {
			log.info(cliente.getNombre());
		});
	}

}