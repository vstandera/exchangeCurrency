package cz.interview.task.services;

import cz.interview.task.converters.AmountExchangeToAmountExchangeDto;
import cz.interview.task.converters.CurrencyExchangeToCurrencyExchangeDto;
import cz.interview.task.domains.AmountExchange;
import cz.interview.task.domains.Currency;
import cz.interview.task.domains.CurrencyExchange;
import cz.interview.task.dto.AmountExchangeDto;
import cz.interview.task.dto.CalculateAmountDto;
import cz.interview.task.dto.CurrencyExchangeDto;
import cz.interview.task.repositories.CurrencyExchangeRepository;
import cz.interview.task.repositories.CurrencyRepository;
import cz.interview.task.repositories.AmountExchangeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

private AmountExchangeRepository amountExchangeRepository;
private CurrencyExchangeRepository currencyExchangeRepository;
private CurrencyRepository currencyRepository;
private AmountExchangeToAmountExchangeDto amountExchangeToAmountExchangeDto;
private CurrencyExchangeToCurrencyExchangeDto currencyExchangeToCurrencyExchangeDto;

    public ExchangeService(AmountExchangeRepository amountExchangeRepository, CurrencyExchangeRepository currencyExchangeRepository,
                           CurrencyRepository currencyRepository, AmountExchangeToAmountExchangeDto amountExchangeToAmountExchangeDto,
                           CurrencyExchangeToCurrencyExchangeDto currencyExchangeToCurrencyExchangeDto) {
        this.amountExchangeRepository = amountExchangeRepository;
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.currencyRepository = currencyRepository;
        this.amountExchangeToAmountExchangeDto = amountExchangeToAmountExchangeDto;
        this.currencyExchangeToCurrencyExchangeDto = currencyExchangeToCurrencyExchangeDto;
    }

    public AmountExchangeDto calculateAmountCurrency(CalculateAmountDto calculateAmountDto) {
        Currency currencyFromC = currencyRepository.findCurrencyByCurrencyCode(calculateAmountDto.getCurrencyCodeFrom());
        Currency currencyToC = currencyRepository.findCurrencyByCurrencyCode(calculateAmountDto.getCurrencyCodeTo());

        MathContext mc = new MathContext(12, RoundingMode.HALF_UP);
        try {
            CurrencyExchange currencyExchange = currencyExchangeRepository.findCurrencyExchangeByCurrencyFromAndCurrencyToAndExchangeDate(currencyFromC, currencyToC, calculateAmountDto.getExchangeDate());
//            CurrencyExchange currencyExchange = currencyExchangeRepository.getCurrencyExchangeByParam(calculateAmountDto.getExchangeDate(), calculateAmountDto.getCurrencyCodeFrom(), calculateAmountDto.getCurrencyCodeTo());
            BigDecimal toAmount = calculateAmountDto.getAmount().multiply(currencyExchange.getExchangeRate(), mc);
            AmountExchange amountExchange = amountExchangeRepository.save(AmountExchange.builder().fromAmount(calculateAmountDto.getAmount()).toAmount(toAmount)
                    .currencyExchange(currencyExchange).build());
            return amountExchangeToAmountExchangeDto.convert(amountExchange);
        } catch (EmptyResultDataAccessException emp) {
            throw new RuntimeException("The CurrencyExchange not found for:"+ calculateAmountDto);
        }
    }
    
    public List<AmountExchange> getAllAmountExchange(){
        List<AmountExchange> amExchanges = new ArrayList<>();
        amountExchangeRepository.findAll().forEach(amExchanges::add);
        return amExchanges;
    }

    public List<CurrencyExchangeDto> getAllAmountExchangeByParams(Date exchangeDate) {
        return currencyExchangeRepository.findAllByExchangeDate(exchangeDate).stream().map(currencyExchangeToCurrencyExchangeDto::convert).collect(Collectors.toList());


    }
}
