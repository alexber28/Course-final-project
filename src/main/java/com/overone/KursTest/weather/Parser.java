package com.overone.KursTest.weather;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Parser {

    private static Document getPage() throws IOException {
        String url = "http://world-weather.by/pogoda/belarus/minsk/3days/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    @GetMapping("/weather")
    public static String getWeather(Model model) throws IOException {
        List<Weather> days = new ArrayList<>();
        Document page  = getPage();
        Elements tableWeather = page.select("div[class=weather-short]");

        Elements names = tableWeather.select("tbody");

        for (Element name:
                tableWeather) {
            String date = "";
            date = name.select("div[class=dates short-d]").text();
            if(date.equals(""))
                date = name.select("div[class=dates short-d red]").text();
            //System.out.println(date + "    Температура     Явление    Давление   Влажность   Ветер");

            List<Elements> elements = new ArrayList<>();
            elements.add(new Elements(name.select("tr[class=night]")));
            elements.add(new Elements(name.select("tr[class=morning]")));
            elements.add(new Elements(name.select("tr[class=day]")));
            elements.add(new Elements(name.select("tr[class=evening]")));

            for (Elements elem:
                    elements) {
                String weatherDay = elem.select("td[class=weather-day]").text();
                String kindOfWth = elem.select("img").first().attr("alt");
                String temperature = elem.select("td[class=weather-temperature]").text();
                String pressure = elem.select("td[class=weather-pressure]").text();
                String kindOfWind = elem.select("img").last().attr("alt");
                String wind = elem.select("td[class=weather-wind]").text();
                String humidity = elem.select("td[class=weather-humidity]").text();
                Weather day = (new Weather(date, weatherDay, kindOfWth, temperature, pressure, kindOfWind, wind, humidity));
                days.add(day);
            }
            //System.out.println(elements);
        }
        //System.out.println(days);
        model.addAttribute("days", days);
        return "weather";
    }
}