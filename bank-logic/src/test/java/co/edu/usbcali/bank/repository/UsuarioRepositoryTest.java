package co.edu.usbcali.bank.repository;

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


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(UsuarioRepositoryTest.class);
	
	private final static String usua = "c4rl0t0";
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		assertFalse(usuarioRepository.findById(usua).isPresent(),"El usuario ya existe");
		
		Usuario usuario = new Usuario();
		
		usuario.setUsuUsuario(usua);
		usuario.setNombre("Carloto Picons");
		usuario.setIdentificacion(new BigDecimal("54689"));
		usuario.setClave("c0ntr4");
		usuario.setActivo("S");
		
		assertTrue(tipoUsuarioRepository.findById(1L).isPresent(),"El tipo de usuario no existe");
		
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
		usuarioRepository.save(usuario);		
		
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(usuarioRepository,"El usuarioRepository es nulo");
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usua);
		assertTrue(usuarioOptional.isPresent());		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)	
	void cTest() {
		assertNotNull(usuarioRepository,"El usuarioRepository es nulo");
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usua);
		
		assertTrue(usuarioOptional.isPresent(),"El usuario con id:"+usua+" no existe");
		
		Usuario usuario = usuarioOptional.get();			
		
		usuario.setActivo("N");		
		
		usuarioRepository.save(usuario);
		
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)	
	void dTest() {
		assertNotNull(usuarioRepository,"El usuarioRepository es nulo");
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usua);		
		assertTrue(usuarioOptional.isPresent(),"El usuario con id:"+usua+" no existe");
		
		Usuario usuario = usuarioOptional.get();
		
		usuarioRepository.delete(usuario);		
		
	}
	
	
	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(usuarioRepository,"El usuarioRepository es nulo");

		List<Usuario> losUsuarios = usuarioRepository.findAll();
		
		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty());	
		
		losUsuarios.forEach(usuario->{
			log.info(usuario.getNombre()
					);
			});
		
	}
	
	

}
