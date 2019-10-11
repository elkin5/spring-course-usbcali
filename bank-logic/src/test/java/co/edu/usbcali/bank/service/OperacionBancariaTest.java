package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class OperacionBancariaTest {

	private final static Logger log = LoggerFactory.getLogger(OperacionBancariaTest.class);

	@Autowired
	OperacionBancaria operacionBancaria;

	@Test
	@DisplayName("retirar")
	void retirarTest() {
		try {
			assertNotNull(operacionBancaria, "Objeto Nulo");
			String cuentaId = "4640-0341-9387-5781";
			BigDecimal valor = new BigDecimal(150000);
			String usuUsuario = "aarelesio";
			Long numeroTransaccion;
			numeroTransaccion = operacionBancaria.retirar(cuentaId, valor, usuUsuario);
			assertNotNull(numeroTransaccion, "Objeto Nulo");
			log.info("Id: " + numeroTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e);
		}
	}

	@Test
	@DisplayName("consignar")
	void consignarTest() {
		try {
			assertNotNull(operacionBancaria, "Objeto Nulo");
			String cuentaId = "4640-0341-9387-5781";
			BigDecimal valor = new BigDecimal(5000000);
			String usuUsuario = "aarelesio";
			Long numeroTransaccion;
			numeroTransaccion = operacionBancaria.consignar(cuentaId, valor, usuUsuario);
			assertNotNull(numeroTransaccion, "Objeto Nulo");
			log.info("Id: " + numeroTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e);
		}
	}

	@Test
	@DisplayName("trasferir")
	void trasferenciaTest() {
		try {
			assertNotNull(operacionBancaria, "Objeto Nulo");
			String cuentaOrigen = "4640-0341-9387-5781";
			String cuentaDestino = "0026-2816-9564-1013";
			BigDecimal valor = new BigDecimal(5000000);
			String usuUsuario = "aarelesio";
			Long numeroTransaccion;
			numeroTransaccion = operacionBancaria.trasferencia(cuentaOrigen, cuentaDestino, valor, usuUsuario);
			assertNotNull(numeroTransaccion, "Objeto Nulo");
			log.info("Id: " + numeroTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e);
		}
	}
}
