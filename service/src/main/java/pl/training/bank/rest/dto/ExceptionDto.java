package pl.training.bank.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDto {

    private String description;

}
