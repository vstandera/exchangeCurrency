package cz.interview.task.controllers;

import cz.interview.task.dto.AmountExchangeDto;
import cz.interview.task.dto.CalculateAmountDto;
import cz.interview.task.dto.CurrencyExchangeDto;
import cz.interview.task.services.ExchangeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "Exchange currency interview", tags = {
        "Exchange" })
@SwaggerDefinition(tags = { @Tag(name = "Exchange", description = "This is a controller for handle Exchange currency.") })
public class ExchangeController {

    private ExchangeServiceImpl exchangeService;

    public ExchangeController(ExchangeServiceImpl exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchangeCurrency/{exchangeDate}")
    @ApiOperation(value = "Get exchange currencies to date.")
    public ResponseEntity<List<CurrencyExchangeDto>> getCurrencyExchange(@PathVariable(name="exchangeDate") @DateTimeFormat(pattern = "dd-MM-yyyy" )Date exchangeDate){
        return ResponseEntity.ok(exchangeService.getAllCurrencyExchangeByParams(exchangeDate));
    }

    @PostMapping("/calculate")
    @ApiOperation(value = "Get calculate amount to different currency.")
    public ResponseEntity<AmountExchangeDto> calculateAmount(@RequestBody CalculateAmountDto calculateAmountDto){
        return ResponseEntity.ok(exchangeService.calculateAmountCurrency(calculateAmountDto));
    }
    @GetMapping("/allAmountExchange")
    @ApiOperation(value = "Get exchange currencies to date.")
    public ResponseEntity<List<AmountExchangeDto>> getCurrencyExchange(){
        return ResponseEntity.ok(exchangeService.getAllAmountExchange());
    }

}
