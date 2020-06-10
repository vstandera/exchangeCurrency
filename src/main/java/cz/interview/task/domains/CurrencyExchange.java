package cz.interview.task.domains;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="CURRENCY_EXCHANGE")
public class CurrencyExchange {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="exchange_date")
    @Temporal(TemporalType.DATE)
    private Date exchangeDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_from")
    private Currency currencyFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_to")
    private Currency currencyTo;


    private BigDecimal exchangeRate;
}
