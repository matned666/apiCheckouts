package eu.mrndesign.matned.nasaApi;

//ANwt3XvpGs7HvBOdmLoEowHZuCPKQDVbBqK6NaSz


import com.google.gson.Gson;

import com.google.gson.Gson;
import eu.mrndesign.matned.nasaApi.model.NSEPInfo;
import eu.mrndesign.matned.weatherApi.WeatherClient;
import eu.mrndesign.matned.weatherApi.model.FullWeatherInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NasaMain {
    private static final String API_TOKEN = "ANwt3XvpGs7HvBOdmLoEowHZuCPKQDVbBqK6NaSz";

    private static final HttpClient httpClient = HttpClient.newBuilder().build();




    public String getNSEPJson() throws IOException, InterruptedException {

        String basicNasaSolarEnergeticParticleApi = "https://api.nasa.gov/planetary/apod?api_key=%s";

        String fullUrl = String.format(basicNasaSolarEnergeticParticleApi, API_TOKEN);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status CODE: " + response.statusCode());

        return response.body();
    }






    private NSEPInfo parseNSEPJson(String nSEPJson){
        Gson gson = new Gson();

        NSEPInfo nsepInfo = gson.fromJson(nSEPJson, NSEPInfo.class);

        return nsepInfo;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NasaMain nasaMain = new NasaMain();
        String nSEP = nasaMain.getNSEPJson();

        System.out.println(nSEP);

        NSEPInfo nsepInfo = nasaMain.parseNSEPJson(nSEP);

        System.out.println(nsepInfo);


    }

}
