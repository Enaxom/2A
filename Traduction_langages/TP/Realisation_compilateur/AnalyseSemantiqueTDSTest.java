import org.junit.*;
import exception.*;

public class AnalyseSemantiqueTDSTest {

	@Before
	public void setUp() {
	}
	
	@Test
	public void testerGlobal() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/test.rat");
	}
	
	@Test
	public void testerGlobal2() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/test2.rat");
	}
	
	@Test
	public void testerDeclarationFonction() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDeclarationFonction.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationFonction() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationFonction.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationParemetre1() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationParametre1.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationParemetre2() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationParametre2.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationVariable1() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable1.rat");
	}
	
	@Test
	public void testerDoubleDeclarationVariable2() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable2.rat");
	}
	
	@Test
	public void testerDoubleDeclarationVariable3() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable3.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationVariable4() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable4.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationVariable5() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable5.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationVariable6() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable6.rat");
	}
	
	@Test(expected=DoubleDeclarationException.class)
	public void testerDoubleDeclarationVariable7() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testDoubleDeclarationVariable7.rat");
	}
	
	@Test
	public void testerAffectation1() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testAffectation1.rat");
	}
	
	@Test(expected=NoDeclarationException.class)
	public void testerAffectation2() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testAffectation2.rat");
	}
	
	@Test
	public void testerAffectation3() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testAffectation3.rat");
	}
	
	@Test(expected=OperationInterditeException.class)
	public void testerAffectation4() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testAffectation4.rat");
	}
	
	@Test(expected=OperationInterditeException.class)
	public void testerAffectation5() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testAffectation5.rat");
	}
	
	@Test
	public void testerUtilisation1() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation1.rat");
	}
	
	@Test
	public void testerUtilisation2() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation2.rat");
	}
	
	@Test(expected=NoDeclarationException.class)
	public void testerUtilisation3() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation3.rat");
	}
	
	@Test(expected=OperationInterditeException.class)
	public void testerUtilisation4() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation4.rat");
	}
		
	@Test(expected=OperationInterditeException.class)
	public void testerUtilisation5() {
		AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation5.rat");
	}
	
    @Test
    public void testerUtilisation6() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation6.rat");
    }
	
    @Test
    public void testerUtilisation7() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation7.rat");
    }
	
    @Test(expected=OperationInterditeException.class)
    public void testerUtilisation8() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation8.rat");
    }
	
    @Test(expected=OperationInterditeException.class)
    public void testerUtilisation9() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation9.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation10() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation10.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation11() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation11.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation12() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation12.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation13() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation13.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation14() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation14.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation15() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation15.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation16() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation16.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation17() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation17.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation18() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation18.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation19() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation19.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation20() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation20.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation21() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation21.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation22() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation22.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation23() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation23.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation24() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation24.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation25() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation25.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerUtilisation26() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testUtilisation26.rat");
    }
	
    @Test(expected=NoDeclarationException.class)
    public void testerRecurviviteVariable() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testRecursiviteVariable.rat");
    }
	
    @Test
    public void testerRecurviviteFonction() {
	    AnalyseSemantique.analyseFichier("./src-rat-tds-test/testRecursiviteFonction.rat");
    }
	
	
	
}
