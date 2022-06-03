package ru.alfabank.currency.resource;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.alfabank.currency.domain.Gif;
import ru.alfabank.currency.service.CurrencyService;
import ru.alfabank.currency.service.GifService;

@ContextConfiguration(classes = {MainResource.class})
@ExtendWith(SpringExtension.class)
class MainResourceTest {
    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private GifService gifService;

    @Autowired
    private MainResource mainResource;

    @Test
    void testCheckCurrencyAndGetGif() throws Exception {
        Gif gif = new Gif();
        gif.setData(null);
        when(this.gifService.getRandomGif((String) any())).thenReturn(gif);
        when(this.currencyService.getCurrencyChanges((String) any())).thenReturn("GBP");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/{currency}", "GBP");
        MockMvcBuilders.standaloneSetup(this.mainResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"data\":null}"));
    }

}

