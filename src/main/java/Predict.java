import setData.DAO.GameDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Predict {
    public static StringBuilder xPredict;
    public static void main(String[] args) {
        xPredict = new StringBuilder();
        GameDAO gameDAO = new GameDAO();
        String date = "2018-01-23";
        NewWay newWay = new NewWay(date);
        saveFilesToPredict(xPredict);
    }
    private static void saveFilesToPredict(StringBuilder x) {
        try {
            PrintWriter XWrite = new PrintWriter("C:\\Users\\iStore\\Google Drive\\Learning\\NHL\\Alesha\\machine-learning-ex4\\ex4\\XNewWaForPredict.txt");
            XWrite.println(x);
            XWrite.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
