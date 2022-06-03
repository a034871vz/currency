package ru.alfabank.currency.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.alfabank.currency.domain.Currency;

import java.net.URI;


@FeignClient(name = "Currency", url = "${currency.server}")
public interface CurrencyFeignClient {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Currency getCurrency(URI path);
}