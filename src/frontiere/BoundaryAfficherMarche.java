package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
		if (infosMarche.length == 0) {
			System.out.println("Le marche est vide, revenez plus tard");
		} else {
			System.out.println(nomAcheteur+", vous trouverez au marche.");
			for (int i=0; i<infosMarche.length; i+=3) {
				String vendeur = infosMarche[i];
				String quantite = infosMarche[i+1];
				String produit = infosMarche[i+2];
				System.out.println("- "+vendeur+" qui vend "+quantite+" "+produit);
			}
		}
	}
}
