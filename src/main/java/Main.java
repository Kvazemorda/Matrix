import Services.HibernateSessionFactory;
import com.google.gson.Gson;
import respond.playersgamelogs.PlayerGameLogs;
import respond.playersgamelogs.PlayersLogs;
import respond.playersgamelogs.RootJson;
import setData.DAO.PlayerLogsDao;
import setData.TeamAndPeriod;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

      createDataBase();
     //   createDataBaseFromFile();
        HibernateSessionFactory.shutdown();
    }

    private static void createDataBaseFromFile() {
        TeamAndPeriod teamAndPeriod = new TeamAndPeriod();
        ArrayList<String> listOfTeamName = teamAndPeriod.getListOfTeam();
        ArrayList<String> listOfPeriod = teamAndPeriod.getListOfPeriod();

        for(int i = 0; i < listOfPeriod.size(); i++) {
            for (int j = 0; j < listOfTeamName.size(); j++) {
                String teamName = listOfTeamName.get(j);
                String period = listOfPeriod.get(i);
                loadFromFolder(teamName+"-"+period);
            }
        }

    }

    private static void createDataBase() {
        TeamAndPeriod teamAndPeriod = new TeamAndPeriod();
            ArrayList<String> listOfTeamName = teamAndPeriod.getListOfTeam();
            ArrayList<String> listOfPeriod = teamAndPeriod.getListOfPeriod();

            for(int i = 0; i < listOfPeriod.size(); i++){
                for(int j = 0; j < listOfTeamName.size(); j++){

                    String teamName = listOfTeamName.get(j);
                    String period = listOfPeriod.get(i);

                    HttpURLConnection connection = HttpBasicAuth.getUrl(teamName, period);
                    InputStream content = null;
                    try {
                        content = connection.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BufferedReader in   =
                            new BufferedReader (new InputStreamReader(content));

                    RootJson playergamelogs = new RootJson();
                    Gson gson = new Gson();
                    playergamelogs = gson.fromJson(in, RootJson.class);
                    saveObjectsToDataBase(playergamelogs);
                    saveTeam(playergamelogs, teamName+"-"+period);

                }
            }

    }

    public static void loadFromFolder(String teamName){
        String path = "C:\\Users\\iStore\\IdeaProjects\\Matrix\\src\\main\\java\\respond\\serializable\\" + teamName;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            RootJson root = (RootJson) ois.readObject();
            saveObjectsToDataBase(root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

    private static void saveObjectsToDataBase(RootJson rootJson){
        PlayersLogs playerLogs = rootJson.getPlayergamelogs();
        if (playerLogs != null){
            ArrayList<PlayerGameLogs> playerGameLogs = playerLogs.getGamelogs();
            if(playerGameLogs != null){
                new PlayerLogsDao().save(playerGameLogs);
            }
        }

    }

}
