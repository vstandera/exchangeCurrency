package cz.interview.task.dto;

import cz.interview.task.domains.enums.CurrencyCode;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CurrencyDto {

    private CurrencyCode currencyCode;

    private String description;

    private String country;
}
