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
			System.out.println("Je suis desole "+nomVendeur+" mais il faut etre un habitant de notre village pour commencer ici.");
		} else {
			System.out.println("Bonjour "+nomVendeur+" , je vaus regarder si je peux vous trouver un etal.");
			boolean etalDisponible = controlPrendreEtal.resteEtals();
			if (!etalDisponible) {
				System.out.println("Desole "+nomVendeur+" je n'ai plus d'etal qui ne soit pas deja occupee.");
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		StringBuilder question = new StringBuilder();
		System.out.println("C'est parfait, il me reste un atal pour vous !");
		System.out.println("Il me faudrait quelques renseignements :");
		question.append("Quel produit souhaitez-vous vendre ?");
		String produit = Clavier.entrerChaine(question.toString());
		question = new StringBuilder();
		question.append("Combien souhaitez-vous en vendre ?");
		int nbProduit = Clavier.entrerEntier(question.toString());
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		if (numeroEtal!=-1) {
			System.out.println("Le vendeur "+nomVendeur+" s'est installe a l'etal n "+(numeroEtal+1));
		}
	}
}
