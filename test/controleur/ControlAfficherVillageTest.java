package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private ControlAfficherVillage controlAfficherVillage;
	private Village village;
	private Chef chef;
	private Gaulois gaulois;
	private Druide druide;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlAfficherVillage = new ControlAfficherVillage(village);
		chef = new Chef("le chef", 5, village);
		village.setChef(chef);
		village.getNom();
		gaulois = new Gaulois("Le gaulois 1", 7);
		druide = new Druide("druide", 5, 6, 12);
		village.ajouterHabitant(gaulois);
		village.ajouterHabitant(druide);
	}

	@Test
	void testControlAfficherVillage() {
		assertNotNull(controlAfficherVillage, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		String[] noms = controlAfficherVillage.donnerNomsVillageois();
		assertNotNull(noms, "La liste des noms ne doit pas être null");
		assertEquals(3, noms.length, "Il doit y avoir 3 villageois (le chef, le druide et le gaulois");
	}

	@Test
	void testDonnerNomVillage() {
		assertEquals("le village", controlAfficherVillage.donnerNomVillage());
	}

	@Test
	void testDonnerNbEtals() {
		assertEquals(2, controlAfficherVillage.donnerNbEtals());
	}

}
