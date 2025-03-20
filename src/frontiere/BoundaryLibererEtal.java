package frontiere;

import controleur.ControlLibererEtal;
import controleur.ControlTrouverEtalVendeur;
import villagegaulois.Etal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'etes pas inscrit sur notre marche aujourd'hui !");
		} else {
			donneesEtal = etal.etatEtal();
			String etalOccupe = donneesEtal[0];
			String produit = donneesEtal[1];
			String quantiteInitial = donneesEtal[2];
			String quantiteVendu = donneesEtal[3];
			if (etalOccupe!="true") {
				System.out.println("Vous avez vendu "+ quantiteVendu + " sur "+quantiteInitial+" "+produit+".");
				System.out.println("Au revoir "+nomVendeur+", passez une bonne journee");
			}
		}
	}

}
