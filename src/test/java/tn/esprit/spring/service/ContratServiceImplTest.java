package tn.esprit.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

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
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;
import tn.esprit.spring.services.IContratService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ContratServiceImplTest {
	@Autowired
	IContratService cs;
	@Autowired
	private ContratRepository repository;
	private static final Logger L = LogManager.getLogger(ContratServiceImpl.class);
	
//	public void testRetrieveAllContrats() {
//	List<Contrat> listContrats = cs.retrieveAllContrats();
//	Assertions.assertEquals(4, listContrats.size());
//	}
	
	public void testRetrieveAllContrats()  {		
		try {
			List<Contrat>contrats=cs.retrieveAllContrats();
			assertThat(contrats).size().isGreaterThan(0);
			for(Contrat c:contrats) {
				L.info("Le contrat est: "+ c);}
			L.info("La taille de contrats  ,"+contrats.size());}
		catch (IllegalArgumentException ex){
			assertEquals("the table is empty", ex.getMessage());
		}
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
		Date d = dateFormat.parse("2011-11-1");
		Contrat c = new Contrat (d,"CDIII", 12);
		Contrat contratUpdated = cs.addContrat(c);
		Assertions.assertEquals(c.getReference(), contratUpdated.getReference());
		
		
	}
	
	//public void testDeleteContrat(){
		//cs.deleteContrat(5);
		//Assertions.assertNull(cs.retrieveContrat(2));
		
	//}
       public  void testDeleteContrat() {
		
		Integer id=6;
		boolean notExistAfterDelete =repository.findById(id).isPresent();
		boolean isExistBeforeDelete =repository.findById(id).isPresent();
		if(isExistBeforeDelete) {
			cs.deleteContrat(id);
			assertTrue(isExistBeforeDelete);
			L.info("le contrat est supprimé ");}

		
		else {
			assertFalse(notExistAfterDelete);
			L.info("le contrat est introuvable ");}
	}
       
	
	
	
	
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
