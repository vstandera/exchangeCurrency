package cz.interview.task.services;

import cz.interview.task.domains.AmountExchange;
import cz.interview.task.dto.AmountExchangeDto;
import cz.interview.task.dto.CalculateAmountDto;
import cz.interview.task.dto.CurrencyExchangeDto;

import java.util.Date;
import java.util.List;


public interface ExchangeService {

    /**
     * Calculate amount to the different currency
     * @param calculateAmountDto The dto from fe with param to calculate
     * @return Dto with calculation information
     */
    AmountExchangeDto calculateAmountCurrency(CalculateAmountDto calculateAmountDto);

    /**
     * Return all amount exchanges after start application
     * @return Dto with calculation information
     */
    List<AmountExchangeDto> getAllAmountExchange();

    /**
     * Return all possible exchange currency to date
     * @param exchangeDate The date
     * @return Dto with currency exchange information
     */
    List<CurrencyExchangeDto> getAllCurrencyExchangeByParams(Date exchangeDate);

}
