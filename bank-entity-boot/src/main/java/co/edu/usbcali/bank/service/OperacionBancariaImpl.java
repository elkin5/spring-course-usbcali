package co.edu.usbcali.bank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cuenta;
import co.edu.usbcali.bank.domain.TipoTransaccion;
import co.edu.usbcali.bank.domain.Transaccion;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.CuentaRepository;
import co.edu.usbcali.bank.repository.TipoTransaccionRepository;
import co.edu.usbcali.bank.repository.TransaccionRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class OperacionBancariaImpl implements OperacionBancaria {

	@Autowired
	CuentaRepository cuentaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TransaccionRepository transaccionReporitory;

	@Autowired
	TipoTransaccionRepository tipoTransaccionReporitory;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		validar(cuenId, valor, usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		if (cuenta.getSaldo().compareTo(valor) == -1) {
			throw new Exception("La cuenta con id " + cuenId + " no tiene fondos suficientes");
		}

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);

		TipoTransaccion tipoTransaccion = tipoTransaccionReporitory.findById(1L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);

		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
		cuentaRepository.save(cuenta);

		transaccion = transaccionReporitory.save(transaccion);

		return transaccion.getTranId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		validar(cuenId, valor, usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);

		TipoTransaccion tipoTransaccion = tipoTransaccionReporitory.findById(2L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);

		cuenta.setSaldo(cuenta.getSaldo().add(valor));
		cuentaRepository.save(cuenta);

		transaccion = transaccionReporitory.save(transaccion);

		return transaccion.getTranId();
	}

//	insert into CUENTA (CUEN_ID, SALDO, CLAVE, ACTIVA, CLIE_ID) values ('9999-9999-9999-9999', 0000000, 'Banco', 'S', 1);
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long trasferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		retirar(cuenIdOrigen, valor, usuUsuario);
		consignar(cuenIdDestino, valor, usuUsuario);
		retirar(cuenIdOrigen, new BigDecimal(2000), usuUsuario);
		consignar("9999-9999-9999-9999", new BigDecimal(2000), usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenIdOrigen).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);

		TipoTransaccion tipoTransaccion = tipoTransaccionReporitory.findById(3L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setValor(valor);

		cuenta.setSaldo(cuenta.getSaldo().add(valor));
		cuentaRepository.save(cuenta);

		transaccion = transaccionReporitory.save(transaccion);

		return transaccion.getTranId();
	}

	private void validar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		if (cuenId == null || cuenId.trim().equals("") == true) {
			throw new Exception("El numero de la cuenta es obligatorio");
		}

		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("El valor debe ser mayor que cero");
		}

		if (usuUsuario == null || usuUsuario.trim().equals("") == true) {
			throw new Exception("El numero de la cuenta es obligatorio");
		}

		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuenId);
		if (cuentaOptional.isPresent() == false) {
			throw new Exception("La cuenta con id " + cuenId + " no existe");
		}

		if (cuentaOptional.get().getActiva().equals("N") == true) {
			throw new Exception("La cuenta con id " + cuenId + " no se encuentra activa");
		}

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuUsuario);
		if (usuarioOptional.isPresent() == false) {
			throw new Exception("El usuario con id " + cuenId + " no existe");
		}

		if (usuarioOptional.get().getActivo().equals("N") == true) {
			throw new Exception("el usuario con id " + cuenId + " no se encuentra activo");
		}
	}

}
