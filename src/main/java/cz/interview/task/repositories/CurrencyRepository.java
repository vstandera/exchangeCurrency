package cz.interview.task.repositories;

import cz.interview.task.domains.Currency;
import cz.interview.task.domains.enums.CurrencyCode;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    public Currency findCurrencyByCurrencyCode(CurrencyCode currencyCode);
}
