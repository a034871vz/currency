package ru.alfabank.currency.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Gif {

    Data data;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class Data {
        String type;
        String id;
        String url;
        String title;
    }
}
