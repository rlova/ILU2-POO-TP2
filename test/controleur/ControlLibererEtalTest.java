package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlLibererEtalTest {
	private ControlLibererEtal controlLibererEtal;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private Village village;
	private Gaulois gaulois;
	private Druide druide;
	private Chef chef;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		chef = new Chef("chef", 3, village);
		village.setChef(chef);
		gaulois = new Gaulois("Le gaulois 1", 7);
		druide = new Druide("druide", 5, 6, 12);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "produit", 3);
	}

	@Test
	void testControlLibererEtal() {
		assertNotNull(controlLibererEtal,"Le constructeur ne doit pas être null");
	}

	@Test
	void testIsVendeur() {
		assertTrue(controlLibererEtal.isVendeur(gaulois.getNom()));
		assertFalse(controlLibererEtal.isVendeur(druide.getNom()));
	}

	@Test
	void testLibererEtal() {
		String[] donnees = controlLibererEtal.libererEtal(gaulois.getNom());
		assertNotNull(donnees, "Les données de l'étal n'est pas null");
		assertEquals(5,donnees.length,"Le tableau des données doit avoir 5 élements");
		assertEquals("true",donnees[0]);
		assertEquals(gaulois.getNom(),donnees[1]);
		assertEquals("produit",donnees[2]);
		assertEquals("3",donnees[3]);
	}
}
