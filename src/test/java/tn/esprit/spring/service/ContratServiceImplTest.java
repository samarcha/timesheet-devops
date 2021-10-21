package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.ContratServiceImpl;
import tn.esprit.spring.services.IContratService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ContratServiceImplTest {
	@Autowired
	IContratService cs;

	
	public void testRetrieveAllContrats() {
	List<Contrat> listContrats = cs.retrieveAllContrats();
	Assertions.assertEquals(16, listContrats.size());
	}
	
	
	public void testAddContrat() throws ParseException, java.text.ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2020-10-20");
		Contrat c = new Contrat (d,"CDI", 20);
		Contrat contratAdded = cs.addContrat(c);
		Assertions.assertEquals(c.getReference(), contratAdded.getReference());
	}
		
	
	public void testUpdateContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2011-12-6");
		Contrat c = new Contrat (d,"CDIII", 12);
		Contrat contratUpdated = cs.addContrat(c);
		Assertions.assertEquals(c.getReference(), contratUpdated.getReference());
		
		
	}
	
	public void testDeleteContrat(){
		cs.deleteContrat(2);
		Assertions.assertNull(cs.retrieveContrat(2));
		
	}	
	
	private static final Logger L = LogManager.getLogger(ContratServiceImpl.class);
	
	@Test
	public void testAll(){
		try{
			L.info("In testAll()");
			testRetrieveAllContrats();
			testAddContrat();
			testUpdateContrat();
			testDeleteContrat();
			L.info("Out of testAll()");
		}catch(Exception e){
			L.error("Out of testAll()with error:" +e);
		}
		
	}
	

}
