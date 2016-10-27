package com.albertogiunta.endpoints.justintrain;

import com.albertogiunta.constants.JIT.JAPI;
import com.albertogiunta.model.News;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.albertogiunta.endpoints.trenitalia.NewsEndpoint.getNews;
import static com.albertogiunta.endpoints.trenitalia.NewsEndpoint.getNewsForTrain;

@RestController
public class NewsEndpoint {

    /**
     * It will search through the complete list of "news" (means a list of the trains that have a delay > tot minutes or other problems)
     * looking for a train with same trainNumber and departureTime. If present it will take the whole string reporting the problem.
     *
     * @param trainId Must be the id of the train we want to know more info about.
     * @param time    Must be the absolute departure time (the time the trains will leave the first station of its list of stations)
     *                Format HH:mm
     * @return the string reporting the problem if present, "No matches found" otherwise
     */
    @RequestMapping(value = JAPI.TRAIN_NEWS,
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String trainNews(@PathVariable(value = JAPI.TRAIN_ID) String trainId,
                            @PathVariable(value = JAPI.TIME) String time) {
        return getNewsForTrain(trainId, time);
    }

    /**
     * @return a list of all the news on the whole national mother land.
     */
    @RequestMapping(value = JAPI.NEWS,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<News> news() {
        return getNews();
    }

}
