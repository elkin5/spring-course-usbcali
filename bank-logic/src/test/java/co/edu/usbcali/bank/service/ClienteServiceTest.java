package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ClienteServiceTest {
	
	private final static Long clieId = 4560L;
	private final static Logger log = LoggerFactory.getLogger(ClienteServiceTest.class);
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);
		
		Cliente cliente = new Cliente();
		
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("homeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 555");
		
		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(),"El tipo de documento no existe");
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
		
		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("findById")	
	void bTest() {
		assertNotNull(clienteService,"El clienteService es nulo");
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);
		assertTrue(clienteOptional.isPresent());
		
	}
	

	@Test
	@DisplayName("update")
	@Transactional
	void cTest() {
		assertNotNull(clienteService,"El clienteService es nulo");
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);
		assertTrue(clienteOptional.isPresent(),"El cliente con id:"+clieId+" no existe");
		
		Cliente cliente = clienteOptional.get();			
		
		cliente.setActivo("N");		
		
		try {
			clienteService.update(cliente);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}		
		
	}
	
	@Test
	@DisplayName("delete")	
	void dTest() {
		assertNotNull(clienteService,"El clienteService es nulo");
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);		
		assertTrue(clienteOptional.isPresent(),"El cliente con id:"+clieId+" no existe");		
		Cliente cliente = clienteOptional.get();	
		
		try {
			clienteService.delete(cliente);
		} catch (Exception e) {
			assertNull(e,e.getMessage());
		}
				
		
	}
	
	
	@Test
	@DisplayName("findAll")	
	void eTest() {
		assertNotNull(clienteService,"El clienteService es nulo");

		List<Cliente> losClientes = clienteService.findAll();
		
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());	
		
		losClientes.forEach(cliente->{
			log.info(cliente.getNombre()
					);
			});
	}

}
