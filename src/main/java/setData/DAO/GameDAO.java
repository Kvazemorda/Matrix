package setData.DAO;

import Services.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import respond.fullgameschedule.GameEntry;
import respond.playersgamelogs.*;

import java.util.ArrayList;
import java.util.Date;

public class GameDAO {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public GameDAO() {
    }

    public int getScore(int idGame){
        String hql = "select gameLogs from PlayerGameLogs gameLogs " +
                "where gameLogs.game.id = :game";

        Query query = session.createQuery(hql);
        query.setParameter("game", idGame);
        ArrayList<PlayerGameLogs> games = (ArrayList) query.list();
        int score = 0;

        for(int i = 0; i < games.size(); i++){
                Team home = games.get(i).getGame().getHomeTeam();
                Stats stats = games.get(i).getStats();

            if (stats.getGoals() != null){
                if(games.get(i).getTeam().equals(home)){
                    score = (int) ( score + Double.valueOf(stats.getGoals().getText()));
                }else {
                    score = (int) ( score - Double.valueOf(stats.getGoals().getText()));
                }
            }
        }
        return score;
    }

    public Team getTeam(int idTeam){
        String hql = "select team from Team team " +
                "where team.id = :team";

        Query query = session.createQuery(hql);
        query.setParameter("team", idTeam);

        return (Team) query.list().get(0);
    }


