package controleur;

import villagegaulois.Etal;

public class ControlLibererEtal {
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;

	public ControlLibererEtal(
			ControlTrouverEtalVendeur controlTrouverEtalVendeur) {
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean isVendeur(String nomVendeur) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		return etal != null;
	}

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
		String[] donneesEtal = null;
		if (!isVendeur(nomVendeur)) {
			return donneesEtal;
		} else {
			Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
			donneesEtal = etal.etatEtal();
//			donneesEtal[0] = etal.isEtalOccupe();
//			donneesEtal[1] = nomVendeur;
//			donneesEtal[2] = etal.getProduit();
//			donneesEtal[3] = etal.getQuantite();
//			donneesEtal[4] = etal.get;
			if (donneesEtal[0]!="true") {
				System.out.println("Vous avez vendu "+ donneesEtal[4] + " sur "+donneesEtal[3]+" "+donneesEtal[2]+".");
				System.out.println("Au revoir "+nomVendeur+", passez une bonne journee");
			}
			etal.libererEtal();
		}
		return donneesEtal;
	}

}
