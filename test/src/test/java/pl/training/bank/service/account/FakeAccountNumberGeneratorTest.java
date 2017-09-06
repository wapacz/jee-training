package pl.training.bank.service.account;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class FakeAccountNumberGeneratorTest {

    private static final String ACCOUNT_NUMBER_REGEX = "\\d{26}";

    @EJB
    private AccountNumberGenerator accountNumberGenerator;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(AccountNumberGenerator.class)
                .addClass(FakeAccountNumberGenerator.class);
    }

    @Test
    public void shouldGenerateValidAccountNumber() {
        String accountNumber = accountNumberGenerator.getNext();
        assertTrue(accountNumber.matches(ACCOUNT_NUMBER_REGEX));
    }

}
