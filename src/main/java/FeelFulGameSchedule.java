import Services.HibernateSessionFactory;
import respond.fullgameschedule.RootSchedule;
import setData.DAO.GameEntryDao;
import setData.TeamAndPeriod;
import urlquery.QueryToSportFeed;
import urlquery.UrlQuery;

import java.util.ArrayList;

public class FeelFulGameSchedule {

    public static void main(String[] args) {
        QueryToSportFeed queryToSportFeed = new QueryToSportFeed(new UrlQuery());
        ArrayList<String> period = new TeamAndPeriod().getListOfPeriod();
        for (int i = 0; i < period.size(); i++){
            RootSchedule rootSchedule = queryToSportFeed.getRootGameSchedule(period.get(i)+"-regular");
            System.out.println(rootSchedule.toString());
            GameEntryDao gameEntryDao = new GameEntryDao();
            gameEntryDao.saveListGame(rootSchedule.getFullGameSchedule().getGameentry());
        }
        HibernateSessionFactory.shutdown();
    }
}
