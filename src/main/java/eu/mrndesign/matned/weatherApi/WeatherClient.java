package eu.mrndesign.matned.weatherApi;

import com.google.gson.Gson;
import eu.mrndesign.matned.weatherApi.model.FullWeatherInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {

    private static final String API_TOKEN = "fd2feec3b4ae703af4a49fb7fc630e66";

    private static final HttpClient httpClient = HttpClient.newBuilder().build();



//  api.openweathermap.org/data/2.5/weather?q=Wrocław,pl&APPID=fd2feec3b4ae703af4a49fb7fc630e66

    public String getCityWeatherJson(String city) throws IOException, InterruptedException {

        String basicWeatherUrlTemplate = "https://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";

        String fullUrl = String.format(basicWeatherUrlTemplate, city, API_TOKEN);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status CODE: " + response.statusCode());

        return response.body();
    }






    private FullWeatherInfo parseWeatherJson(String weatherJson){
        Gson gson = new Gson();

        FullWeatherInfo fullWeatherInfo = gson.fromJson(weatherJson, FullWeatherInfo.class);


        return fullWeatherInfo;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WeatherClient weatherClient = new WeatherClient();
        String weather = weatherClient.getCityWeatherJson("Wrocław,pl");

        System.out.println(weather);




        FullWeatherInfo fullWeatherInfo = weatherClient.parseWeatherJson(weather);

        System.out.println(fullWeatherInfo);


    }


}
