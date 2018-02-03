import respond.fullgameschedule.GameEntry;
import respond.playersgamelogs.*;
import setData.DAO.GameDAO;
import setData.DAO.GameEntryDao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Получить список игр и собрать статистику для каждой игры
 */
public class NewWay {
    String dateOfString = "2018-01-27";
    int deepLearning = 2;
    StringBuilder X, y, yScore;
    int yLine, xLine, score;
    int iteration;


    public NewWay(Team TeamAway, Team TeamHome){

        String datePredict = "2018-01-20";
        Date date = getDateFromString(datePredict);
        writeDataForPredict(TeamAway, TeamHome, date);
        Predict.xPredict.append(System.getProperty("line.separator"));
    }

    public NewWay(String dateString){

        String datePredict = dateString;
        Date date = getDateFromString(datePredict);
        writeDataForPredictPercent(date);
        Predict.xPredict.append(System.getProperty("line.separator"));
    }

    public NewWay(Date date){
       // writeDataForPredictPercentTeam(date);
        writeDataForPredictTotalGoal(date);
    }

    private void writeDataForPredictTotalGoal(Date date) {
        List<GameEntry> listOfGame = new GameEntryDao().getListGameTodayDate(date);
        System.out.println("game количество "+ listOfGame.size() + " today  " + date.toString());
        for (int i = 0; i < listOfGame.size(); i++){
            List<Team> listTeamsOfGame = getTeamsOfThisGame(listOfGame.get(i));
            System.out.println(" в игре участвовало " + listTeamsOfGame.size() + " команды");
            for(int j = 0; j < listTeamsOfGame.size(); j++){
                iteration = 0;
                System.out.println("Ищем предыдущие игры команды " + listTeamsOfGame.get(j).getID() + " для игры " +
                        listOfGame.get(i).getId());
                getStatBeforePesantagePredict(listTeamsOfGame.get(j), listOfGame.get(i), date);
            }
            getScoreOfGameTotalGoal(Alesha.y, listOfGame.get(i).getId());
            Alesha.y.append(System.getProperty("line.separator"));
            Alesha.xPredictTrain.append(System.getProperty("line.separator"));
            Alesha.createFiles();
            Alesha.saveFiles();
        }
    }

    private void getScoreOfGameTotalGoal(StringBuilder y, int id) {
        score = new GameDAO().getScoreRange(id);
        y.append(score);
    }

    private void getStatBeforeTotalGoal(Team team, Date date) {
        GameDAO gameDao = new GameDAO();

        ArrayList<Double> playerGameLogs =
                gameDao.getPreviousStatOfEachTeamTotalGoal(team, date);

        for (int i = 0; i < playerGameLogs.size(); i++) {
            try{
                Alesha.xPredictTrain.append(playerGameLogs.get(i) + " ");
            }catch (NullPointerException e){
                Alesha.xPredictTrain.append(0 + " ");
            }
        }
    }

    private void writeDataForPredictPercent(Date date) {
        List<GameEntry> listOfGame = new GameEntryDao().getListGameTodayDate(date);
        System.out.println("game количество "+ listOfGame.size() + " today  " + date.toString());
        for (int i = 0; i < listOfGame.size(); i++){
            List<Team> listTeamsOfGame = getTeamsOfThisGame(listOfGame.get(i));
            System.out.println(" в игре участвовало " + listTeamsOfGame.size() + " команды");
            for(int j = 0; j < listTeamsOfGame.size(); j++){
                iteration = 0;
                System.out.println("Ищем предыдущие игры команды " + listTeamsOfGame.get(j).getID() + " для игры " +
                        listOfGame.get(i).getId());
                getStatBeforePesantagePredict(listTeamsOfGame.get(j), listOfGame.get(i), date);
            }
            Predict.xPredict.append(System.getProperty("line.separator"));

        }
    }

    private void writeDataForPredictPercentTeam(Date date) {
        List<GameEntry> listOfGame = new GameEntryDao().getListGameTodayDate(date);
        System.out.println("game количество "+ listOfGame.size() + " today  " + date.toString());
        for (int i = 0; i < listOfGame.size(); i++){
            List<Team> listTeamsOfGame = getTeamsOfThisGame(listOfGame.get(i));
            System.out.println(" в игре участвовало " + listTeamsOfGame.size() + " команды");
            for(int j = 0; j < listTeamsOfGame.size(); j++){
                System.out.println("Ищем предыдущие игры команды " + listTeamsOfGame.get(j).getID() + " для игры " +
                        listOfGame.get(i).getId());
                getStatBeforePersantagePredictTeam(listTeamsOfGame.get(j), listOfGame.get(i), date);
            }
            getScoreOfGameTeam(Alesha.y, listOfGame.get(i).getId());
            //System.out.println(" игра " +  listOfGame.get(i).getId() + " счет " + " " + score );
            Alesha.y.append(System.getProperty("line.separator"));
            Alesha.xPredictTrain.append(System.getProperty("line.separator"));
        }
    }

