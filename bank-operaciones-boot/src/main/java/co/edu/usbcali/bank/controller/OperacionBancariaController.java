package co.edu.usbcali.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.dto.TransaccionDTO;
import co.edu.usbcali.bank.service.OperacionBancaria;

@RestController
@RequestMapping("/api/operaciones")
public class OperacionBancariaController {

	@Autowired
	OperacionBancaria operacionBancaria;

	@PostMapping("/consignar")
	public ResponseEntity<?> consignar(@RequestBody TransaccionDTO transaccionDTO) {
		try {
			Long idTransaccion = operacionBancaria.consignar(transaccionDTO.getCuenId(), transaccionDTO.getValor(),
					transaccionDTO.getUsuUsuario());

			return ResponseEntity.ok().body(idTransaccion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}
	}

	@PostMapping("/retirar")
	public ResponseEntity<?> retirar(@RequestBody TransaccionDTO transaccionDTO) {
		try {
			Long idTransaccion = operacionBancaria.retirar(transaccionDTO.getCuenId(), transaccionDTO.getValor(),
					transaccionDTO.getUsuUsuario());

			return ResponseEntity.ok().body(idTransaccion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400, e.getMessage()));
		}
	}
}
