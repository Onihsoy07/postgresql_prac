package com.test.postgresql_test.Service.ServiceImpl;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.test.postgresql_test.Service.CusSafelist;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class BaseDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String xssResult;
        if ("content".equals(p.getCurrentName())) {
            xssResult = xssContent(p.getText());
        } else if ("comment".equals(p.getCurrentName())) {
            xssResult = xssComment(p.getText());
        } else {
            xssResult = xssBasic(p.getText());
        }

        return xssResult;
    }

    private String xssContent(String text) {
        return Jsoup.clean(text, CusSafelist.cusBoardXSS());
    }

    private String xssComment(String text) {
        return Jsoup.clean(text, CusSafelist.cusBoardXSS());
    }

    private String xssBasic(String text) {
        return Jsoup.clean(text, Safelist.none());
    }


}