    private void getStatBeforePersantagePredictTeam(Team idTeam, GameEntry idGame, Date date) {
        GameDAO gameDao = new GameDAO();

        ArrayList<Double> playerGameLogs =
                gameDao.getPreviousStatOfEachPlayerPercentTeam(idTeam, date);

        for (int i = 0; i < playerGameLogs.size(); i++) {
            try{
                Alesha.xPredictTrain.append(playerGameLogs.get(i) + " ");
            }catch (NullPointerException e){
                Alesha.xPredictTrain.append(0 + " ");
            }
        }
    }

    private void getStatBeforePesantagePredict(Team idTeam, GameEntry idGame, Date date) {
        GameDAO gameDao = new GameDAO();

        //получаю игроков предыдущей игры данной команды
        ArrayList<Player> listOfPlayer = gameDao.getPlayers(idTeam, idGame);
        for (int j = 0; j < listOfPlayer.size(); j++) {

            if (listOfPlayer.get(j) != null) {
                int idPplayer = listOfPlayer.get(j).getID();
                ArrayList<Double> playerGameLogs =
                        gameDao.getPreviousStatOfEachPlayerPercent(idPplayer, idTeam, date);

                for (int i = 0; i < playerGameLogs.size(); i++) {
                    Alesha.xPredictTrain.append(playerGameLogs.get(i) + " ");
                }

            } else {
                Alesha.xPredictTrain.append(0 + " " + 0 + " " + 0 + " ");
            }
        }
    }

    private void writeDataForPredict(Team teamHome, Team teamAway, Date date) {
        getStatForPreidect(teamHome, teamAway, date);
    }

    public NewWay() {
        X = new StringBuilder();
        y = new StringBuilder();
        yScore = new StringBuilder();
        Date date = getDateFromString(dateOfString);
        //Метод который сохраняет статистику для игр
        saveStatisticsForLearning(deepLearning, date);
    }

    /**
     * Метод сохраняет статистику для обучения Алёши
     * @param deepLearning В метод передается числовой параметр, который определяет глубину сбора данных предыдущих игр
     *          если указано 2 - значит брать для обучений статистику 2ух предыдущих игр для одной команды
     * @param date указываем дату до которой собираем стату для игры
     */
    private void saveStatisticsForLearning(int deepLearning, Date date) {
        yLine = 1;
        xLine = 1;
        List<GameEntry> listOfGame = new GameEntryDao().getListBeforeDate(date);
        System.out.println("game "+ listOfGame.size() + " before date " + date.toString());
        for (int i = 0; i < listOfGame.size(); i++){
            List<Team> listTeamsOfGame = getTeamsOfThisGame(listOfGame.get(i));
            System.out.println(" в игре участвовало " + listTeamsOfGame.size() + " команды");
            for(int j = 0; j < listTeamsOfGame.size(); j++){
                iteration = 0;
                System.out.println("Ищем предыдущие игры команды " + listTeamsOfGame.get(j).getID() + " для игры " +
                         listOfGame.get(i).getId());
                //getPreviousStatGameForTeam(listTeamsOfGame.get(j), listOfGame.get(i));
                getStatBeforePesantage(listTeamsOfGame.get(j), listOfGame.get(i), date);
            }
                getScoreOfGame(y, listOfGame.get(i).getId());
                X.append(System.getProperty("line.separator"));
                y.append(System.getProperty("line.separator"));
                yScore.append(System.getProperty("line.separator"));
                System.out.println("строка y " + yLine + " строка x " + xLine + " игра " +  listOfGame.get(i).getId()
                        + " " + score );
                yLine++;
                xLine++;
                saveFiles(X, y, yScore);
        }

    }

