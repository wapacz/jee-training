package pl.training.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.text.NumberFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperationSummary implements Serializable {

    private static final int CURRENCY_UNIT = 100;
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();

    @NonNull
    private OperationType type;
    @NonNull
    private long funds;

    @Override
    public String toString() {
        return String.format("%ss: %s", type.name().toLowerCase(), formatter.format((double) funds / CURRENCY_UNIT));
    }

}
