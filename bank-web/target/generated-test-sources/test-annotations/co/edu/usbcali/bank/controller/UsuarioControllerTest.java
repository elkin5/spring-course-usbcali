package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.dto.UsuarioDTO;
import co.edu.usbcali.bank.dto.UsuarioDTO;

class UsuarioControllerTest {

	private final static String usua = "elkin5";
	private final static Logger log = LoggerFactory.getLogger(UsuarioControllerTest.class);
	private final static String url = "http://localhost:8080/bank-web/api/usuario/";

	@Test
	@DisplayName("save")
	void aTest() {
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuUsuario(usua);
		usuarioDTO.setNombre("elkin hurtad");
		usuarioDTO.setIdentificacion(new BigDecimal("54689"));
		usuarioDTO.setClave("knl");
		usuarioDTO.setActivo("S");
		usuarioDTO.setTiusId(1L);

		Object result = restTemplate.postForObject(url + "save", usuarioDTO, Object.class);
		assertNotNull(result);
	}

	@Test
	@DisplayName("update")
	void bTest() {
		RestTemplate restTemplate = new RestTemplate();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuUsuario(usua);
		usuarioDTO.setNombre("Elkin Hurtado");
		usuarioDTO.setIdentificacion(new BigDecimal("54689"));
		usuarioDTO.setClave("hhhhh");
		usuarioDTO.setActivo("N");
		usuarioDTO.setTiusId(1L);

		restTemplate.put(url + "update", usuarioDTO);
	}

	@Test
	@DisplayName("findById")
	void cTest() {
		RestTemplate restTemplate = new RestTemplate();

		UsuarioDTO result = restTemplate.getForObject(url + "findById/" + usua, UsuarioDTO.class);
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
		restTemplate.delete(url + "delete/" + usua);
	}

}
