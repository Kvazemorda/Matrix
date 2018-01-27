import setData.DAO.GameDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Predict {
    public static StringBuilder xPredict;
    public static void main(String[] args) {
        xPredict = new StringBuilder();
        GameDAO gameDAO = new GameDAO();

        new NewWay(gameDAO.getTeam(27),gameDAO.getTeam(15));
        new NewWay(gameDAO.getTeam(7),gameDAO.getTeam(6));
        new NewWay(gameDAO.getTeam(9),gameDAO.getTeam(22));
        new NewWay(gameDAO.getTeam(2),gameDAO.getTeam(23));
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
