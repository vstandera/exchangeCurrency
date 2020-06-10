package cz.interview.task.dto;

import cz.interview.task.domains.Currency;
import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CurrencyExchangeDto {

    private Date exchangeDate;

    private CurrencyDto currencyFrom;

    private CurrencyDto getCurrencyTo;

    private BigDecimal exchangeRate;
}
