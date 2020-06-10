package cz.interview.task.converters;

import cz.interview.task.domains.CurrencyExchange;
import cz.interview.task.dto.CurrencyExchangeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeToCurrencyExchangeDto implements Converter<CurrencyExchange, CurrencyExchangeDto> {

    private CurrencyToCurrencyDto currencyToCurrencyDto;

    public CurrencyExchangeToCurrencyExchangeDto(CurrencyToCurrencyDto currencyToCurrencyDto) {
        this.currencyToCurrencyDto = currencyToCurrencyDto;
    }

    @Override
    public CurrencyExchangeDto convert(CurrencyExchange currencyExchange) {
        return CurrencyExchangeDto.builder().currencyFrom(currencyToCurrencyDto.convert(currencyExchange.getCurrencyFrom()))
                .getCurrencyTo(currencyToCurrencyDto.convert(currencyExchange.getCurrencyTo()))
                .exchangeDate(currencyExchange.getExchangeDate())
                .exchangeRate(currencyExchange.getExchangeRate()).build();
    }
}
