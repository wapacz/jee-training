package pl.training.bank.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDto implements Serializable {

    private String description;

}