    /**
     * Первым запросом надо получить предыдущую игру для определенной команды
     * Второым запросом для предыдущей игры получить список игроков
     * @param team команда
     * @return
     */
    public ArrayList<PlayerGameLogs> getPreviousPlayersOfGame(Team team, Date currentDate){
        int countPreviousGame = 1;
        String hql = " select games from GameEntry games " +
                "where games.date < :currentDate " +
                "and (games.awayTeam.id = :team " +
                "or games.homeTeam.id = :team)" +
                "order by games.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",team.getID());
        query.setParameter("currentDate", currentDate);
        query.setMaxResults(countPreviousGame);
        int previousIdGame = 0;
        ArrayList<GameEntry> gameEntries = (ArrayList<GameEntry>) query.list();
        Query query1 = null;
        ArrayList<PlayerGameLogs> listOfPlayerStat = new ArrayList<>();
        if(query.list().size() != 0){
            for (GameEntry gameEntry: gameEntries){
                previousIdGame = gameEntry.getId();

                String hql1 = " select players from PlayerGameLogs players " +
                        "where players.game.id = :gameCurrent " +
                        "order by players.player.position ";

                query1 = session.createQuery(hql1);
                query1.setParameter("gameCurrent", previousIdGame);
                listOfPlayerStat.addAll(query1.list());
            }
            System.out.println("!!!!!!! " + listOfPlayerStat.size());
        } if(listOfPlayerStat.size() <= 40){
            int size = 40 - listOfPlayerStat.size();
            for(int i = 0; i < size; i++){
                listOfPlayerStat.add(null);
            }
        }
        return listOfPlayerStat;
    }

    public ArrayList<PlayerGameLogs> getPreviousPlayersOfSomeGame(Team team, Game gameCurrent){
        String hql = " select players.player from PlayerGameLogs players " +
                "where players.game.id < :gameCurrent " +
                "and players.team.id = :team " +
                "order by players.game.id desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",team.getID());
        query.setParameter("gameCurrent",gameCurrent.getId());
        ArrayList<PlayerGameLogs> listOfPlayerStat = new ArrayList<>();
        if(query.list().size() != 0 && query.list().size()>10){
            for (int i = 0; i <= 9; i++){
                listOfPlayerStat.add((PlayerGameLogs) query.list().get(i));
            }
          return (ArrayList) query.list();
        }
        return listOfPlayerStat;
    }


    public ArrayList<PlayerGameLogs> getPreviousStatOfEeachPlayer(int idPlayer, Team idTeam, Date gameCurrent){
        String hql = " select players from PlayerGameLogs players " +
                "where players.game.date < :gameCurrent " +
                "and players.player.ID = :player " +
                "and players.team = :team " +
                "order by players.game.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("player",idPlayer);
        query.setParameter("team",idTeam);
        query.setParameter("gameCurrent", gameCurrent);

        ArrayList<PlayerGameLogs> listOfPlayerStat = new ArrayList<>();
        if(query.list().size() != 0 && query.list().size()>10){
            for (int i = 0; i <= 4; i++){
                listOfPlayerStat.add((PlayerGameLogs) query.list().get(i));
            }
            return (ArrayList) query.list();
        }
        return listOfPlayerStat;
    }

    public ArrayList<Double> getPreviousStatOfEachPlayerPercent(int idPlayer, Team idTeam,
                                                                Date gameCurrent){
        String hql = " select players.game from PlayerGameLogs players " +
                "where players.game.date < :gameCurrent " +
                "and players.player.ID = :player " +
                "order by players.game.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("player",idPlayer);
        query.setParameter("gameCurrent", gameCurrent);
        query.setMaxResults(15);

        ArrayList<Game> games;
        games = (ArrayList<Game>) query.list();

        double win = 0;
        double lose = 0;
        double draw = 0;

        for(Game game: games){
            int score = getScore(game.getId());
            if(game.getHomeTeam().equals(idTeam)){

                if (score < 0){
                    lose++;
                }else if((score) > 0){
                    win++;
                }else {
                    draw++;
                }
            }else {
                if (score < 0){
                    win++;
                }else if((score) > 0){
                    lose++;
                }else {
                    draw++;
                }
            }

        }
        Double sizeGame =  (double) games.size();
        Double winPercent = (win/sizeGame) * 100;
        Double losePercent = (lose/sizeGame) * 100;
        Double drawPercent = (draw/sizeGame) * 100;
        double shoudBeZero  = sizeGame - win - lose - draw;
        System.out.println("игрок " + idPlayer + " победил " + win + " проиграл " + lose + " сыграл в ничью " + draw +
        " из " + sizeGame +  " игр " + shoudBeZero);
        ArrayList<Double> percent = new ArrayList<>();
        percent.add(winPercent);
        percent.add(losePercent);
        percent.add(drawPercent);

        return percent;
    }

    public ArrayList<Double> getPreviousStatOfEachPlayerPercentTeam(Team idTeam,
                                                                Date gameCurrent){
        String hql = " select game from Game game " +
                "where game.date < :gameCurrent " +
                "and (game.awayTeam = :team " +
                "or game.homeTeam = :team) " +
                "order by game.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",idTeam);
        query.setParameter("gameCurrent", gameCurrent);
        query.setMaxResults(10);

        ArrayList<Game> games;
        games = (ArrayList<Game>) query.list();

        ArrayList<Double> percent = new ArrayList<>();
        double win = 0;
        double lose = 0;
        double draw = 0;
        for(int i = 0; i < games.size(); i++){
            Game game = games.get(i);
            int score = getScore(game.getId());
            if(game.getHomeTeam().equals(idTeam)){
            if (score < 0){
                lose++;
            }else if((score) > 0){
                win++;
            }else {
                draw++;
            }
        }else {
            if (score < 0){
                win++;
            }else if((score) > 0){
                lose++;
            }else {
                draw++;
            }
        }
            percent.add((double)score);
        }
        percent.add(win/(double) (games.size()));
        percent.add(lose/(double) (games.size()));
        percent.add(draw/(double) (games.size()));
        return percent;
    }

    public ArrayList<Double> getPreviousStatOfEachTeamTotalGoal(Team idTeam, Date gameCurrent){
        int maxResult = 7;
        String hql = " select game from Game game " +
                "where game.date < :gameCurrent " +
                "and (game.awayTeam = :team " +
                "or game.homeTeam = :team) " +
                "order by game.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",idTeam);
        query.setParameter("gameCurrent", gameCurrent);
        query.setMaxResults(maxResult);

        ArrayList<Game> games;
        games = (ArrayList<Game>) query.list();

        ArrayList<Double> percent = new ArrayList<>();
        for(int i = 0; i < games.size(); i++){
            Game game = games.get(i);
            int score = getScoreRange(game.getId());
            percent.add((double)score);
            int score1 = getTotalGoal(game.getId());
            percent.add((double)score1);
        }
        if(percent.size() < maxResult){
            int diff = maxResult - percent.size();
            for(int i = 0; i < diff; i++){
                percent.add(0.0);
            }
        }
        return percent;
    }


    public ArrayList<PlayerGameLogs> getPreviousPlayersOfSomeGameForPredict(int idTeam){
        String hql = " select players from PlayerGameLogs players " +
                "where players.team.id = :team " +
                "order by players.game.id desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",idTeam);
        ArrayList<PlayerGameLogs> listOfPlayerStat = new ArrayList<>();
        if(query.list().size() != 0 && query.list().size()>10){
            for (int i = 0; i <= 9; i++){
                listOfPlayerStat.add((PlayerGameLogs) query.list().get(i));
            }
            return (ArrayList) query.list();
        }
        return listOfPlayerStat;
    }

    public ArrayList<Player> getPlayers(Team idTeam, GameEntry idGame) {
        String hql = " select players.player from PlayerGameLogs players " +
                "where players.team = :team " +
                "and players.game.id = :game " +
                "order by players.player.position";

        Query query = session.createQuery(hql);
        query.setParameter("team",idTeam);
        query.setParameter("game",idGame.getId());
        query.setMaxResults(25);
        ArrayList<Player> list = new ArrayList<>();
        list.addAll(query.list());
        if(list.size() < 25){
            for(int i = list.size(); i < 25; i++){
                list.add(null);
            }
            System.out.println("количество играков в команде " + list.size());
        }

        return list;
    }

    public ArrayList<Player> getPlayersByTeam(Team idTeam) {
        Game game = getPreviousGameByTeam(idTeam);
        String hql = " select playerGamelog.player from PlayerGameLogs playerGamelog " +
                "where playerGamelog.team = :team " +
                "and playerGamelog.game = :game " +
                "order by playerGamelog.game.id desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",idTeam);
        query.setParameter("game", game);
        ArrayList<Player> list = new ArrayList<>();
        list.addAll(query.list());
        if(list.size() < 25){
            for(int i = list.size(); i < 25; i++){
                list.add(null);
            }
            System.out.println("!!!!!количество играков в команде для предсказания " + list.size());
        }

        return list;
    }

    private Game getPreviousGameByTeam(Team idTeam) {
        String hql = " select playerGamelog.game from PlayerGameLogs playerGamelog " +
                "where playerGamelog.team = :team " +
                "order by playerGamelog.game.id desc";

        Query query = session.createQuery(hql);
        query.setParameter("team",idTeam);
        return (Game) query.list().get(0);
    }

    public ArrayList<Date> getDatesOfGames() {
        String hql = " select playerGamelog.date from Game playerGamelog " +
                "order by playerGamelog.date desc";

        Query query = session.createQuery(hql);

        return (ArrayList<Date>)  query.list();

    }

    public int getTotalGoal(int idGame){
        String hql = "select gameLogs from PlayerGameLogs gameLogs " +
                "where gameLogs.game.id = :game";

        Query query = session.createQuery(hql);
        query.setParameter("game", idGame);
        ArrayList<PlayerGameLogs> games = (ArrayList) query.list();
        int score = 0;

        for(int i = 0; i < games.size(); i++){
            Stats stats = games.get(i).getStats();
            score = (int) ( score + Double.valueOf(stats.getGoals().getText()));
        }
        return score;
    }

    public int getScoreRange(int idGame){
        String hql = "select gameLogs from PlayerGameLogs gameLogs " +
                "where gameLogs.game.id = :game";

        Query query = session.createQuery(hql);
        query.setParameter("game", idGame);
        ArrayList<PlayerGameLogs> games = (ArrayList) query.list();
        int score = 0;

        for(int i = 0; i < games.size(); i++){
            Stats stats = games.get(i).getStats();
            score = (int) ( score + Double.valueOf(stats.getGoals().getText()));
        }
        if(score <= 4){
            return 1;
          }else if(score > 4 && score < 9){
            return 2;
        }else return 3;
    }
}