    private void saveFiles(StringBuilder x, StringBuilder y, StringBuilder yScore) {
        try {
            PrintWriter XWrite = new PrintWriter("C:\\Users\\iStore\\Google Drive\\Learning\\NHL\\Alesha\\machine-learning-ex4\\ex4\\XNewWayContinio.txt");
            PrintWriter YWrite = new PrintWriter("C:\\Users\\iStore\\Google Drive\\Learning\\NHL\\Alesha\\machine-learning-ex4\\ex4\\YNewWayContinio.txt");
            PrintWriter yScoreWrite = new PrintWriter("C:\\Users\\iStore\\Google Drive\\Learning\\NHL\\Alesha\\machine-learning-ex4\\ex4\\YScorepmContio.txt");
            YWrite.println(y);
            YWrite.close();
            XWrite.println(x);
            XWrite.close();
            yScoreWrite.println(yScore);
            yScoreWrite.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

     void getScoreOfGame(StringBuilder y, int idGame){
        score = new GameDAO().getScore(idGame);
        if (score < 0){
            y.append("2");
        }else if((score) > 0){
            y.append("1");
        }else {
            y.append("3");
        }
        yScore.append(score + " ");
    }

    void getScoreOfGameTeam(StringBuilder y, int idGame){
        score = new GameDAO().getScore(idGame);
        if (score < 0){
            y.append("2");
        }else if((score) > 0){
            y.append("1");
        }else {
            y.append("3");
        }
    }

    /**
     * Метод собирает статистику по игрокам двух команд из предыдущие игры
     * @param idTeam id команды по которой ищем предыдущую игр
     * StringBuilder - сохраняем часть строки параметров для обучения Алеши
     */
    private void getPreviousStatGameForTeam(Team idTeam, Date date) {
        GameDAO gameDao = new GameDAO();
        Team idHome = null;
        Team idAway = null;
        Game idGameBefore = null;
        //получаю игроков предыдущей игры данной команды
        ArrayList<PlayerGameLogs> playerGameLogs =  gameDao.getPreviousPlayersOfGame(idTeam, date);
        if(iteration < 2) {
            System.out.println("игроков в предыдущей игре было " + playerGameLogs.size());
            int total = 0;

            for (int i = 0; i < playerGameLogs.size(); i++) {
            int goalsHome = 0;
            int plusMinusHome = 0;
            int assistHome = 0;
            int hitsHome = 0;
            int faceOffWinsHome = 0;
            int faceOffLossesHome = 0;
            int lossesHome = 0;

            int goalsAway = 0;
            int plusMinusAway = 0;
            int assistAway = 0;
            int hitsAway = 0;
            int faceOffWinsAway = 0;
            int faceOffLossesAway = 0;
            int lossesAway = 0;

            UnitStat unitStat = new UnitStat();
            unitStat.setText("0");
            Stats stats = new Stats();
//            Date dateNextPrevious = playerGameLogs.get(0).getGame().getDate();


                if(playerGameLogs.get(i) != null) {
                    stats = playerGameLogs.get(i).getStats();
                    if (stats.getGoals() == null) {
                        stats.setGoals(unitStat);
                    }
                    if (stats.getPlusMinus() == null) {
                        stats.setPlusMinus(unitStat);
                    }
                    if (stats.getFaceoffLosses() == null) {
                        stats.setFaceoffLosses(unitStat);
                    }
                    if (stats.getFaceoffWins() == null) {
                        stats.setFaceoffWins(unitStat);
                    }
                    if (stats.getAssists() == null) {
                        stats.setAssists(unitStat);
                    }
                    if (stats.getLosses() == null) {
                        stats.setLosses(unitStat);
                    }
                    if (stats.getHits() == null) {
                        stats.setHits(unitStat);
                    }
                    if (playerGameLogs.get(i).getTeam().equals(playerGameLogs.get(i).getGame().getHomeTeam())) {
                        idHome = playerGameLogs.get(i).getTeam();
                        goalsHome = getIntFromString(goalsHome, stats.getGoals().getText());
                        plusMinusHome = getIntFromString(plusMinusHome, stats.getPlusMinus().getText());
                        assistHome = getIntFromString(assistHome, stats.getAssists().getText());
                        hitsHome = getIntFromString(hitsHome, stats.getHits().getText());
                        faceOffLossesHome = getIntFromString(faceOffLossesHome, stats.getFaceoffLosses().getText());
                        faceOffWinsHome = getIntFromString(faceOffWinsHome, stats.getFaceoffWins().getText());
                        lossesHome = getIntFromString(lossesHome, stats.getLosses().getText());
                    } else {
                        idAway = playerGameLogs.get(i).getTeam();
                        goalsAway = getIntFromString(goalsAway, stats.getGoals().getText());
                        plusMinusAway = getIntFromString(plusMinusAway, stats.getPlusMinus().getText());
                        assistAway = getIntFromString(assistAway, stats.getAssists().getText());
                        hitsAway = getIntFromString(hitsAway, stats.getHits().getText());
                        faceOffLossesAway = getIntFromString(faceOffLossesAway, stats.getFaceoffLosses().getText());
                        faceOffWinsAway = getIntFromString(faceOffWinsAway, stats.getFaceoffWins().getText());
                        lossesAway = getIntFromString(lossesAway, stats.getLosses().getText());
                    }
                }
                System.out.print("home " + goalsHome + " " + plusMinusHome + " " + assistHome + " " + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome + " " + lossesHome + " ");
                System.out.print("away " + goalsAway + " " + plusMinusAway + " " + assistAway + " " + hitsAway
                        + " " + faceOffLossesAway + " " + faceOffWinsAway + " " + lossesAway + " ");
                Alesha.xPredictTrain.append(goalsHome + " " + plusMinusHome + " " + assistHome + " " + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome + " " + lossesHome + " ");
                Alesha.xPredictTrain.append(goalsAway + " " + plusMinusAway + " " + assistAway + " " + hitsAway
                        + " " + faceOffLossesAway + " " + faceOffWinsAway + " " + lossesAway + " ");
                total += goalsAway + goalsHome;
            }
            Alesha.xPredictTrain.append(total + " ");
         //   iteration++;
         //   getPreviousStatGameForTeam(idHome, dateNextPrevious);
          //  getPreviousStatGameForTeam(idAway, dateNextPrevious);

        }
    }



    private void getStatBefore(Team idTeam, GameEntry idGame, Date date) {

        GameDAO gameDao = new GameDAO();

        UnitStat unitStat = new UnitStat();
        unitStat.setText("0");
        //получаю игроков предыдущей игры данной команды
        ArrayList<Player> listOfPlayer = gameDao.getPlayers(idTeam, idGame);
        for(int j = 0; j < listOfPlayer.size(); j++){
            int goalsHome = 0;
            int plusMinusHome = 0;
            int assistHome = 0;
            int hitsHome = 0;
            int faceOffWinsHome = 0;
            int faceOffLossesHome = 0;
            int lossesHome = 0;

            if(listOfPlayer.get(j) != null){
                int idPplayer = listOfPlayer.get(j).getID();
                ArrayList<PlayerGameLogs> playerGameLogs =  gameDao.getPreviousStatOfEeachPlayer(idPplayer, idTeam, date);
                System.out.println("Игр у игрока " + playerGameLogs.size());

                for(int i = 0; i < playerGameLogs.size(); i++){
                    Stats stats = playerGameLogs.get(i).getStats();
                    if(stats.getGoals() == null) {
                        stats.setGoals(unitStat);
                    }
                    if(stats.getPlusMinus() == null){
                        stats.setPlusMinus(unitStat);
                    }
                    if(stats.getFaceoffLosses() == null){
                        stats.setFaceoffLosses(unitStat);
                    }
                    if(stats.getFaceoffWins() == null){
                        stats.setFaceoffWins(unitStat);
                    }
                    if(stats.getAssists() == null){
                        stats.setAssists(unitStat);
                    }
                    if(stats.getLosses() == null){
                        stats.setLosses(unitStat);
                    }
                    if(stats.getHits() == null){
                        stats.setHits(unitStat);
                    }
                    goalsHome = getIntFromString(goalsHome, stats.getGoals().getText());
                    plusMinusHome = getIntFromString(plusMinusHome, stats.getPlusMinus().getText());
                    assistHome = getIntFromString(assistHome, stats.getAssists().getText());
                    hitsHome = getIntFromString(hitsHome, stats.getHits().getText());
                    faceOffLossesHome = getIntFromString(faceOffLossesHome, stats.getFaceoffLosses().getText());
                    faceOffWinsHome = getIntFromString(faceOffWinsHome, stats.getFaceoffWins().getText());
                    lossesHome = getIntFromString(lossesHome, stats.getLosses().getText());
                }
                System.out.printf("home/away " + + goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
                X.append(goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
            }else {
                goalsHome = 0;
                plusMinusHome = 0;
                assistHome = 0;
                hitsHome = 0;
                faceOffLossesHome = 0;
                faceOffWinsHome = 0;
                lossesHome = 0;

                System.out.printf("home/away " + + goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
                X.append(goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
            }

        }
    }

    private void getStatBeforePesantage(Team idTeam, GameEntry idGame, Date date) {

        GameDAO gameDao = new GameDAO();

        //получаю игроков предыдущей игры данной команды
        ArrayList<Player> listOfPlayer = gameDao.getPlayers(idTeam, idGame);
        for(int j = 0; j < listOfPlayer.size(); j++){

            if(listOfPlayer.get(j) != null){
                int idPplayer = listOfPlayer.get(j).getID();
                ArrayList<Double> playerGameLogs =
                        gameDao.getPreviousStatOfEachPlayerPercent(idPplayer, idTeam, date);

                for(int i = 0; i < playerGameLogs.size(); i++){
                    X.append(playerGameLogs.get(i) + " ");
                }

            }else {
                X.append(0 + " " + 0 + " "  + 0 + " ");
            }

        }
    }


    private void getStatForPreidect(Team idHome, Team idAway, Date date) {
        grabeAndWriteDataForPredict(idHome, date);
        grabeAndWriteDataForPredict(idAway, date);
    }

    private void grabeAndWriteDataForPredict(Team idTeam, Date date){
        GameDAO gameDao = new GameDAO();
        UnitStat unitStat = new UnitStat();
        unitStat.setText("0");
        //получаю игроков предыдущей игры данной команды
        ArrayList<Player> listOfPlayer = gameDao.getPlayersByTeam(idTeam);
        for(int j = 0; j < listOfPlayer.size(); j++){
            int goalsHome = 0;
            int plusMinusHome = 0;
            int assistHome = 0;
            int hitsHome = 0;
            int faceOffWinsHome = 0;
            int faceOffLossesHome = 0;
            int lossesHome = 0;

            if(listOfPlayer.get(j) != null){
                int idPplayer = listOfPlayer.get(j).getID();
                ArrayList<PlayerGameLogs> playerGameLogs =  gameDao.getPreviousStatOfEeachPlayer(idPplayer, idTeam, date);
                System.out.println("Игр у игрока " + playerGameLogs.size());

                for(int i = 0; i < playerGameLogs.size(); i++){
                    Stats stats = playerGameLogs.get(i).getStats();
                    if(stats.getGoals() == null) {
                        stats.setGoals(unitStat);
                    }
                    if(stats.getPlusMinus() == null){
                        stats.setPlusMinus(unitStat);
                    }
                    if(stats.getFaceoffLosses() == null){
                        stats.setFaceoffLosses(unitStat);
                    }
                    if(stats.getFaceoffWins() == null){
                        stats.setFaceoffWins(unitStat);
                    }
                    if(stats.getAssists() == null){
                        stats.setAssists(unitStat);
                    }
                    if(stats.getLosses() == null){
                        stats.setLosses(unitStat);
                    }
                    if(stats.getHits() == null){
                        stats.setHits(unitStat);
                    }
                    goalsHome = getIntFromString(goalsHome, stats.getGoals().getText());
                    plusMinusHome = getIntFromString(plusMinusHome, stats.getPlusMinus().getText());
                    assistHome = getIntFromString(assistHome, stats.getAssists().getText());
                    hitsHome = getIntFromString(hitsHome, stats.getHits().getText());
                    faceOffLossesHome = getIntFromString(faceOffLossesHome, stats.getFaceoffLosses().getText());
                    faceOffWinsHome = getIntFromString(faceOffWinsHome, stats.getFaceoffWins().getText());
                    lossesHome = getIntFromString(lossesHome, stats.getLosses().getText());
                }
                System.out.printf("home/away " + + goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
                Predict.xPredict.append(goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
            }else {
                goalsHome = 0;
                plusMinusHome = 0;
                assistHome = 0;
                hitsHome = 0;
                faceOffLossesHome = 0;
                faceOffWinsHome = 0;
                lossesHome = 0;

                System.out.printf("home/away " + + goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
                Predict.xPredict.append(goalsHome + " " + plusMinusHome + " "  + assistHome + " "  + hitsHome
                        + " " + faceOffLossesHome + " " + faceOffWinsHome  + " "  + lossesHome  + " " );
            }

        }
    }


    private int getIntFromString(int plus, String text){
        if(text != null){
            return (int) (Double.valueOf(text) + 0);
        }else{
            return plus;
        }
    }
    /**
     *
     * @param gameEntry
     * @return
     */
    private List<Team> getTeamsOfThisGame(GameEntry gameEntry) {
        Team homeTeam = gameEntry.getHomeTeam();
        Team awayTeam = gameEntry.getAwayTeam();
        System.out.println("в игре учоствовали: дома - " + homeTeam.getID() + " гости " + awayTeam.getID());
        ArrayList<Team> listTeamOfGame = new ArrayList<>();
        listTeamOfGame.add(homeTeam);
        listTeamOfGame.add(awayTeam);
        return listTeamOfGame;
    }

    public Date getDateFromString(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


}
