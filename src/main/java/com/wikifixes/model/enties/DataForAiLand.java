package com.wikifixes.model.enties;


import java.util.Date;

public class DataForAiLand {
    private String auid;
    private String product;
    private String keyOfAd;
    private double cost;
    private int sessionCount;
    private Date dateStart;
    private Date dateBuy;
    private String local;
    private String os;
    private String browser;

    public DataForAiLand() {
    }

    public DataForAiLand(String auid, String product, String keyOfAd, double cost, int sessionCount, Date dateStart, Date dateBuy, String local, String os, String browser) {
        this.auid = auid;
        this.product = product;
        this.keyOfAd = keyOfAd;
        this.cost = cost;
        this.sessionCount = sessionCount;
        this.dateStart = dateStart;
        this.dateBuy = dateBuy;
        this.local = local;
        this.os = os;
        this.browser = browser;
    }

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getKeyOfAd() {
        return keyOfAd;
    }

    public void setKeyOfAd(String keyOfAd) {
        this.keyOfAd = keyOfAd;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}