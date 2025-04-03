package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	private Chef chef;
	private Gaulois gaulois;
	private Druide druide;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		chef = new Chef("le chef", 5, village);
		village.setChef(chef);
		village.getNom();
		gaulois = new Gaulois("Le gaulois 1", 7);
		druide = new Druide("druide", 5, 6, 12);
		village.ajouterHabitant(gaulois);
		village.trouverHabitant(gaulois.getNom());
		village.donnerVillageois();
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		
	}
	
	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlPrendreEtal, "Le controleur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		Etal etalOccupe = new Etal();
		assertTrue(controlPrendreEtal.resteEtals());
		etalOccupe.occuperEtal(chef, "fleurs", 5);
		assertTrue(etalOccupe.isEtalOccupe());
		Etal etalNonOccupe = new Etal();
		assertFalse(etalNonOccupe.isEtalOccupe());
	}

	@Test
	void testPrendreEtal() {
		int numero = controlPrendreEtal.prendreEtal(chef.getNom(), "fleurs", 3);
		assertEquals(0, numero);
		int numeroNul = controlPrendreEtal.prendreEtal("Marie", "citrons", 12);
		assertEquals(-1, numeroNul);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlPrendreEtal.verifierIdentite(chef.getNom()));
		assertFalse(controlPrendreEtal.verifierIdentite(druide.getNom()));
	}

}
