package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioServiceTest {

	private final static Logger log = LoggerFactory.getLogger(UsuarioServiceTest.class);
	private final static String usua = "elkin5";

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);
		assertFalse(usuarioService.findById(usua).isPresent(), "El usuario ya existe");

		Usuario usuario = new Usuario();

		usuario.setUsuUsuario(usua);
		usuario.setNombre("elkin hurtad");
		usuario.setIdentificacion(new BigDecimal("54689"));
		usuario.setClave("knl");
		usuario.setActivo("S");

		assertTrue(tipoUsuarioRepository.findById(1L).isPresent(), "El tipo de usuario no existe");

		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(usuarioService, "El usuarioService es nulo");
		Optional<Usuario> usuarioOptional = usuarioService.findById(usua);
		assertTrue(usuarioOptional.isPresent());
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(usuarioService, "El usuarioService es nulo");
		Optional<Usuario> usuarioOptional = usuarioService.findById(usua);

		assertTrue(usuarioOptional.isPresent(), "El usuario con id:" + usua + " no existe");

		Usuario usuario = usuarioOptional.get();

		usuario.setActivo("N");

		try {
			usuarioService.update(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(usuarioService, "El usuarioService es nulo");
		Optional<Usuario> usuarioOptional = usuarioService.findById(usua);
		assertTrue(usuarioOptional.isPresent(), "El usuario con id:" + usua + " no existe");

		Usuario usuario = usuarioOptional.get();

		try {
			usuarioService.delete(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(usuarioService, "El usuarioService es nulo");

		List<Usuario> losUsuarios = usuarioService.findAll();

		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty());

		losUsuarios.forEach(usuario -> {
			log.info(usuario.getNombre());
		});
	}

}
