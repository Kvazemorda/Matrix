package setData.DAO;


import Services.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import respond.fullgameschedule.GameEntry;

import java.util.Date;
import java.util.List;

public class GameEntryDao {

    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public void saveListGame(List<GameEntry> gameEntries){
        session.beginTransaction();
        for(GameEntry game: gameEntries) {
            session.saveOrUpdate(game.getAwayTeam());
            session.getTransaction().commit();
            session.clear();
            session.beginTransaction();
            session.saveOrUpdate(game.getHomeTeam());
            session.getTransaction().commit();
            session.clear();
            session.beginTransaction();
            session.saveOrUpdate(game);
            System.out.println(game);
        }
        session.getTransaction().commit();
    }

    public List<GameEntry> getListBeforeDate(Date date){
        String hql = "select game from GameEntry game " +
                " where game.date < :date " +
                " order by game.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("date", date);
        return query.list();
    }

    public List<GameEntry> getListGameTodayDate(Date date){
        String hql = "select game from GameEntry game " +
                " where game.date = :date " +
                " order by game.date desc";

        Query query = session.createQuery(hql);
        query.setParameter("date", date);
        return query.list();
    }
}
