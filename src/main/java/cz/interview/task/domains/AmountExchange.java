package cz.interview.task.domains;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="AMOUNT_EXCHANGE")
public class AmountExchange {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "currency_exchange_id")
    private CurrencyExchange currencyExchange;
    @Column(name="from_amount")
    private BigDecimal fromAmount;

    @Column(name="to_amount")
    private BigDecimal toAmount;

    @CreatedDate
    @Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
