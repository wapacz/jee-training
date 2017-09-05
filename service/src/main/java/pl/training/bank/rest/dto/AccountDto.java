package pl.training.bank.rest.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class AccountDto {

    @Pattern(regexp = "\\d{26}")
    private String number;
    private long balance;

}
