package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;
import villagegaulois.Etal;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)==false) {
			System.out.println("Je suis d�sol�e "+nomAcheteur+" mais il faut �tre un habitant de notre village pour commercier ici.");
			return;
		} else { 		
			StringBuilder question = new StringBuilder();
			question.append("Quel produit voulez-vous acheter ?");
			String produit = Clavier.entrerChaine(question.toString());
			Gaulois[] vendeurs = controlAcheterProduit.trouverVendeursProduit(produit);
			if (vendeurs==null || vendeurs.length==0) {
				System.out.println("Désomé, personne ne vend ce produit au marche.");
				return;
			}
			question = new StringBuilder();
			question.append("Chez quel commer�ant voulez-vous acheter des "+produit+" ?");
			String nomVendeur = Clavier.entrerChaine(question.toString());
			System.out.println(nomAcheteur+" se d�place jusqu'� l'�tal du vendeur "+nomVendeur);
			trouverEtalVendeur(nomAcheteur, nomVendeur, produit);
		}
	}
	
	public void trouverEtalVendeur(String nomAcheteur, String nomVendeur, String produit) {
		Etal etal = controlAcheterProduit.trouverEtalVendeur(nomVendeur);
		if (etal==null) {
			System.out.println("Je suis d�sol� Bonemine mais il faut �tre un habitant de notre village pour commercer ici.");
		} else {
			System.out.println("Bonjour "+nomAcheteur);
			StringBuilder question = new StringBuilder();
			question.append("Combien de fleurs voulez-vous acheter ?");
			int nbProduit = Clavier.entrerEntier(question.toString());
			//if (nbProduit)
			System.out.println(nomAcheteur+" ach�te "+nbProduit+" "+produit+" � "+nomVendeur);
		}
	}
}
