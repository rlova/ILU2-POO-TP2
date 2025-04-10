package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
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
		gaulois = new Gaulois("gaulois", 7);
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
		assertFalse(controlLibererEtal.isVendeur("miss"));
		assertFalse(controlLibererEtal.isVendeur(""));
		assertFalse(controlLibererEtal.isVendeur("  "));
	}

	@Test
	void testLibererEtal() {
		// test sur un étal qui existe
		String[] donnees = controlLibererEtal.libererEtal(gaulois.getNom());
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(gaulois.getNom());
		assertNotNull(etal);
		assertFalse(etal.isEtalOccupe());
		assertNotNull(donnees, "Les données de l'étal ne sont pas nuls");
		assertEquals(5,donnees.length,"Le tableau des données doit avoir 5 élements");
		assertEquals("true",donnees[0]);
		assertEquals(gaulois.getNom(),donnees[1]);
		assertEquals("produit",donnees[2]);
		assertEquals("3",donnees[3]);
		assertFalse(controlLibererEtal.isVendeur(gaulois.getNom()));
		
		// test sur un vendeur qui n'existe pas
		String[] donneesInexistants = controlLibererEtal.libererEtal("miss");
		assertNull(donneesInexistants, "le vendeur n'existe pas donc les donnees sont nuls");
		
		// test sur un personnage sans étal
		String[] donneesSansEtal = controlLibererEtal.libererEtal(druide.getNom());
		assertNull(donneesSansEtal,"Le druide n'a pas d'étal donc les données sont nuls");
		
		// test sur données incorrects
		String[] donneesVide = controlLibererEtal.libererEtal("");
		assertNull(donneesVide);
		String[] donneesAvecEsp = controlLibererEtal.libererEtal("  ");
		assertNull(donneesAvecEsp);
		
		// test faire partir un vendeur
		Gaulois asterix = new Gaulois("asterix", 6);
		village.ajouterHabitant(asterix);
		village.installerVendeur(asterix, "fruits", 3);
		assertTrue(controlLibererEtal.isVendeur(asterix.getNom()));
		village.partirVendeur(asterix);
		assertFalse(controlLibererEtal.isVendeur(asterix.getNom()), "le vendeur est bien parti");
		
		// libérer un étal déjà libéré
		String[] etalDejaLibere = controlLibererEtal.libererEtal(gaulois.getNom());
		assertNull(etalDejaLibere);
	}
}
