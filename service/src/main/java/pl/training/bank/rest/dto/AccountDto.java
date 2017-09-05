package pl.training.bank.rest.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class AccountDto {

    private String number;
    private long balance;

}
