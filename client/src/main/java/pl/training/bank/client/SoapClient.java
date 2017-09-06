package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.account.AccountDto;
import pl.training.bank.operation.OperationDto;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static pl.training.bank.operation.OperationTypeDto.DEPOSIT;

public class SoapClient {

    public static void main(String[] args) throws MalformedURLException {
        Bank bank = getSoapProxy();
        AccountDto account = bank.createAccount();
        OperationDto operation = new OperationDto(account, DEPOSIT, 2_000L);
        bank.processOperation(operation);
    }

    private static Bank getSoapProxy() throws MalformedURLException {
        URL wsdl = new URL("http://localhost:8080/bank/Bank/BankService?wsdl");
        QName serviceName = new QName("http://service.bank.training.pl/", "Bank");
        QName portName = new QName("http://service.bank.training.pl/", "Bank");
        Service service = Service.create(wsdl, serviceName);
        return service.getPort(portName, Bank.class);
    }

}
