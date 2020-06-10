package cz.interview.task.dto;

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
public class AmountExchangeDto {

    private CurrencyExchangeDto currencyExchange;

    private BigDecimal fromAmount;

    private BigDecimal toAmount;

    private Date createDate;
}
