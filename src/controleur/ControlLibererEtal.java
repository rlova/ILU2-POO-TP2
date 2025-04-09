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
		return etal != null && etal.isEtalOccupe();
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
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		if (etal==null || !etal.isEtalOccupe()) {
			return donneesEtal;
<<<<<<< HEAD
=======
		} else {
			Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
			donneesEtal = etal.etatEtal();
			if (donneesEtal[0]!="true") {
				System.out.println("Vous avez vendu "+ donneesEtal[4] + " sur "+donneesEtal[3]+" "+donneesEtal[2]+".");
				System.out.println("Au revoir "+nomVendeur+", passez une bonne journee");
			}
			etal.libererEtal();
>>>>>>> 04d2f50407f0cab9195742425471c07c05ea36ab
		}
		donneesEtal = etal.etatEtal();
		etal.libererEtal();
		return donneesEtal;
	}

}
