import org.junit.*;
import exception.*;

public class AnalyseSemantiqueTypeTest {

	@Before
	public void setUp() {
	}
	
	@Test
	public void testerGlobal() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/test.rat");
	}
	
	@Test
	public void testerGlobal2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/test2.rat");
	}
	
	@Test
	public void testerDeclaration1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration1.rat");
	}
	
	@Test
	public void testerDeclaration2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration2.rat");
	}
	
	@Test
	public void testerDeclaration3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDeclaration4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDeclaration5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration5.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDeclaration6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration6.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDeclaration7() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration7.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDeclaration8() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration8.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDeclaration9() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDeclaration9.rat");
	}
	
	@Test
	public void testerAffectation1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation1.rat");
	}
	
	@Test
	public void testerAffectation2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation2.rat");
	}
	
	@Test
	public void testerAffectation3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAffectation4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAffectation5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation5.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAffectation6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation6.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAffectation7() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation7.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAffectation8() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation8.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAffectation9() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAffectation9.rat");
	}
	
	@Test
	public void testerConditionnelle1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testConditionnelle1.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerConditionnelle2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testConditionnelle2.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerConditionnelle3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testConditionnelle3.rat");
	}
	
	@Test
	public void testerConditionnelle4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testConditionnelle4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerConditionnelle5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testConditionnelle5.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerConditionnelle6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testConditionnelle6.rat");
	}
	
	@Test
	public void testerRepetition1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRepetition1.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRepetition2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRepetition2.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRepetition3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRepetition3.rat");
	}
	
	@Test
	public void testerRepetition4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRepetition4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRepetition5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRepetition5.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRepetition6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRepetition6.rat");
	}
	
	@Test
	public void testerPrint1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testPrint1.rat");
	}
	
	@Test
	public void testerPrint2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testPrint2.rat");
	}
	
	@Test
	public void testerPrint3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testPrint3.rat");
	}
	
	@Test
	public void testerAppel1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel1.rat");
	}
	
	@Test
	public void testerAppel2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel2.rat");
	}
	
	@Test
	public void testerAppel3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel3.rat");
	}
	
	@Test
	public void testerAppel4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel4.rat");
	}
	
	@Test
	public void testerAppel5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel5.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel6.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel7() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel7.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel8() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel9.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel10() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel10.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel11() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel11.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel12() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel12.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerAppel13() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testAppel13.rat");
	}
	
	@Test
	public void testerRetourFonction1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRetourFonction1.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRetourFonction2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRetourFonction2.rat");
	}
	
	@Test
	public void testerRationnel1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRationnel1.rat");
	}
	
	@Test
	public void testerRationnel2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRationnel2.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRationnel3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRationnel3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRationnel4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRationnel4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerRationnel5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRationnel5.rat");
	}
	
	@Test
	public void testerNumerateur1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testNumerateur1.rat");
	}
	
	@Test
	public void testerNumerateur2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testNumerateur2.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerNumerateur3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testNumerateur3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerNumerateur4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testNumerateur4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerNumerateur5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testNumerateur5.rat");
	}
	
	@Test
	public void testerDenominateur1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDenominateur1.rat");
	}
	
	@Test
	public void testerDenominateur2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDenominateur2.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDenominateur3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDenominateur3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDenominateur4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDenominateur4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerDenominateur5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testDenominateur5.rat");
	}
	
	@Test
	public void testerIdent1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent1.rat");
	}
	
	@Test
	public void testerIdent2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent2.rat");
	}
	
	@Test
	public void testerIdent3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerIdent4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent4.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerIdent5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent5.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerIdent6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent6.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerIdent7() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent7.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerIdent8() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent8.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerIdent9() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testIdent9.rat");
	}
	
	@Test
	public void testerRecursiviteFonction() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testRecursiviteFonction.rat");
	}
	
	@Test
	public void testerOperation1() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation1.rat");
	}
	
	@Test
	public void testerOperation2() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation2.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerOperation3() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation3.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerOperation4() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation4.rat");
	}
	
	@Test
	public void testerOperation5() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation5.rat");
	}
	
	@Test
	public void testerOperation6() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation6.rat");
	}
	
	@Test
	public void testerOperation7() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation7.rat");
	}
	
	@Test
	public void testerOperation8() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation8.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerOperation9() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation9.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerOperation10() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation10.rat");
	}
	
	@Test(expected=TypeException.class)
	public void testerOperation11() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation11.rat");
	}
	
	@Test
	public void testerOperation12() {
		AnalyseSemantique.analyseFichier("./src-rat-type-test/testOperation12.rat");
	}

	
	
	
}
