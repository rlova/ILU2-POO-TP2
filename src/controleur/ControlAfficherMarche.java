package controleur;

import villagegaulois.Village;

public class ControlAfficherMarche {
	private Village village;

	public ControlAfficherMarche(Village village) {
		this.village = village;
	}

	public String[] donnerInfosMarche() {
		String[] infosMarche = village.donnerEtatMarche();
		for (int i=0; i<infosMarche.length; i+=3) {
			String vendeur = infosMarche[i];
			String quantite = infosMarche[i+1];
			String produit = infosMarche[i+2];
			System.out.println("- "+vendeur+" qui vend "+quantite+" "+produit);
		}
		return infosMarche;
	}
}
