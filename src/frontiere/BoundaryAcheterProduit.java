package frontiere;

import controleur.ControlAcheterProduit;
import villagegaulois.Etal;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (controlAcheterProduit.verifierIdentite(nomAcheteur)==false) {
			System.out.println("Je suis désolée "+nomAcheteur+" mais il faut être un habitant de notre village pour commercier ici.");
		} else { 		
			StringBuilder question = new StringBuilder();
			question.append("Quel produit voulez-vous acheter ?");
			String produit = Clavier.entrerChaine(question.toString());
			question = new StringBuilder();
			question.append("Chez quel commerçant voulez-vous acheter des "+produit+" ?");
			String nomVendeur = Clavier.entrerChaine(question.toString());
			System.out.println(nomAcheteur+" se déplace jusqu'à l'étal du vendeur "+nomVendeur);
			trouverEtalVendeur(nomAcheteur, nomVendeur, produit);
		}
	}
	
	public void trouverEtalVendeur(String nomAcheteur, String nomVendeur, String produit) {
		Etal etal = controlAcheterProduit.trouverEtalVendeur(nomVendeur);
		if (etal==null) {
			System.out.println("Je suis désolé Bonemine mais il faut être un habitant de notre village pour commercer ici.");
		} else {
			System.out.println("Bonjour "+nomAcheteur);
			StringBuilder question = new StringBuilder();
			question.append("Combien de fleurs voulez-vous acheter ?");
			int nbProduit = Clavier.entrerEntier(question.toString());
			//if (nbProduit)
			System.out.println(nomAcheteur+" achète "+nbProduit+" "+produit+" à "+nomVendeur);
		}
	}
}
