package com.albertogiunta.endpoints.trenitalia;

import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TAPI;
import com.albertogiunta.model.News;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsEndpoint {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static List<News> getNews() {
        News[] newsArray = REST_TEMPLATE.getForObject(TAPI.DNS + TAPI.NEWS, News[].class);
        return newsArray.length > 0 ? Arrays.asList(newsArray) : new LinkedList<>();
    }

    public static String getNewsForTrain(String requestedtrainId, String requestedTime) {
        Pattern p = Pattern.compile("(\\d+:\\d+)|\\d+");
        Matcher m;
        String trainId;
        String departureTime;

        for (News news : getNews()) {
            m = p.matcher(news.getMessage());
            trainId = m.find() ? m.group() : "";
            departureTime = m.find() ? m.group() : "";
            if (trainId.equalsIgnoreCase(requestedtrainId)
                    && departureTime.equalsIgnoreCase(requestedTime)) {
                return news.getMessage();
            }
        }

        return JVALUE.NAS;
    }
}
