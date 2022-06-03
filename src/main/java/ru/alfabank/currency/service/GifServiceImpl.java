package ru.alfabank.currency.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.currency.domain.Gif;
import ru.alfabank.currency.feign.GifFeignClient;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {

    @Value("${gif.appId}")
    String app_id;
    @Value("${gif.server}")
    String server;

    private final GifFeignClient gifFeignClient;

    @Override
    public Gif getRandomGif(String tag) {
        log.debug("getRandomGif - start: tag={}", tag);
        Gif gif = gifFeignClient.getRandomGif(createURI(tag));
        log.debug("getRandomGif - end: tag={}", tag);
        return gif;
    }

    private URI createURI(String tag) {
        return URI.create(new StringBuilder()
                .append(server)
                .append("gifs/random?api_key=")
                .append(app_id).append("&tag=")
                .append(tag).toString());
    }
}
