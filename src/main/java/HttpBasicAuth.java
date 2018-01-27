import com.google.gson.Gson;
import respond.playersgamelogs.RootJson;
import setData.TeamAndPeriod;
import urlquery.UrlQuery;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;


public class HttpBasicAuth {

    public static void main(String[] args) {

        try {
            ArrayList<String> listOfTeamName = getListOfTeam();
            ArrayList<String> listOfPeriod = getListOfSeason();

            for(int i = 0; i < listOfPeriod.size(); i++){
                for(int j = 0; j < listOfTeamName.size(); j++){

                    String teamName = listOfTeamName.get(j);
                    String period = listOfPeriod.get(i);

                    HttpURLConnection connection = getUrl(teamName, period);
                    InputStream content = connection.getInputStream();
                    BufferedReader in   =
                            new BufferedReader (new InputStreamReader(content));

                    RootJson playergamelogs = new RootJson();
                    Gson gson = new Gson();
                    playergamelogs = gson.fromJson(in, RootJson.class);
                    saveTeam(playergamelogs, teamName+"-"+period);
                    in.close();
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpURLConnection getUrl(String teamName, String period){
        // подготавливаю строку запроса
        UrlQuery urlQuery = new UrlQuery();
        String typeQuery = "player_gamelogs.json";
        String filter = "?team=" + teamName;

        URL url = null;
        try {
            url = new URL(urlQuery.filterPerioud(period) + typeQuery + "/" + filter);
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

    public static void saveTeam(RootJson rootJson, String teamName){
        try {
            System.out.println(rootJson.toString());
            String path = "C:\\Users\\iStore\\IdeaProjects\\Matrix\\src\\main\\java\\respond\\serializable\\" + teamName;
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rootJson);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getListOfSeason(){

        ArrayList<String> list =  new TeamAndPeriod().getListOfPeriod();
        return list;
    }

    public static ArrayList<String> getListOfTeam(){

        ArrayList<String> list = new TeamAndPeriod().getListOfTeam();
    return list;
    }
}