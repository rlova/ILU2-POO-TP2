package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private ControlAfficherVillage controlAfficherVillage, controlAfficherVillageDuChef;
	private Village village, villageDuChef;
	private Chef chef;
	private Gaulois gaulois;
	private Druide druide;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlAfficherVillage = new ControlAfficherVillage(village);
		chef = new Chef("chef", 5, village);
		village.setChef(chef);
		village.getNom();
		gaulois = new Gaulois("gaulois", 7);
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
		assertEquals("chef",noms[0]);	
		assertEquals("gaulois",noms[1]);
		assertEquals("le druide druide",noms[2]);
		
		// test sur un village sans chef
		villageDuChef = new Village("village du chef", 4, 2);
		controlAfficherVillageDuChef = new ControlAfficherVillage(villageDuChef);
		Chef ChefSeulHabitant = new Chef("solo", 4, villageDuChef);
		villageDuChef.setChef(ChefSeulHabitant);
		String[] nomDuChef = controlAfficherVillageDuChef.donnerNomsVillageois();
		assertEquals(1, nomDuChef.length,"le tableau de String doit contenir seulement le nom du chef");
		assertEquals("solo",nomDuChef[0]);
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
