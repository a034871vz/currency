package ru.alfabank.currency.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.currency.domain.Gif;
import ru.alfabank.currency.service.CurrencyService;
import ru.alfabank.currency.service.GifService;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainResource {

    private final CurrencyService currencyService;
    private final GifService gifService;

    @GetMapping("/{currency}")
    public ResponseEntity<Gif> checkCurrencyAndGetGif(@PathVariable String currency) {
        log.debug("checkCurrencyAndGetGif - start: currency={}", currency);
        String tag = currencyService.getCurrencyChanges(currency);
        Gif result = gifService.getRandomGif(tag);
        log.debug("checkCurrencyAndGetGif - end: tag={}", tag);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
