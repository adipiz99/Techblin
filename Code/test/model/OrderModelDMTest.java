package model;

import java.sql.SQLException;
import java.util.Collection;

import entity.OrderBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OrderModelDMTest extends TestCase {

	
	OrderModelDM orderDao;
	OrderBean TC8_1,TC8_2,TC8_3,TC8_4,TC8_5,TC8_6,TC8_7,TC8_8,TC8_9,TC8_11;
	Collection<OrderBean>ordini;
	
	
	public void setUp() {
		orderDao = new OrderModelDM();
	}
	
	//(Mario, Rossi, mariorossi@gmail.com, acquirente@example.it, Via Roma, Chieti, 66100, "Carta","")
	public void testDosaveExceptionNome() throws SQLException {
		TC8_1 = new OrderBean("Mari0","Rossi","acquirente@example.it","Via Roma","66100","Chieti","Chieti","Carta","Marion Rossi 121365478545 12/2022 4444");
		
		try {
			orderDao.doSave(TC8_1);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
		
	}
	
	public void testDosaveExceptionCognome() throws SQLException {
		TC8_2 = new OrderBean("Mario","Ross1","acquirente@example.it","Via Roma","66100","Chieti","Chieti","Carta","Marion Rossi 121365478545 12/2022 4444");
		try {
			orderDao.doSave(TC8_2);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
		
	}
	
	public void testDosaveExceptionEmail() throws SQLException {
		TC8_3 = new OrderBean("Mario","Rossi","acquirente@","Via Roma","66100","Chieti","Chieti","Carta","Marion Rossi 121365478545 12/2022 4444");
		try {
			orderDao.doSave(TC8_3);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDosaveExceptionIndirizzo() throws SQLException {
		TC8_4 = new OrderBean("Mario","Rossi","acquirente@example.it","Via Rom!","66100","Chieti","Chieti","Carta","Marion Rossi 121365478545 12/2022 4444");
		try {
			orderDao.doSave(TC8_4);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDosaveExceptionProvincia() throws SQLException {
		TC8_5 = new OrderBean("Mario","Rossi","acquirente@example.it","Via Roma","66100","Chieti","Chiet!","Carta","Marion Rossi 121365478545 12/2022 4444");
		try {
			orderDao.doSave(TC8_5);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDosaveExceptionCittà() throws SQLException {
		TC8_6 = new OrderBean("Mario","Rossi","acquirente@example.it","Via Roma","66100","Chiet!","Chieti","Carta","Marion Rossi 121365478545 12/2022 4444");
		try {
			orderDao.doSave(TC8_6);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDosaveExceptionCap() throws SQLException {
		TC8_7 = new OrderBean("Mario","Rossi","acquirente@example.it","Via Roma","66y","Chieti","Chieti","Carta","Marion Rossi 121365478545 12/2022 4444");
		try {
			orderDao.doSave(TC8_7);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDosaveExceptionDatiPagamento() throws SQLException {
		TC8_8 = new OrderBean("Mario","Rossi","acquirente@example.it","Via Roma","66100","Chieti","Chieti","Carta","");
		try {
			orderDao.doSave(TC8_8);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDosave() throws SQLException {
		ordini = orderDao.doRetrieveAll(null);
		int numOrdini = ordini.size();
		TC8_11 = new OrderBean("Mario","Rossi","acquirente@example.it","Via Roma","66100","Chieti","Chieti","Carta","Mario Rossi 4694412611557321 12/2022");
		orderDao.doSave(TC8_11);
		numOrdini++;
		ordini = orderDao.doRetrieveAll(null);
		assertEquals(numOrdini,ordini.size());
		}
	
	public static Test suite() {
		return new TestSuite(OrderModelDMTest.class);
	}
	}
	
	
