package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
		};
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			
		}
		
		private int trouverEtalLibre() {
		
			for (int i = 0; i < etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			Etal[] etalsProduit;
			int nbEtalsProduit = 0;
			
			for (Etal etal : etals) {
				if (etal.contientProduit(produit)) {
					nbEtalsProduit++;
				}
			}
			
			etalsProduit = new Etal[nbEtalsProduit];
			
			int indice = 0;
			for (Etal etal : etalsProduit) {
				if (etal.contientProduit(produit)) {
					etalsProduit[indice] = etal;
					indice++;
				}
			}
			
			return etalsProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			
			for (Etal etal : etals) {
				if (etal.getVendeur() == gaulois) {
					return etal;
				}
			}
			return null;
		}
		
		private void afficherMarche() {
			int nbEtalsVide = 0;
			
			for (Etal etal : etals) {
				if (!etal.isEtalOccupe()) {
					etal.afficherEtal();
				} else {
					nbEtalsVide++;
				}
			}
			if (nbEtalsVide != 0) {
				System.out.println("Il reste " + nbEtalsVide + " étals non utilisés dans le marché. \n");
			}
		}
	}

}