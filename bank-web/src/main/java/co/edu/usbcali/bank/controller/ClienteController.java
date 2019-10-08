package co.edu.usbcali.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;
import co.edu.usbcali.bank.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/findById/{id}")
	public ClienteDTO findById(@PathVariable("id") Long id) {
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		if(clienteOptional.isPresent() == false) {
			return null;
		}
		return null;
	}
}
