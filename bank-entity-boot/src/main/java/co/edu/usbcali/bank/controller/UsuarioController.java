package co.edu.usbcali.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;
import co.edu.usbcali.bank.mapper.UsuarioMapper;
import co.edu.usbcali.bank.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioMapper usuarioMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO clienteDTO) {
		try {
			Usuario cliente = usuarioMapper.usuarioDTOToUsuario(clienteDTO);
			cliente = usuarioService.save(cliente);
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(cliente));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody UsuarioDTO clienteDTO) {
		try {
			Usuario cliente = usuarioMapper.usuarioDTOToUsuario(clienteDTO);
			cliente = usuarioService.update(cliente);
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(cliente));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		try {
			usuarioService.deleteById(id);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		try {
			Optional<Usuario> clienteOptional = usuarioService.findById(id);
			if (clienteOptional.isPresent() == false) {
				return null;
			}
			Usuario cliente = clienteOptional.get();
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(cliente));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		try {
			List<Usuario> listaclientes = usuarioService.findAll();

			List<UsuarioDTO> listUsuariosDTO = usuarioMapper.toUsuariosDTO(listaclientes);
			return ResponseEntity.ok().body(listUsuariosDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}

	}
}
