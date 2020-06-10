package cz.interview.task.converters;


import cz.interview.task.domains.Currency;
import cz.interview.task.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyToCurrencyDto implements Converter<Currency, CurrencyDto> {

    @Override
    public CurrencyDto convert(Currency currency) {
       return CurrencyDto.builder().country(currency.getCountry()).currencyCode(currency.getCurrencyCode()).description(currency.getDescription())
                .build();
    }
}
