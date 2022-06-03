package ru.alfabank.currency.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Currency {

    String disclaimer;
    String license;
    Long timestamp;
    String base;
    Map<String, Double> rates;

}
