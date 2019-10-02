package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;

class UsuarioJPATest {

	private final static String usuUsuario = "elkinh";

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
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNull(usuario, "El cliente con id " + usuUsuario + " ya existe");

		usuario = new Usuario();
		usuario.setUsuUsuario("elkinh");
		usuario.setActivo("S");
		usuario.setClave("123");
		usuario.setIdentificacion(new BigDecimal(1234));
		usuario.setNombre("Elkin Hurtado");

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "Objeto null");
		usuario.setTipoUsuario(tipoUsuario);

		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(entityManager, "Objeto null");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "El cliente con id " + usuUsuario + " no existe");

	}

	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(entityManager, "Objeto null");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "El cliente con id " + usuUsuario + " no existe");

		usuario.setActivo("N");

		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(entityManager, "Objeto null");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "El cliente con id " + usuUsuario + " no existe");

		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
	}

	private final static Logger log = LoggerFactory.getLogger(ClienteJPATest.class);

	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(entityManager, "Objeto null");
		String jpql = "SELECT usu FROM Usuario usu";
		Query query = entityManager.createQuery(jpql);
		List<Usuario> losUsuarios = query.getResultList();
		assertNotNull(losUsuarios, "Objeto losClientes null");
		assertFalse(losUsuarios.isEmpty());

//		for (Cliente cliente : losClientes) {
//			log.info(cliente.getNombre());
//		}

		losUsuarios.forEach(usuario -> {
			log.info(usuario.getNombre());
		});
	}

}
