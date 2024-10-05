package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		Gaulois gaulois = new Gaulois("gaulois", 12);
        etal.acheterProduit(-1, gaulois);
		System.out.println("Fin du test");
		}

}
