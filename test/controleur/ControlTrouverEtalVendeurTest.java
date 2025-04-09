package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	private ControlTrouverEtalVendeur controlTrouverVendeur;
	private Village village;
	private Gaulois gaulois;
	private Druide druide;
	private Chef chef;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlTrouverVendeur = new ControlTrouverEtalVendeur(village);
		chef = new Chef("le chef", 5, village);
		village.setChef(chef);
		gaulois = new Gaulois("Le gaulois 1", 7);
		druide = new Druide("druide", 5, 6, 12);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "produit", 3);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(controlTrouverVendeur, "Le constructeur ne doit pas être null");
	}

	@Test
	void testTrouverEtalVendeur() {
		Etal etalExistant = controlTrouverVendeur.trouverEtalVendeur(gaulois.getNom());
		assertNotNull(etalExistant, "L'etal existe bien et ne doit pas être null");
		assertEquals(gaulois,etalExistant.getVendeur(),"Le même vendeur");
		Etal etalInexistant = controlTrouverVendeur.trouverEtalVendeur("miss");
		assertNull(etalInexistant, "L'étal n'existe pas car pas de vendeur");
		Etal vendeurSansEtal = controlTrouverVendeur.trouverEtalVendeur(druide.getNom());
		assertNull(vendeurSansEtal, "L'étal n'existe pas donc il est null");
	}

}
