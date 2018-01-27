package setData.DAO;

import Services.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import respond.playersgamelogs.*;

import java.util.ArrayList;

public class PlayerLogsDao {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public PlayerLogsDao() {
    }

    public void save(ArrayList<PlayerGameLogs> playerGameLogs) {
            for (PlayerGameLogs playerLogs : playerGameLogs) {
                if(playerLogs != null) {
                    System.out.printf("игра " + playerLogs.getGame().getId());
                    session.beginTransaction();
                    Game game = playerLogs.getGame();
                    Team homeTeam = game.getHomeTeam();
                    Team awayTeam = game.getAwayTeam();
                    session.saveOrUpdate(homeTeam);
                    session.saveOrUpdate(awayTeam);
                    session.saveOrUpdate(game);
                    Player player = playerLogs.getPlayer();
                    session.saveOrUpdate(player);
                    Stats stats = playerLogs.getStats();
                    session.saveOrUpdate(stats);
                    session.saveOrUpdate(playerLogs);
                    session.getTransaction().commit();
                    session.clear();
                }
                String hql = " select stats.stats from PlayerGameLogs stats " +
                        "where stats = :playerGameLogs";

                Query query = session.createQuery(hql);
                query.setParameter("playerGameLogs", playerLogs);
                Stats stats1 = (Stats) query.list().get(0);
                if(stats1.getLosses() != null || stats1.getGoals() != null) {
                    System.out.println(stats1.getGoals().getText());
                }else {
                    System.out.println(stats1.toString());
                }
                session.clear();
        }
    }
}
