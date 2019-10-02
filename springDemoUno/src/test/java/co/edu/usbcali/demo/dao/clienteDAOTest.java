package co.edu.usbcali.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContextDAO.xml")
class clienteDAOTest {

	private final static Logger log = LoggerFactory.getLogger(clienteDAOTest.class);

	@Autowired
	private ClienteDAO clienteDAO;

	@Test
	void test() {

		assertNotNull(clienteDAO, "El objeto es nulo");
	}

}
