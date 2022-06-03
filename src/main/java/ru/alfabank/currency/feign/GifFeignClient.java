package ru.alfabank.currency.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.alfabank.currency.domain.Gif;

import java.net.URI;


@FeignClient(name = "Gif", url = "${gif.server}")
public interface GifFeignClient {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Gif getRandomGif(URI path);
}