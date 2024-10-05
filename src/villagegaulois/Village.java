package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
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
					+ " vivent les l�gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		private Etal[] etals;

		private Marche(int nbEtals) {
			this.etals=new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i]= new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
			
		private int trouverEtalLibre() {
			for(int indiceEtal=0;indiceEtal<etals.length;indiceEtal++) {
				if(!etals[indiceEtal].isEtalOccupe()) {
					return indiceEtal;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
		    int count = 0;
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].contientProduit(produit)) {
		            count++;
		        }
		    }

		    Etal[] etalsProduit = new Etal[count];
		    int index = 0;
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].contientProduit(produit)) {
		            etalsProduit[index++] = etals[i];
		        }
		    }
		    return etalsProduit;
		}

		
		private Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etals.length;i++) {
				if(etals[i].getVendeur()==gaulois) return etals[i];
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nonOccupe=0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}else {
					nonOccupe++;
				}
			}
			if(nonOccupe!=0) {
				chaine.append("Il reste " + nonOccupe + " étals non utilisés dans le marché.\n");
			}
			return chaine.toString();
		}
		
		
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
			StringBuilder chaine = new StringBuilder();
			chaine.append(vendeur.getNom()+ " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
			int etal = marche.trouverEtalLibre();
			if (etal == -1) {
			    return "Il n'y a plus d'étal disponible pour le vendeur " + vendeur.getNom() + ".";
			}

			marche.utiliserEtal(etal, vendeur, produit, nbProduit);
			etal++;
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + etal + ".\n");
			return chaine.toString();
		}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		switch(etalsProduit.length) {
		case (0):
			chaine.append("Il n'y a pas de vendeur qui propose " + produit + " au marché.\n");
			break;
		case (1):
			chaine.append("Seul le vendeur " + etalsProduit[0].getVendeur().getNom() + " propose " + produit + " au marché.\n");
			break;
		default:
			chaine.append("Les vendeurs qui proposent " + produit + " sont :\n");
			for (int i = 0; i < etalsProduit.length; i++) {
				if (etalsProduit[i] != null) {
					chaine.append("- " + etalsProduit[i].getVendeur().getNom() + "\n");
				}
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		 return rechercherEtal(vendeur).libererEtal();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \"" + getNom() + "\" possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
}