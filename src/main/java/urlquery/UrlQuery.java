package urlquery;


public class UrlQuery {
    public String baseDomain = "https://api.mysportsfeeds.com/";
    public String versionApi = "v1.1/";
    public String pull = "pull/";
    public String nameSport = "nhl/";

    public UrlQuery() {
    }

    public String getQuery(){
        return baseDomain + versionApi + pull + nameSport;
    }

    public String filterPerioud(String period){
        return getQuery() + period + "/";
    }
}
