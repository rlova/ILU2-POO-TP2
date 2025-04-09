package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	private Chef chef;
	private Gaulois gaulois;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("Le village des bisounours", 10, 2);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		chef = new Chef("le chef", 5, village);
		village.setChef(chef);
		gaulois = new Gaulois("le Gaulois", 5);
		village.getNom();
		village.trouverHabitant(gaulois.getNom());
		village.donnerVillageois();
	}
	
	@Test
	void testControlVerifierIdentite() {
		assertNotNull(controlVerifierIdentite, "Le constructeur ne renvoie pas null");
	}

	@Test
	void testVerifierIdentite() { 
		assertTrue(controlVerifierIdentite.verifierIdentite(chef.getNom()));
		assertFalse(controlVerifierIdentite.verifierIdentite(gaulois.getNom()));
	}

}
