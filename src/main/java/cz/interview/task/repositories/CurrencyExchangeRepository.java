package cz.interview.task.repositories;

import cz.interview.task.domains.Currency;
import cz.interview.task.domains.CurrencyExchange;
import cz.interview.task.domains.enums.CurrencyCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {

    CurrencyExchange findCurrencyExchangeByCurrencyFromAndCurrencyToAndExchangeDate(Currency currencyFrom, Currency currencyTo, Date exchangeDate);

    @Query(value="SELECT e.* FROM CURRENCY_EXCHANGE e JOIN CURRENCY cf ON e.currency_from=cf.id JOIN CURRENCY ct ON e.currency_to=ct.id  WHERE cf.currency_code=:currCodeFrom AND ct.currency_code=:CurrencyCodeTo AND e.exchange_date=:exchangeDate", nativeQuery=true)
    CurrencyExchange getCurrencyExchangeByParam(@Param("exchangeDate") Date exchangeDate, @Param("currCodeFrom") CurrencyCode currCodeFrom, @Param("CurrencyCodeTo") CurrencyCode CurrencyCodeTo);

    List<CurrencyExchange> findAllByExchangeDate(Date exchangeDate);

}
