package ru.alfabank.currency.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.currency.domain.Currency;
import ru.alfabank.currency.feign.CurrencyFeignClient;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{

    @Value("${currency.appId}")
    String app_id;
    @Value("${currency.server}")
    String server;
    @Value("${currency.base}")
    String base;
    @Value("${gif.rich}")
    String rich;
    @Value("${gif.broken}")
    String broken;

    private final CurrencyFeignClient currencyFeignClient;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    @Override
    public String getCurrencyChanges(String currency) {
        log.debug("getCurrencyChanges - start: currency={}", currency);
        LocalDate today = LocalDate.now();
        Currency yesterdayCurrency = currencyFeignClient.getCurrency(createURI(currency, today.minusDays(1)));
        Currency todayCurrency = currencyFeignClient.getCurrency(createURI(currency, today));
        String result = getResult(yesterdayCurrency, todayCurrency, currency);
        log.debug("getCurrencyChanges - end: currency={}", currency);
        return result;
    }

    private URI createURI(String currency,  LocalDate day) {
        return URI.create(new StringBuilder()
                .append(server)
                .append("historical/")
                .append(formatter.format(day)).append(".json?app_id=")
                .append(app_id).append("&base=")
                .append(base).append("&symbols=").append(currency).toString());
    }

    private String getResult(Currency yesterdayCurrency, Currency todayCurrency, String currency) {
        return todayCurrency.getRates().get(currency) > yesterdayCurrency.getRates().get(currency) ?
                rich : broken;
    }
}
