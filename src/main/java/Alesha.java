import setData.DAO.GameDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Alesha {
    public static StringBuilder xPredictTrain, y;
    public static PrintWriter XWrite, YWrite, yScoreWrite;

    public static void main(String[] args) {
        xPredictTrain = new StringBuilder();
        y = new StringBuilder();
        dataForTrainAleshaWithTotalScore();

    }

    private static void dataForTrainAleshaWithTotalScore(){
        ArrayList<Date> datesOfGames = new GameDAO().getDatesOfGames();

        Date lastDate = new Date();
        for (Date date: datesOfGames){
            if(!lastDate.equals(date)){
                lastDate = date;
                NewWay fuh = new NewWay(date);

            }
        }
    }

    private static void dataForTrainAleshaWitnWLD(){
        ArrayList<Date> datesOfGames = new GameDAO().getDatesOfGames();

        Date lastDate = new Date();
        for (Date date: datesOfGames){
            if(!lastDate.equals(date)){
                lastDate = date;
                NewWay fuh = new NewWay(date);
                createFiles();
                saveFiles();
            }
        }
    }

    public static void createFiles(){
        try {
            XWrite = new PrintWriter("C:\\Users\\iStore\\Google Drive\\Learning\\NHL\\Alesha\\machine-learning-ex4\\ex4\\XNewWayContinio.txt");
            YWrite = new PrintWriter("C:\\Users\\iStore\\Google Drive\\Learning\\NHL\\Alesha\\machine-learning-ex4\\ex4\\YNewWayContinio.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveFiles() {
        System.out.println("запись игр");
            YWrite.println(y);
            YWrite.close();
            XWrite.println(xPredictTrain);
            XWrite.close();
    }
}
