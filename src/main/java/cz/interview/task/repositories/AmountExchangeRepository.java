package cz.interview.task.repositories;

import cz.interview.task.domains.AmountExchange;
import org.springframework.data.repository.CrudRepository;

public interface AmountExchangeRepository extends CrudRepository<AmountExchange, Long> {
}
