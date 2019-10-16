package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

class ClienteJPATest {

	private final static Long clieId = 4565L;

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	@BeforeEach
	void beforeEach() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bank-logic");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterEach
	void afterEach() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(entityManager, "Objeto null");
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

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(entityManager, "Objeto null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

	}

	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(entityManager, "Objeto null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(entityManager, "Objeto null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente con id " + clieId + " no existe");

		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
	}

	private final static Logger log = LoggerFactory.getLogger(ClienteJPATest.class);

	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(entityManager, "Objeto null");
		String jpql = "SELECT cli FROM Cliente cli";
		Query query = entityManager.createQuery(jpql);
		List<Cliente> losClientes = query.getResultList();
		assertNotNull(losClientes, "Objeto losClientes null");
		assertFalse(losClientes.isEmpty());

//		for (Cliente cliente : losClientes) {
//			log.info(cliente.getNombre());
//		}

		losClientes.forEach(cliente -> {
			log.info(cliente.getNombre());
		});
	}
}
