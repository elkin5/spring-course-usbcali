package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

//@Service se usa cuando es para l�gica de negocio
@Service
@Scope("singleton")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	Validator validator;

	public void validar(Cliente cliente) throws Exception {
		try {

			if (cliente == null) {
				throw new Exception("El cliente es nulo");
			}

			Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}

				throw new Exception(strMessage.toString());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente save(Cliente cliente) throws Exception {

		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == true) {
			throw new Exception("El cliente con id: " + cliente.getClieId() + " Ya existe");
		}

		if (tipoDocumentoRepository.findById(cliente.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("El tipo de documento con id: " + cliente.getClieId() + "No existe");
		}

		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente update(Cliente cliente) throws Exception {
		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con id: " + cliente.getClieId() + " No existe");
		}

		if (tipoDocumentoRepository.findById(cliente.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("El tipo de documento con id: " + cliente.getClieId() + "No existe");
		}

		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {

		return clienteRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cliente cliente) throws Exception {

		validar(cliente);

		if (clienteRepository.findById(cliente.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con id: " + cliente.getClieId() + " No existe");
		}

		cliente = findById(cliente.getClieId()).get();
		if (cliente.getCuentaRegistradas().size() > 0) {
			throw new Exception("No se puede borrar el cliente porque tiene cuentas registradas");
		}

		if (cliente.getCuentas().size() > 0) {
			throw new Exception("No se puede borrar el cliente porque tiene cuentas");
		}

		clienteRepository.delete(cliente);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {

		if (id == null || id < 1) {
			throw new Exception("El id no puede ser nulo, ni menor a uno");
		}

		if (findById(id).isPresent() == false) {
			throw new Exception("El cliente que desa eliminar no existe");
		}

		delete(findById(id).get());
	}

	@Override
	@Transactional(readOnly = false)
	public List<Cliente> findByNombre(String nombre) {	
		return clienteRepository.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Cliente> findByNombreLike(String nombre) {
		
		return clienteRepository.findByNombreLike(nombre);
	}

}
