package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.entity.Account;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SoapClient {

    public static void main(String[] args) throws MalformedURLException {
        Bank bank = getSoapProxy();

        Account account = bank.createAccount();
        bank.deposit(1000, account.getNumber());
        System.out.printf("Account: %d\n", bank.getBalance(account.getNumber()));
    }

    private static Bank getSoapProxy() throws MalformedURLException {
        URL wsdl = new URL("http://localhost:8080/bank/Bank/BankService?wsdl");
        QName serviceName = new QName("http://service.bank.training.pl/", "Bank");
        QName portName = new QName("http://service.bank.training.pl/", "Bank");
        Service service = Service.create(wsdl, serviceName);
        return service.getPort(portName, Bank.class);
    }

}
