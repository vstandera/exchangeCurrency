package cz.interview.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.interview.task.domains.enums.CurrencyCode;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalculateAmountDto {

    private CurrencyCode currencyCodeFrom;

    private CurrencyCode currencyCodeTo;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date exchangeDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;
}
