package test.java.br.com.arangel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({VendaDaoTest.class, ProdutoDaoTest.class,
        ProdutoServiceTest.class, ClienteDaoTest.class, ClienteServiceTest.class})
public class AllTests {
}
