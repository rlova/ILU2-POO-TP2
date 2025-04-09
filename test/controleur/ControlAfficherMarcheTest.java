package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private ControlAfficherMarche controlAfficheMarche;
	private Village village;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village", 5, 2);
		controlAfficheMarche = new ControlAfficherMarche(village);
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(controlAfficheMarche,"le constructeur ne doit pas être null");
	}

	@Test
	void testDonnerInfosMarche() {
		fail("Not yet implemented");
	}

}
