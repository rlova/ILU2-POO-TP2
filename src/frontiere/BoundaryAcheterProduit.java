package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;
import villagegaulois.Etal;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)==false) {
			System.out.println("Je suis desolee "+nomAcheteur+" mais il faut �tre un habitant de notre village pour commercier ici.");
		} else { 		
			StringBuilder question = new StringBuilder();
			question.append("Quel produit voulez-vous acheter ?");
			String produit = Clavier.entrerChaine(question.toString());
			Gaulois[] vendeurs = controlAcheterProduit.trouverVendeursProduit(produit);
			if (vendeurs==null || vendeurs.length==0) {
				System.out.println("Desole, personne ne vend ce produit au marche.");
			}
			question = new StringBuilder();
			question.append("Chez quel commercant voulez-vous acheter des "+produit+" ?");
			for (int i=0; i<vendeurs.length; i++) {
				System.out.println((i+1)+" - "+vendeurs[i].getNom());
			}
			String nomVendeur = Clavier.entrerChaine(question.toString());
			System.out.println(nomAcheteur+" se deplace jusqu'a l'etal du vendeur "+nomVendeur);
			trouverEtalVendeur(nomAcheteur, nomVendeur, produit);
		}
	}
	
	public void trouverEtalVendeur(String nomAcheteur, String nomVendeur, String produit) {
		Etal etal = controlAcheterProduit.trouverEtalVendeur(nomVendeur);
		if (etal==null) {
			System.out.println("Je suis desole"+ nomVendeur +"mais il faut �tre un habitant de notre village pour commercer ici.");
		} else {
			System.out.println("Bonjour "+nomAcheteur);
			StringBuilder question = new StringBuilder();
			question.append("Combien de "+ produit +" fleurs voulez-vous acheter ?");
			int quantite = Clavier.entrerEntier(question.toString());
			int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantite);
			if (quantiteAchetee==0) {
				System.out.println(nomAcheteur+" veut acheter "+quantite+" "+produit+", malheureusement il n'yen a plus !");
			} else if (quantiteAchetee<quantite) {
				System.out.println(nomAcheteur+" veut acheter "+quantite+" "+", malheureusement "+nomVendeur+" n'en a plus que "+quantiteAchetee+". "+nomAcheteur+" achete tout le stock de "+nomVendeur+".");
			} else {
				System.out.println(nomAcheteur+" achete "+quantite+" "+produit+" a "+nomVendeur);
			}
		}
	}
}
