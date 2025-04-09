package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private ControlAcheterProduit controlAcheterProduit;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private Village village;
	private Gaulois bonemine;
	private Gaulois obelix;
	private Chef chef;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 12, 4);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		chef = new Chef("chef", 7, village);
		village.setChef(chef);
		bonemine = new Gaulois("bonemine", 4);
		obelix = new Gaulois("obelix",10);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(obelix);
		village.installerVendeur(bonemine, "fleurs", 15);
		village.installerVendeur(obelix, "chocolat",10);
	}
	
	@Test
	void testControlAcheterProduit() {
		assertNotNull(controlAcheterProduit,"le constructeur ne doit pas être null");
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlAcheterProduit.verifierIdentite(bonemine.getNom()));
		assertFalse(controlAcheterProduit.verifierIdentite("miss"));
	}

	@Test
	void testTrouverEtalVendeur() {
		Etal etal = controlAcheterProduit.trouverEtalVendeur("bonemine");
		assertNotNull(etal,"L'étal de Bonemine existe");
		assertEquals("bonemine", etal.getVendeur().getNom());
		assertNull(controlAcheterProduit.trouverEtalVendeur("miss"));
	}

	@Test
	void testTrouverVendeursProduit() {
		// test sur des produits où il y a des vendeurs
		Gaulois[] vendeursFleurs = controlAcheterProduit.trouverVendeursProduit("fleurs");
		assertEquals(1,vendeursFleurs.length);
		assertEquals("bonemine",vendeursFleurs[0].getNom());
		
		// test sur des produits où il n'y a pas de vendeurs
		Gaulois[] vendeursPommes = controlAcheterProduit.trouverVendeursProduit("pommes");
		assertNull(vendeursPommes);
	}

	@Test
	void testAcheterProduit() {
		// test acheter un produit qui existe
		// cas de stock normal
		int quantiteAchetee = controlAcheterProduit.acheterProduit("bonemine", 2);
		assertEquals(2,quantiteAchetee);
		
		// cas de stock insuffisant
		quantiteAchetee = controlAcheterProduit.acheterProduit("bonemine", 20);
		assertEquals(13,quantiteAchetee); // quantiteAchetee>quantiteDispo => quantiteAchetee=quantiteDispo
		
		// test acheter un produit d'un étal vide
		controlAcheterProduit.acheterProduit("obelix", 10);
		int quantiteAcheteeEtalVide = controlAcheterProduit.acheterProduit("obelix", 5);
		assertEquals(0,quantiteAcheteeEtalVide);
	}

}
