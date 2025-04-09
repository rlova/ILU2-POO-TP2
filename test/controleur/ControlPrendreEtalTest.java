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
		assertTrue(controlPrendreEtal.resteEtals(),"il y a toujours des étals dispo après avoir occuper une");
		etalOccupe.occuperEtal(gaulois, "bonbons", 3);
		assertTrue(controlPrendreEtal.resteEtals(),"toujours des étals dispo");
		Etal etalNonOccupe = new Etal();
		assertFalse(etalNonOccupe.isEtalOccupe());
	}

	@Test
	void testPrendreEtal() {
		int numero = controlPrendreEtal.prendreEtal(chef.getNom(), "fleurs", 3);
		assertEquals(0, numero);
		int numeroNul = controlPrendreEtal.prendreEtal("Marie", "citrons", 12);
		assertEquals(-1, numeroNul, "il faut que l'habitant soit dans le village");
		int produitNul = controlPrendreEtal.prendreEtal(gaulois.getNom(), null, 1);
		assertEquals(1,produitNul);
		
		// test avec un habitant non vendeur
		Gaulois asterix = new Gaulois("asterix", 3);
		village.ajouterHabitant(asterix);
		int numNonVendeur = controlPrendreEtal.prendreEtal(asterix.getNom(), "pommes", 3);
		assertEquals(-1,numNonVendeur);
		
		// test lorsqu'il n'y a plus d'étals dispo
		Gaulois bonemine = new Gaulois("bonemine", 5);
		int sansEtalDispo = controlPrendreEtal.prendreEtal(bonemine.getNom(), "chocolat", 10);
		assertEquals(-1,sansEtalDispo, "affiche -1 lorsqu'il n'y a plus d'étal dispo");
		
		// test sur un produit vide 
		Village villageTest = new Village("village test", 7, 4);
		ControlPrendreEtal control = new ControlPrendreEtal(controlVerifierIdentite, villageTest);
		villageTest.setChef(chef);
		int produitVide = control.prendreEtal(gaulois.getNom(), "", 7);
		assertEquals(-1,produitVide, "le produit ne doit pas être vide");
		
		// test sur une quantité nulle
		int quantiteNulle = control.prendreEtal(gaulois.getNom(), "bonbons", 0);
		assertEquals(-1,quantiteNulle, "la quantité ne doit pas être nulle");
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlPrendreEtal.verifierIdentite(chef.getNom()));
		assertFalse(controlPrendreEtal.verifierIdentite(druide.getNom()));
	}

}
