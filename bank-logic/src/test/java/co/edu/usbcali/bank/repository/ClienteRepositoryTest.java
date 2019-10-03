package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteRepositoryTest {

	@Autowired
	IClienteRepository clienteRepository;
	@Autowired
	ITipoDocumentoRepository tipoDocumentoRepository;

	private final static Long clieId = 4565L;

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(clienteRepository, "Objeto clienteRepository nulo");
		assertNotNull(tipoDocumentoRepository, "Objeto tipoDocumentoRepository nulo");
		assertFalse(clienteRepository.findById(clieId).isPresent());

		Cliente cliente = new Cliente();
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida ss 123");
		cliente.setEmail("homeroJay@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 55 5555");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(), "Tipo de documento no existe");
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());

		clienteRepository.save(cliente);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(clienteRepository, "Objeto null");
		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(clienteRepository, "Objeto null");
		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");

		clienteOptional.get().setActivo("N");

		clienteRepository.save(clienteOptional.get());
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(clienteRepository, "Objeto null");
		Optional<Cliente> clienteOptional = clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(), "El cliente con id " + clieId + " no existe");

		clienteRepository.delete(clienteOptional.get());
		;
	}

	private final static Logger log = LoggerFactory.getLogger(ClienteRepositoryTest.class);

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(clienteRepository, "Objeto null");
		List<Cliente> losClientes = clienteRepository.findAll();
		assertNotNull(losClientes, "Objeto losClientes null");
		assertFalse(losClientes.isEmpty());

		losClientes.forEach(cliente -> {
			log.info(cliente.getNombre());
		});
	}
}
