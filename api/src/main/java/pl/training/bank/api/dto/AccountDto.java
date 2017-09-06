package pl.training.bank.api.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Data
public class AccountDto implements Serializable {

    @Pattern(regexp = "\\d{26}")
    private String number;
    private long balance;

}
