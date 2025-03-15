package frontiere;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		if (!nomVendeurConnu) {
			System.out.println("Je suis désolé "+nomVendeur+" mais il faut etre un habitant de notre village pour commencer ici.\n");
		} else {
			System.out.println("Bonjour "+nomVendeur+" , je vaus regarder su je peux vous trouver un étal.\n");
			boolean etalDisponible = controlPrendreEtal.resteEtals();
			if (!etalDisponible) {
				System.out.println("Désolé "+nomVendeur+" je n'ai plus d'étal qui ne soit pas déjà occupé.\n");
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		StringBuilder question = new StringBuilder();
		System.out.println("C'est parfait, il me reste un étal pour vous !\n");
		System.out.println("Il me faudrait quelques renseignements :\n");
		question.append("Quel produit souhiatez-vous vendre ?\n");
		String produit = Clavier.entrerChaine(question.toString());
		question = new StringBuilder();
		int nbProduit = Clavier.entrerEntier(question.toString());
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		if (numeroEtal!=-1) {
			System.out.println("Le vendeur "+nomVendeur+" s'est installé à l'étal n°"+numeroEtal);
		}
	}
}
