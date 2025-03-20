package controleur;

import villagegaulois.Etal;

public class ControlLibererEtal {
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;

	public ControlLibererEtal(
			ControlTrouverEtalVendeur controlTrouverEtalVendeur) {
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	//TODO a completer

	/**
	 * 
	 * @param nomVendeur
	 * @return donneesEtal est un tableau de chaine contenant
	 * 		[0] : un boolean indiquant si l'étal est occupé
	 * 		[1] : nom du vendeur
	 * 		[2] : produit vendu
	 * 		[3] : quantité de produit à vendre au début du marché
	 * 		[4] : quantité de produit vendu
	 */
	public String[] libererEtal(String nomVendeur) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		boolean vendeurReconnu = etal != null;
		String[] donneesEtal = null;
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
		return donneesEtal;
	}

}
