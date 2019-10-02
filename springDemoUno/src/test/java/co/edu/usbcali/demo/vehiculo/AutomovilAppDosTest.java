package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AutomovilAppDosTest {

	@Test
	void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContextDos.xml");

		assertNotNull(applicationContext, "El objeto es nulo");
		Automovil automovil = (Automovil)applicationContext.getBean("automovil");
		assertNotNull(automovil, "El automovil es nulo");
		assertNotNull(automovil.getMotor(), "El motor es nulo");
		assertFalse(automovil.getMotor().getElectrico());
	}

}
