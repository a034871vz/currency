package ru.alfabank.currency.service;

import ru.alfabank.currency.domain.Gif;

public interface GifService {
    Gif getRandomGif(String tag);
}
