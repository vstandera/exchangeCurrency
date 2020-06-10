package cz.interview.task.boot;

import cz.interview.task.domains.Currency;
import cz.interview.task.domains.CurrencyExchange;
import cz.interview.task.domains.enums.CurrencyCode;
import cz.interview.task.repositories.AmountExchangeRepository;
import cz.interview.task.repositories.CurrencyExchangeRepository;
import cz.interview.task.repositories.CurrencyRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@Component
public class AfterBoot {

    CurrencyRepository currencyRepository;
    AmountExchangeRepository amountExchangeRepository;
    CurrencyExchangeRepository currencyExchangeRepository;

    public AfterBoot(CurrencyRepository currencyRepository, AmountExchangeRepository amountExchangeRepository, CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyRepository = currencyRepository;
        this.amountExchangeRepository = amountExchangeRepository;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    private static String EXCHANGE= "/exchange";



    @EventListener(ApplicationReadyEvent.class)
    public void initAfterStart(){
        currencyRepository.save(Currency.builder().country("Czech").currencyCode(CurrencyCode.CZK).description("Currency for Czech.").build());
        currencyRepository.save(Currency.builder().country("USA").currencyCode(CurrencyCode.USD).description("Currency for USA.").build());
        currencyRepository.save(Currency.builder().country("EU").currencyCode(CurrencyCode.EUR).description("Currency for EU.").build());
        currencyRepository.save(Currency.builder().country("Japan").currencyCode(CurrencyCode.JPY).description("Currency for Japan.").build());
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.CZK),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.EUR));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.EUR),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.CZK));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.CZK));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.CZK),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.CZK));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.CZK),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.EUR),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.EUR));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.EUR));
        saveCurrencyExchange(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.EUR),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD));
//        saveCurrencyExchangeDifferentCurrency(currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.JPY),currencyRepository.findCurrencyByCurrencyCode(CurrencyCode.USD));
    }

    private void saveCurrencyExchange(Currency currencyFrom, Currency currencyTo){
        try {

            File f = new File(getFilePath()+"/"+currencyFrom.getCurrencyCode().name() + "_TO_" + currencyTo.getCurrencyCode().name() + ".txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            MathContext mc = new MathContext(12, RoundingMode.HALF_UP);
            System.out.println("Reading file using Buffered Reader");

            while ((readLine = b.readLine()) != null) {
                String[] split = readLine.split("\\|");
                BigDecimal exchangeRate = new BigDecimal(split[1].replaceAll(",", "."));
                BigDecimal bigDecimal = new BigDecimal("1");
                Date exchangeDate = new SimpleDateFormat("dd.MM.yyyy").parse(split[0]);
                BigDecimal bigDecimal2 = bigDecimal.divide(exchangeRate, mc);
                System.out.println(split[0]+"|"+bigDecimal2.toString());
                currencyExchangeRepository.save(CurrencyExchange.builder().exchangeDate(exchangeDate).currencyTo(currencyTo)
                        .currencyFrom(currencyFrom).exchangeRate(exchangeRate).build());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void saveCurrencyExchangeDifferentCurrency(Currency currencyFrom, Currency currencyTo){
        try {

            File f = new File(getFilePath()+"/"+currencyFrom.getCurrencyCode().name() + "_TO_" + CurrencyCode.CZK + ".txt");
            File f2 = new File(getFilePath()+"/"+currencyTo.getCurrencyCode().name() + "_TO_" + CurrencyCode.CZK + ".txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            BufferedReader b2 = new BufferedReader(new FileReader(f2));
            String readLine = "";
            MathContext mc = new MathContext(12, RoundingMode.HALF_UP);
            System.out.println("Reading file using Buffered Reader");
            String readLine2="";
            while ((readLine = b.readLine()) != null) {
                readLine2 = b2.readLine();
                String[] split = readLine.split("\\|");
                String[] split2 = readLine2.split("\\|");
                BigDecimal exchangeRate = new BigDecimal(split[1].replaceAll(",", "."));
                BigDecimal exchangeRate2 = new BigDecimal(split2[1].replaceAll(",", "."));
                Date exchangeDate = new SimpleDateFormat("dd.MM.yyyy").parse(split[0]);
                BigDecimal bigDecimal2 = exchangeRate.divide(exchangeRate2, mc);
                System.out.println(split[0]+"|"+bigDecimal2.toString());
                currencyExchangeRepository.save(CurrencyExchange.builder().exchangeDate(exchangeDate).currencyTo(currencyTo)
                        .currencyFrom(currencyFrom).exchangeRate(exchangeRate).build());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
//        CurrencyExchange.builder().currencyFrom(currencyFrom).currencyTo(currencyTo).exchangeDate()
    }

    private String getFilePath() {
        URL url = this.getClass().getResource(EXCHANGE);
        return new File(url.getFile()).getAbsolutePath();
    }


}
