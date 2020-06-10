package cz.interview.task.converters;

import cz.interview.task.domains.AmountExchange;
import cz.interview.task.dto.AmountExchangeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AmountExchangeToAmountExchangeDto implements Converter<AmountExchange, AmountExchangeDto> {


    CurrencyExchangeToCurrencyExchangeDto currencyExchangeToCurrencyExchangeDto;

    public AmountExchangeToAmountExchangeDto(CurrencyExchangeToCurrencyExchangeDto currencyExchangeToCurrencyExchangeDto) {
        this.currencyExchangeToCurrencyExchangeDto = currencyExchangeToCurrencyExchangeDto;
    }

    @Override
    public AmountExchangeDto convert(AmountExchange amountExchange) {
        return AmountExchangeDto.builder().createDate(amountExchange.getCreateDate())
                .currencyExchange(currencyExchangeToCurrencyExchangeDto.convert(amountExchange.getCurrencyExchange()))
                .fromAmount(amountExchange.getFromAmount()).toAmount(amountExchange.getToAmount()).build();
    }
}
