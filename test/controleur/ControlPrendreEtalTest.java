package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	private Chef chef;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		chef = new Chef("le chef", 5, village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		
	}
	
	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlPrendreEtal, "Le controleur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		fail("Not yet implemented");
	}

	@Test
	void testPrendreEtal() {
		fail("Not yet implemented");
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlPrendreEtal.verifierIdentite(chef.getNom()));
	}

}
