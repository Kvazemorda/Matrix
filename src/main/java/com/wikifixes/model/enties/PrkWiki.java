package com.wikifixes.model.enties;

public class PrkWiki {
    private boolean download;
    private String keyError;
    private String downloadHourOfDay;
    private String visitHourOfDay;
    private String productName;
    private String auid;
    private String url;
    private String lang;
    private String os;
    private String browser;
    private String gclid;
    private String beltTime;
    private String marker;
    private String belt;
    private String sessionCount;
    private String size;
    private int purchase;
    double revenue;
    private String currency;
    private String errorsInCart;
    private String kwFromGetofUrl;
    private int weekVisit;

    public PrkWiki() {
    }

    public int getWeekVisit() {
        return weekVisit;
    }

    public void setWeekVisit(int weekVisit) {
        this.weekVisit = weekVisit;
    }

    public String getKwFromGetofUrl() {
        return kwFromGetofUrl;
    }

    public void setKwFromGetofUrl(String kwFromGetofUrl) {
        this.kwFromGetofUrl = kwFromGetofUrl;
    }

    public String getErrorsInCart() {
        return errorsInCart;
    }

    public void setErrorsInCart(String errorsInCart) {
        this.errorsInCart = errorsInCart;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {

        this.revenue = revenue;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(String sessionCount) {
        this.sessionCount = sessionCount;
    }

    public PrkWiki(String auid) {
        this.auid = auid;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getGclid() {
        return gclid;
    }

    public void setGclid(String gclid) {
        this.gclid = gclid;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getKeyError() {
        return keyError;
    }

    public void setKeyError(String keyError) {
        this.keyError = keyError;
    }

    public String getDownloadHourOfDay() {
        return downloadHourOfDay;
    }

    public void setDownloadHourOfDay(String downloadHourOfDay) {
        this.downloadHourOfDay = downloadHourOfDay;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisitHourOfDay() {
        return visitHourOfDay;
    }

    public void setVisitHourOfDay(String visitHourOfDay) {
        this.visitHourOfDay = visitHourOfDay;
    }

    public String getBeltTime() {
        return beltTime;
    }

    public void setBeltTime(String beltTime) {
        this.beltTime = beltTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrkWiki{");
        sb.append(", auid='").append(auid).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", downloadHourOfDay=").append(downloadHourOfDay);
        sb.append("download=").append(download);
        sb.append(", visitHourOfDay=").append(visitHourOfDay);
        sb.append(", keyError='").append(keyError).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
