package com.test.postgresql_test.Service;

import org.jsoup.safety.Safelist;

public class CusSafelist {

    public static Safelist cusBoardXSS() {
        return Safelist.relaxed()
                .addProtocols("img", "src", "data")
                .addTags("font", "tr", "td")
                .addAttributes("font", "style", "color")
                .addAttributes("table", "class");
    }
}
