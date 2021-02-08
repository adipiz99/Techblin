package model;

import java.sql.SQLException;
import java.util.Collection;

import entity.ProductBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProductModelDMTest extends TestCase {

	
	
	ProductModelDM productDao;
	ProductBean TC5_1,TC5_2,TC5_3,TC5_4,TC5_5,TC7_1,TC7_2,TC7_3,TC7_4,TC7_5;
	String TC11_1,TC11_2;
	Collection<ProductBean> prodotti;
	
	protected void setUp() throws Exception{
		productDao = new ProductModelDM();
	}
	
	
	public void testDoSaveExceptionNome() throws SQLException{
		TC5_1 = new ProductBean("Sch","RTX 3080 GPU Nvidia", 998,12);
		
		try {
			productDao.doSave(TC5_1);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionDescrizione() throws SQLException{
		TC5_2 = new ProductBean("Scheda video Nvidia","RTX", 998,12);
		
		try {
			productDao.doSave(TC5_2);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionPrezzo() throws SQLException{
		TC5_3 = new ProductBean("Scheda video Nvidia","RTX 3080 GPU Nvidia",9980000,12);
		
		try {
			productDao.doSave(TC5_3);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSaveExceptionQuantità() throws SQLException{
		TC5_4 = new ProductBean("Scheda video Nvidia","RTX 3080 GPU Nvidia",998,120000);
		
		try {
			productDao.doSave(TC5_4);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoSave() throws SQLException{
		prodotti = productDao.doRetrieveAll(null);
		int numProdotti = prodotti.size();
		TC5_5 = new ProductBean("Scheda video Nvidia","RTX 3080 GPU Nvidia",998,12);
		productDao.doSave(TC5_5);
		numProdotti++;
		prodotti = productDao.doRetrieveAll(null);
		assertEquals(numProdotti,prodotti.size());
		}
	
	//TC7_1 = new ProductBean("Processore Intel i7","Intel i7 10700K",850,1);
	public void testDoUpdateExceptionNome() throws SQLException {
		TC7_1 = new ProductBean("Proc","Intel i7 10700K",850,1);
		TC7_1.setCode(13);
		try {
			productDao.doUpdate(TC7_1);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}

	public void testDoUpdateExceptionDescrizione() throws SQLException {
		TC7_2 = new ProductBean("Processore Intel i7","Intel i7",850,1);
		TC7_2.setCode(13);
		try {
			productDao.doUpdate(TC7_2);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}
	
	public void testDoUpdateExceptionPrezzo() throws SQLException {
		TC7_3 = new ProductBean("Processore Intel i7","Intel i7 10700K",850000000,1);
		TC7_3.setCode(13);
		try {
			productDao.doUpdate(TC7_3);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}

	public void testDoUpdateExceptionQuantità() throws SQLException {
		TC7_4 = new ProductBean("Processore Intel i7","Intel i7 10700K",850,1000000);
		TC7_4.setCode(13);
		try {
			productDao.doUpdate(TC7_4);
			fail("Check not passed");
		}catch (InvalidDataException ex) {
			
		}
	}  
	
	public void testDoUpdate() throws SQLException {
		TC7_5 = new ProductBean("Processore Intel i7","Intel i7 10700K",850,1);
		TC7_5.setCode(13);
		productDao.doUpdate(TC7_5);
		ProductBean x = productDao.doRetrieveByKey("13");
		assertEquals(TC7_5.getName(),x.getName());
		
		}
	
	public void testDoRetrieveByKeyProductNotFound() throws SQLException{
		ProductBean x = new ProductBean();
		TC11_1 = "500";
		assertEquals(x,productDao.doRetrieveByKey(TC11_1));
					
		}
	
	public void testDoRetrieveByKey() throws SQLException{
		ProductBean x = new ProductBean();
		x.setName("Gigabyte GeForce RTX 2060 Gaming OC PRO");
		TC11_2 = "5";
		assertEquals(x.getName(),productDao.doRetrieveByKey(TC11_2).getName());
		}
	
	public static Test suite() {
		return new TestSuite(ProductModelDMTest.class);
	}
	}
	  
