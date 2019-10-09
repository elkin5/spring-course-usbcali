package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import antlr.collections.List;
import co.edu.usbcali.bank.dto.ClienteDTO;

class ClienteControllerTest {

	private final static Long clieId = 7890L;
	private final static Logger log = LoggerFactory.getLogger(ClienteControllerTest.class);
	private final static String url = "http://localhost:8080/bank-web/api/cliente/";

	@Test
	@DisplayName("save")
	void aTest() {
		RestTemplate restTemplate = new RestTemplate();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo("S");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Avenida ss 123");
		clienteDTO.setEmail("homeroJay@gmail.com");
		clienteDTO.setNombre("Homero J Simpson");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("555 55 5555");

		Object result = restTemplate.postForObject(url + "save", clienteDTO, Object.class);
		assertNotNull(result);
	}

	@Test
	@DisplayName("update")
	void bTest() {
		RestTemplate restTemplate = new RestTemplate();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo("N");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Avenida siempre viva");
		clienteDTO.setEmail("homeroJay@gmail.com");
		clienteDTO.setNombre("Homero J Simpson");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("555 55 5555");

		restTemplate.put(url + "update", clienteDTO);
	}

	@Test
	@DisplayName("findById")
	void cTest() {
		RestTemplate restTemplate = new RestTemplate();

		ClienteDTO result = restTemplate.getForObject(url + "findById/" + clieId, ClienteDTO.class);
		assertNotNull(result, "El cliente no existe");
	}

	@Test
	@DisplayName("findAll")
	void dTest() {
		RestTemplate restTemplate = new RestTemplate();

		Object[] results = restTemplate.getForObject(url + "findAll/", Object[].class);
		assertNotNull(results);
	}

	@Test
	@DisplayName("delete")
	void eTest() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url + "delete/" + clieId);
	}
}
