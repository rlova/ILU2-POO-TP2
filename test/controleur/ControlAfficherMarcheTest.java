package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private ControlAfficherMarche controlAfficheMarche, controlAfficherMarcherEtalVide;
	private Village village, villageEtalVide;
	private Gaulois bonemine;
	private Gaulois obelix;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlAfficheMarche = new ControlAfficherMarche(village);
		bonemine = new Gaulois("bonemine", 5);
		obelix = new Gaulois("obelix", 2);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(obelix);
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(controlAfficheMarche,"le constructeur ne doit pas être null");
	}

	@Test
	void testDonnerInfosMarche() {
		// test sur les infos d'un marché vide
		String[] infosVides = controlAfficheMarche.donnerInfosMarche();
		assertNotNull(infosVides,"Les infos ne sont jamais null");
		assertEquals(0,infosVides.length,"le marché retourne un tableau vide");
		
		// test sur un marché avec des vendeurs
		village.installerVendeur(bonemine, "fleurs", 3);
		village.installerVendeur(obelix, "pommes", 10);
		String[] infos = controlAfficheMarche.donnerInfosMarche();
		assertEquals(6,infos.length);
		assertEquals("bonemine",infos[0]);
		assertEquals("3",infos[1]);
		assertEquals("fleurs",infos[2]);
		assertEquals("obelix",infos[3]);
		assertEquals("10",infos[4]);
		assertEquals("pommes",infos[5]);
		
		// test sur un marché avec un étal vide
		villageEtalVide = new Village("Village avec étal vide", 4, 1);
		controlAfficherMarcherEtalVide = new ControlAfficherMarche(villageEtalVide);
		villageEtalVide.installerVendeur(bonemine, null, 0);
		String[] infosEtalVide = controlAfficherMarcherEtalVide.donnerInfosMarche();
		assertEquals(3,infosEtalVide.length);
		assertEquals("bonemine",infosEtalVide[0]);
		assertEquals("0", infosEtalVide[1]);
		assertNull(infosEtalVide[2]);
		
		// test sur plusieurs vendeurs
		Village villagevendeursMax = new Village("village max vendeurs", 400,300);
		ControlAfficherMarche controlMaxVendeurs = new ControlAfficherMarche(villagevendeursMax);
		for (int i=0; i<100; i++) {
			Gaulois v = new Gaulois("vendeur"+i,1);
			villagevendeursMax.ajouterHabitant(v);
			villagevendeursMax.installerVendeur(v, "produit"+i, i);
		}
		String[] infosPlusieursVendeurs = controlMaxVendeurs.donnerInfosMarche();
		assertEquals(300,infosPlusieursVendeurs.length);
		
		// test sur quantité négative
		Village villageQtNegative = new Village("village quantité négative", 2, 3);
		ControlAfficherMarche controlQtNegative = new ControlAfficherMarche(villageQtNegative);
		villageQtNegative.installerVendeur(bonemine, "mangues", -1);
		String[] infosQtNegative = controlQtNegative.donnerInfosMarche();
		assertEquals("-1",infosQtNegative[1]);
		
		// test sur produit avec espace
		Village villagePtEspace = new Village("village produit avec espace", 2, 3);
		ControlAfficherMarche controlPtEspace = new ControlAfficherMarche(villagePtEspace);
		villageQtNegative.installerVendeur(bonemine, "  ananas   ", 4);
		String[] infosPtEspace = controlPtEspace.donnerInfosMarche();
		assertEquals(0,infosPtEspace.length);
	}
}
