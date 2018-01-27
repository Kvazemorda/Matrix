package urlquery;

import com.google.gson.Gson;
import respond.fullgameschedule.RootSchedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class QueryToSportFeed {
    UrlQuery urlQuery;

    public QueryToSportFeed(UrlQuery urlQuery) {
        this.urlQuery = urlQuery;
    }

    public RootSchedule getRootGameSchedule(String period){
        HttpURLConnection connection = getHttpUrlConnectionForFullGameSchedule(period);
        InputStream content = null;
        try {
            content = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in   =
                new BufferedReader (new InputStreamReader(content));

        RootSchedule rootSchedule;
        Gson gson = new Gson();
        rootSchedule = gson.fromJson(in, RootSchedule.class);

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootSchedule;
    }

    public HttpURLConnection getHttpUrlConnectionForFullGameSchedule(String period){
        // подготавливаю строку запроса
        String typeQuery = "full_game_schedule.json";

        URL url = null;
        try {
            url = new URL(urlQuery.filterPerioud(period) + typeQuery);
            System.out.println(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String userPassword = "Kvazemorda:Hohlov7203013320";
        byte[] message = userPassword.getBytes(StandardCharsets.UTF_8);
        String encoding = Base64.getEncoder().encodeToString(message);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setDoOutput(true);
        connection.setRequestProperty  ("Authorization", "Basic " + encoding);

        return connection;
    }
}
