package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.TipoUsuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TipoUsuarioRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(ClienteRepositoryTest.class);
	private final static Long tipoId = 4L;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(tipoUsuarioRepository);		
		assertFalse(tipoUsuarioRepository.findById(tipoId).isPresent(),"El tipo de usuario ya existe");
		
		TipoUsuario tipoUsuario = new TipoUsuario();
		
		tipoUsuario.setTiusId(tipoId);
		tipoUsuario.setNombre("Gerente");
		tipoUsuario.setActivo("S");
		
		tipoUsuarioRepository.save(tipoUsuario);		
		
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(tipoUsuarioRepository,"El tipoUsuarioRepository es nulo");
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tipoId);
		assertTrue(tipoUsuarioOptional.isPresent());		
	}
	
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)	
	void cTest() {
		assertNotNull(tipoUsuarioRepository,"El tipoUsuarioRepository es nulo");
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tipoId);
		
		assertTrue(tipoUsuarioOptional.isPresent(),"El tipo usuario con id:"+tipoId+" no existe");
		
		TipoUsuario tipoUsuario = tipoUsuarioOptional.get();			
		
		tipoUsuario.setActivo("N");		
		
		tipoUsuarioRepository.save(tipoUsuario);
		
	}
	
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)	
	void dTest() {
		assertNotNull(tipoUsuarioRepository,"El tipoUsuarioRepository es nulo");
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tipoId);
		
		assertTrue(tipoUsuarioOptional.isPresent(),"El tipo usuario con id:"+tipoId+" no existe");
			
		TipoUsuario tipoUsuario = tipoUsuarioOptional.get();			
		
		tipoUsuarioRepository.delete(tipoUsuario);		
		
	}
	
	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(tipoUsuarioRepository,"El tipoUsuarioRepository es nulo");

		List<TipoUsuario> losTiposUsuarios = tipoUsuarioRepository.findAll();
		
		assertNotNull(losTiposUsuarios);
		assertFalse(losTiposUsuarios.isEmpty());	
		
		losTiposUsuarios.forEach(tipousu->{
			log.info(tipousu.getNombre()
					);
			});
		
	}

}
