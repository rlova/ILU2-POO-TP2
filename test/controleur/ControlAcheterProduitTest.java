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
		assertFalse(controlAcheterProduit.verifierIdentite("BONEMINE"));
		assertFalse(controlAcheterProduit.verifierIdentite(""));
	}

	@Test
	void testTrouverEtalVendeur() {
		Etal etal = controlAcheterProduit.trouverEtalVendeur("bonemine");
		assertNotNull(etal,"L'étal de Bonemine existe");
		assertEquals("bonemine", etal.getVendeur().getNom());
		// étal non occupé
		assertNull(controlAcheterProduit.trouverEtalVendeur("miss"));
		assertNull(controlAcheterProduit.trouverEtalVendeur(""));
	}

	@Test
	void testTrouverVendeursProduit() {
		assertNull(controlAcheterProduit.trouverVendeursProduit(""));
		
		// test sur des produits où il y a des vendeurs
		Gaulois[] vendeursFleurs = controlAcheterProduit.trouverVendeursProduit("fleurs");
		assertEquals(1,vendeursFleurs.length);
		assertEquals("bonemine",vendeursFleurs[0].getNom());
		
		// test sur des produits où il n'y a pas de vendeurs
		Gaulois[] vendeursPommes = controlAcheterProduit.trouverVendeursProduit("pommes");
		assertNull(vendeursPommes);
		
		// test sur des produits avec plusiseurs vendeurs
		Gaulois crabe1 = new Gaulois("vendeuse",3);
		Gaulois crabe2 = new Gaulois("vendeur",5);
		village.installerVendeur(crabe1, "crabes", 8);
		village.installerVendeur(crabe2, "crabes", 4);
		Gaulois[] vendeursCrabes = controlAcheterProduit.trouverVendeursProduit("crabes");
		assertEquals(2,vendeursCrabes.length);
		assertTrue(vendeursCrabes[0].getNom().equals("vendeuse"));
		assertTrue(vendeursCrabes[1].getNom().equals("vendeur"));
	}

	@Test
	void testAcheterProduit() {
		// test acheter un produit qui existe
		// cas de stock normal
		int quantiteAchetee = controlAcheterProduit.acheterProduit("bonemine", 2);
		assertEquals(2,quantiteAchetee);
		// cas de stock insuffisant
		int quantiteInsuffisant = controlAcheterProduit.acheterProduit("bonemine", 20);
		assertEquals(13,quantiteInsuffisant); // quantiteInsuffisant>quantiteDispo => quantiteInsuffisant=quantiteDispo
		// cas où la quantité est négative
		int quantiteNegative = controlAcheterProduit.acheterProduit("bonemine", -1);
		assertEquals(0, quantiteNegative);
		// cas où on la quantité est nulle
		int quantiteNulle = controlAcheterProduit.acheterProduit("bonemine", 0);
		assertEquals(0,quantiteNulle);
		
		// test acheter un produit d'un étal devenu vide 
		controlAcheterProduit.acheterProduit("obelix", 10);
		int quantiteAcheteeEtalVide = controlAcheterProduit.acheterProduit("obelix", 5);
		assertEquals(0,quantiteAcheteeEtalVide);
		
		// test acheter un produit d'un étal inexistant
		int quantiteEtalInexistant = controlAcheterProduit.acheterProduit("panoramix", 3);
		assertEquals(0,quantiteEtalInexistant);
		
		// test acheter un produit d'un vendeur sans étal
		Gaulois vendeur = new Gaulois("vendeur", 4);
		village.ajouterHabitant(vendeur);
		int quantiteSansEtal = controlAcheterProduit.acheterProduit("vendeur", 7);
		assertEquals(0, quantiteSansEtal);
	}

}
