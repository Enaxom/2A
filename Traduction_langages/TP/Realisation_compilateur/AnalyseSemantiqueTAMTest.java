import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.*;

import analyseurs.VisiteurSemantique;
import attributs.AttributSynthetise;
import attributs.TDS;
import exception.*;
import rat.RatStandaloneSetup;
import rat.rat.Prog;


public class AnalyseSemantiqueTAMTest {

	// Fonction qui prend en entrée en fichier Rat 
	// renvoie la châine de caractères contenant le code TAM généré
	private String compiler(String fichierrat) {
		
		RatStandaloneSetup.doSetup();
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.getResource(URI.createURI(fichierrat), true);

		String code = "";
		if(!resource.getErrors().isEmpty()){
			//Analyse syntaxique
			for(Diagnostic d : resource.getErrors()){
				System.out.println(d.toString());
			}
		}
		else{
			// Analyse sémantique
			Prog prog = (Prog) resource.getContents().get(0);
			// TO DO à modifier au besoin
			code = (new VisiteurSemantique(new TDS(), 0, "SB").doSwitch(prog)).getCode();
			//System.out.println(code);
		}
		return code;
	}
	
	// Exécute le code TAM passé en paramètre et renvoie la sortie de la machine ITam
	private String executerTAM(String codeTam)
	{
		String line = "";
		try {
			// Ecriture du fichier
			File f = new File("testAuto.tam");
			FileWriter fw = new FileWriter (f);
			fw.write(codeTam);
			fw.flush();
			fw.close();
		
			// Exécution du code TAM
			Runtime runtime = Runtime.getRuntime();
			// TO DO : modifier le chemin d'accès au jar au besoin
			Process process = runtime.exec("java -jar runtam.jar testAuto.tam");
		
			// Récupération de la sortie
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			line =reader.readLine();
			reader.close();
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}
		return line;
	}
	
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testerprintint() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testprintint.rat"));
	 Assert.assertTrue (exit.equals("42"));
	}
	
	@Test
	public void testerprintbool() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testprintbool.rat"));
	 Assert.assertTrue (exit.equals("true"));
	}
	
	@Test
	public void testerprintrat() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testprintrat.rat"));
	 Assert.assertTrue (exit.equals("[4|5]"));
	}
	
	@Test
	public void testeraddint() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testaddint.rat"));
	 Assert.assertTrue (exit.equals("42"));
	}
	
	@Test
	public void testeraddrat() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testaddrat.rat"));
	 Assert.assertTrue (exit.equals("[7|6]"));
	}
	
	@Test
	public void testermultint() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testmultint.rat"));
	 Assert.assertTrue (exit.equals("440"));
	}
	
	@Test
	public void testermultrat() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testmultrat.rat"));
	 Assert.assertTrue (exit.equals("[14|3]"));
	}
	
	@Test
	public void testernum() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testnum.rat"));
	 Assert.assertTrue (exit.equals("4"));
	}
	
	@Test
	public void testerdenom() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testdenom.rat"));
	 Assert.assertTrue (exit.equals("7"));
	}
	
	@Test
	public void testerwhile1() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testwhile1.rat"));
	 Assert.assertTrue (exit.equals("19"));
	}
	
	@Test
	public void testerif1() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testif1.rat"));
	 Assert.assertTrue (exit.equals("18"));
	}
	
	@Test
	public void testerif2() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testif2.rat"));
	 Assert.assertTrue (exit.equals("21"));
	}
	
	@Test
	public void testerfactiter() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/factiter.rat"));
	 Assert.assertTrue (exit.equals("120"));
	}
	
	@Test
	public void testerfactrec() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/factrec.rat"));
	 Assert.assertTrue (exit.equals("120"));
	}
	
	@Test
	public void testerfuns() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/testfuns.rat"));
	 Assert.assertTrue (exit.equals("28"));
	}
	
	@Test
	public void testercomplique() {
	 String exit = executerTAM ( compiler ("./src-rat-tam-test/complique.rat"));
	 Assert.assertTrue (exit.equals("[9|4][27|14][27|16][3|2]"));
	}
	
}

