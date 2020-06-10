package cz.interview.task.domains;

import cz.interview.task.domains.enums.CurrencyCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="CURRENCY")
public class Currency {
    @Id
    @GeneratedValue
    private long id;
    @Column(name="currency_code")
    private CurrencyCode currencyCode;

    private String description;

    private String country;
}
