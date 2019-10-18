package co.edu.usbcali.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.TipoDocumentoDTO;
import co.edu.usbcali.bank.mapper.TipoDocumentoMapper;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@RestController
@RequestMapping("/tipoDocumento")
@CrossOrigin("*")
public class TipoDocumentoController {

	@Autowired
	TipoDocumentoMapper tipoDocumentoMapper;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@GetMapping("findAll")
	public List<TipoDocumentoDTO> findAll() {
		List<TipoDocumento> listaTipoDocumento = tipoDocumentoRepository.findAll();
		return tipoDocumentoMapper.toTipoDocumentoDTO(listaTipoDocumento);
	}

}
