package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche(nbEtals);
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
	
	private static class Marche{
		Etal[] etals;

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
				if(etals[indiceEtal].isEtalOccupe()) {
					return indiceEtal;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			Etal[] etalsProduit = new Etal[etals.length];
			
			for(int indiceEtal=0;indiceEtal<etals.length;indiceEtal++) {
				if(etals[indiceEtal].contientProduit(produit)) {
					etalsProduit[indiceEtal]=etals[indiceEtal];
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
					chaine.append (etals[i].afficherEtal());
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
	
	
	
	
	
	
	
}