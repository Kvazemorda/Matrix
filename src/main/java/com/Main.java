package com;

import com.wikifixes.controller.SafeToString;
import com.wikifixes.model.enties.PrkWiki;
import org.json.JSONObject;
import test.MLPClassifierLeaner;

import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static StringBuilder toFile = new StringBuilder();
    static String timeBelt = null, testData;
    static String belt = null, lang = null;
    static SafeToString safeToString = new SafeToString();
    static List<PrkWiki> listOfPrk;
    static int countRows;
    static int numImputs;




    public static void main(String[] args) {
        countRows = 0;
        listOfPrk = new ArrayList<>();
        File file = null;
        String line = null;
        testData = "C:\\Users\\iStore\\IdeaProjects\\Matrix\\test.csv";
        try {
            ArrayList<String> days = new ArrayList<>();
         /*   days.add("2018-07-03.txt");
            days.add("2018-07-04.txt");
            days.add("2018-07-05.txt");
            days.add("2018-07-06.txt");
            days.add("2018-07-07.txt");
            days.add("2018-07-08.txt");
            days.add("2018-07-09.txt");
            days.add("2018-07-10.txt");
            days.add("2018-07-11.txt");
            days.add("2018-07-12.txt");
            days.add("2018-07-13.txt");
            days.add("2018-07-14.txt");
          days.add("2018-07-15.txt");
         */days.add("2018-07-17.txt");
            days.add("2018-07-18.txt");
            days.add("2018-07-19.txt");
            days.add("2018-07-20.txt");
            days.add("2018-07-21.txt");
            days.add("2018-07-24.txt");
            days.add("2018-07-25.txt");
            days.add("2018-07-26.txt");
            days.add("2018-07-27.txt");
            days.add("2018-07-28.txt");
         //   days.add("2018-07-29.txt");
            days.add("2018-07-30.txt");
            days.add("2018-07-31.txt");
            days.add("2018-08-01.txt");
            days.add("2018-08-02.txt");
       //     days.add("2018-08-03.txt");


            for(int i = 0; i < days.size(); i++) {
                file = new File("C:\\Users\\iStore\\Downloads\\files\\"+ days.get(i));
                Date date = getDateFromFileName(days.get(i));
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuffer stringBuffer = new StringBuffer();
                System.out.println(days.get(i));
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                    line = line.replaceAll("\\+", "%2B");
                    line = URLDecoder.decode(line, "UTF-8");
                    String[] splitLine = line.split("\t");
                    //сохраняю ауид без сессии

                    JSONObject obj = new JSONObject(splitLine[1]);
                    // находим сессию пользователя
                    String session = obj.getJSONObject("session").toString();
                    JSONObject sessionObj = new JSONObject(session);
                    //перебираем сессии юзера
                    checkEachSession(sessionObj, splitLine[0], date);
                }
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter pwTestData = new PrintWriter(
                    new File(testData));
            int before5 = 0;
            int more5Before10 = 0;
            int more10Before30 = 0;
            int more30Before60 = 0;
            int more60 = 0;
            int total = 0;
            int perchase5 = 0;
            int perchase510 = 0;
            int perchase1030 = 0;
            int perchase3060 = 0;
            int perchase60 = 0;

            int purchaseNotDownload = 0;
            for (PrkWiki prk: listOfPrk){
                safeToFile(prk);
                countRows++;

                if(prk.getDownloadHourOfDay() != null && !prk.getDownloadHourOfDay().equals("0")){
                    double downloaded = Double.parseDouble(prk.getDownloadHourOfDay());
                    double visited = Double.parseDouble(prk.getVisitHourOfDay());
                    double different = downloaded - visited;
                    if(different >= 0){
                        total++;
                    }

                    if(prk.getCurrency() != null && !prk.getCurrency().equals("USD")){
                //        System.out.println(prk.getCurrency() + " " + prk.getRevenue());
                        changeCurrancyToUSD(prk);
                 //       System.out.println(prk.getCurrency() + " после перевода в доллары " + prk.getRevenue());
                    }

                  if(different >= 0 && different <= 5){
                    before5++;
                      perchase5 += prk.getRevenue();
                  }else if(different > 5 && different <= 10){
                      more5Before10++;
                      perchase510 += prk.getRevenue();
                  }else if(different > 10 && different <= 30){
                      more10Before30++;
                      perchase1030 += prk.getRevenue();
                  }else if(different > 30 && different <=120){
                      more30Before60++;
                      perchase3060 += prk.getRevenue();
                  }else if(different > 120){
                      more60++;
                      perchase60 += prk.getRevenue();
                  }
                }else {
                    purchaseNotDownload += prk.getRevenue();
                }
            }
            System.out.println("Меньше 5 секунд " + before5 + " продажи " + perchase5 + " $");
            System.out.println("больше 5 меньше 10 " + more5Before10 + " продажи " + perchase510 + " $");
            System.out.println("больше 10 меньше 30 " + more10Before30 + " продажи " + perchase1030 + " $");
            System.out.println("больше 30 меньше 120 " + more30Before60  + " продажи " + perchase3060 + " $");
            System.out.println("больше 120 " + more60  + " продажи " + perchase60 + " $");
            System.out.println("всего скачиваний " + total);
            System.out.println("покупок без скачиваний " + purchaseNotDownload);


            pwTestData.write(toFile.toString());
            pwTestData.close();

            numImputs = getRowsFromDataSet(toFile) - 1;
            MLPClassifierLeaner mlp = new MLPClassifierLeaner();
            mlp.main(countRows, numImputs, 2, testData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Date getDateFromFileName(String s) {
        String split = s.substring(0, s.length()-4);
        SimpleDateFormat formate = new SimpleDateFormat("YYYY-dd-MM");
        Date date = null;
        try {
            date = formate.parse(split);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private static void changeCurrancyToUSD(PrkWiki prk) {
        if(prk.getCurrency().equals("EUR")){
           prk.setRevenue(prk.getRevenue() * 1.17);
        }else if(prk.getCurrency().equals("AUD")){
            prk.setRevenue(prk.getRevenue() * 0.74);
        }else if(prk.getCurrency().equals("GBP")){
            prk.setRevenue(prk.getRevenue()* 1.31);
        }else if(prk.getCurrency().equals("CAD")){
            prk.setRevenue(prk.getRevenue() * 0.76);
        }
    }

    private static int getRowsFromDataSet(StringBuilder toFile) {
        Scanner scanner = new Scanner(toFile.toString());
        String line = "";
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            break;
        }
        String[] splitLine = line.split(",");
        return splitLine.length;
    }

    public static void checkEachSession(JSONObject jsonObj, String auid, Date date) {
            for (Object key : jsonObj.keySet()) {
                //based on you key types
                String keyStr = (String) key;
                Object keyValue = jsonObj.get(keyStr);

                if (keyValue instanceof JSONObject) {
                    PrkWiki prk = new PrkWiki(auid + "." + keyStr);
                    // сохраняю кол-во сессий
                    prk.setSessionCount(String.valueOf((((String) key).length()-1)));
                    parsingOfSession((JSONObject) keyValue, prk);

                    if(prk.getVisitHourOfDay() != null
                            && prk.getProductName() != null
                            && prk.getProductName().equals("du")
                        ){

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int week = cal.get(Calendar.WEEK_OF_YEAR);
                        prk.setWeekVisit(week);
                        listOfPrk.add(prk);
                    }
                }
            }
    }

    private static void parsingOfSession(JSONObject jsonObject, PrkWiki prk) {
        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Object keyValue = jsonObject.get(keyStr);

            if (keyStr.equals("url")) {
                String value = keyValue.toString();
          //      if (value.contains("/driver-updater/")) {
                    //сохраняю имя продукта
                    getProduct(value, prk);
                    // сохраняю имя параметра из рекламы
                    prk.setKeyError(getParamFromWiki(value));
                    prk.setLang(getLangFromUrl(value));
           //     }
            }
            if(keyStr.equals("downloadTime")){
                prk.setDownload(true);
                prk.setDownloadHourOfDay(getTime(keyValue.toString()));
            }
            if(keyStr.equals("time")){
                prk.setVisitHourOfDay(getTime(keyValue.toString()));
                prk.setBeltTime(timeBelt);
                prk.setBelt(belt);
            }
            if(keyStr.equals("os")){
                if(!keyValue.toString().contains("Windows")){
                    prk.setOs("other OS");
                }else {
                    prk.setOs(keyValue.toString());
                }
            }

            if(keyStr.equals("browser")){
                if(keyValue.toString().contains("Mozilla")){
                    prk.setBrowser("Mozilla");
                }else if(isFamousBrowsers(keyValue.toString())){
                    prk.setBrowser(keyValue.toString());
                }else {
                    prk.setBrowser("Other Brothers");
                }
            }
            if(keyStr.equals("screenSize")){
                prk.setSize(keyValue.toString());
            }
            if(keyStr.equals("cartError")){
                prk.setErrorsInCart(keyValue.toString());
            }
            if(jsonObject.has("purchase")){
                prk.setPurchase(1);
            }else{
                prk.setPurchase(0);
            }

            if(keyStr.equals("get")){
                if(keyValue.toString().contains("kw=")){
                    prk.setKwFromGetofUrl(getKwFromHttp(keyValue.toString()));
                }
            }

            if(keyStr.equals("purchase")){
          //      parsingOfPurchase((JSONObject) keyValue, prk);
            }
           /* if(keyStr.equals("cartError")){
                if(jsonObject.has("purchase")){
                    System.out.println(keyValue + "; prechase");
                }else{
                    System.out.println(keyValue);
                }
            }*/
        }
    }

    private static String getKwFromHttp(String s) {
        String kw = s;
        kw = kw.substring(kw.indexOf("kw=")+3, kw.length());
        if(kw.contains("&")) {
            kw = kw.substring(0, kw.indexOf("&"));
        }
        return kw;
    }

    private static boolean isFamousBrowsers(String s) {
        boolean isFamousBrowser = false;
        String[] listBrowser = new String[6];
        listBrowser[0] = "Opera";
        listBrowser[1] = "Chrome";
        listBrowser[2] = "Internet Explorer";
        listBrowser[3] = "Firefox";
        listBrowser[4] = "Microsoft Edge";
        listBrowser[5] = "Safari";


        for(int i = 0; i < listBrowser.length; i++){
            if(s.equals(listBrowser[i])){
                isFamousBrowser = true;
                break;
            }
        }
        return isFamousBrowser;
    }

    private static void parsingOfPurchase(JSONObject jsonObject, PrkWiki prk) {
        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Object keyValue = jsonObject.get(keyStr);
            if(keyStr.equals("currency")){
                prk.setCurrency(keyValue.toString());
            }
            if(keyStr.equals("revenue")){
                prk.setRevenue(Double.parseDouble(keyValue.toString()));
            }

        }

    }

    private static String getLangFromUrl(String value) {
        lang = null;
        String[] prepairLang = value.split("/");
        if(prepairLang.length >= 2){
            lang = prepairLang[1];
        }
        return lang;
    }


    public static String getTime(String downloadTime){
      //  "Fri Jul 06 2018 15:39:48 GMT+1200"
        timeBelt = null;
        belt = null;
        String timeEndPart = downloadTime.substring(downloadTime.indexOf(":")-2);
        String onlyTime = timeEndPart.substring(0,timeEndPart.indexOf(" "));
        String prepareBelt = timeEndPart.substring(timeEndPart.indexOf(" "));
        prepareBelt = prepareBelt.trim();
        String[] splitBelt = null;
        if(prepareBelt.contains("-")){
            splitBelt = prepareBelt.split("-");
            if(splitBelt.length >= 2){
                splitBelt[1] = "-" + splitBelt[1];
            }
        }else if(prepareBelt.contains("+")){
            splitBelt = prepareBelt.split("\\+");
            if(splitBelt.length >= 2){
                splitBelt[1] = "+" + splitBelt[1];
            }
        }else{
            splitBelt = prepareBelt.split(" ");
            if(splitBelt.length >= 2){
                splitBelt[1] = "+" + splitBelt[1];
            }
        }
        if(splitBelt != null && splitBelt.length >= 2){
            timeBelt = splitBelt[1];
            belt = splitBelt[0];
        }

        String[] timeParse = onlyTime.split(":");
        int hourToSecond = Integer.parseInt(timeParse[0]) * 3600;
        int minToSecond = Integer.parseInt(timeParse[1]) * 60;
        int second = Integer.parseInt(timeParse[2]);
        String time = String.valueOf ((double) (hourToSecond + minToSecond + second));

        return time;
    }
    // сохраняю имя продукта
    public static void getProduct(String value, PrkWiki prkWiki){
        String driverUpdater = "driver-updater";
        String driverUpdaterDownload = "tweakbit.com/driver-updater/download";
        String prk = "tweakbit.com/en/land/pc-repair/";
        String pcsup = "pc-speed-up";
        String am = "anti-malware";
        String wikiErLand = "errors/dll/";
        String wikiNoErLand = "errors/0x/";
        String pcr = "outbyte.com/en/land/pc-repair/";
        String wikiErrorsExe = "/errors/exe/";
        String wikiErrorsBDll = "errors/b/dll/";
        String wikiErrorsB0x = "errors/b/0x";

        if(value.contains(driverUpdater)){
            prkWiki.setProductName("du");
        }
        if(value.contains(driverUpdaterDownload)){
            prkWiki.setProductName("duDownload");
        }
        if(value.contains(prk)){
            prkWiki.setProductName("prk");
        }
        if(value.contains(pcsup)){
            prkWiki.setProductName("pcsup");
        }
        if(value.contains(am)){
            prkWiki.setProductName("am");
        }
        if(value.contains(wikiErLand)) {
            prkWiki.setProductName("prk-wiki");
        }
        if(value.contains(pcr)){
            prkWiki.setProductName("pcr");
        }
        if(value.contains(wikiNoErLand) || value.contains(wikiErrorsExe)
                || value.contains(wikiErrorsBDll) || value.contains(wikiErrorsB0x))
            prkWiki.setProductName("prk-wiki");
    }

    public static String getParamFromWiki(String url){
        String param = "";
        String splitX = "/0x/";
        String splitDll = "/dll/";
        String splitExe = "/exe/";
            if(url.contains(splitX) && url.split(splitX).length >= 2){
                param = url.split(splitX)[1];
            }else if(url.contains(splitDll) && url.split(splitDll).length >=2){
                param = url.split(splitDll)[1];
        }else if(url.contains(splitExe)){
                param = url.split(splitExe)[1];
            }
        if(param.length()> 0){
            if(param.substring(param.length()-1).equals("/")){
                param = param.substring(0,param.length()-1);
            }
        }
        param = param.trim();
        return param;
    }



    public static void safeToFile(PrkWiki prk){
        if (prk.isDownload() && prk.getOs().contains("Windows")) {
            toFile.append(1);
        } else toFile.append(0);
        toFile.append(",");

        //сохраняем os
        toFile.append(safeToString.safeToString(prk.getOs(), "os"));

        //сохраняем ошибки
        //System.out.println(prk.getKeyError());
     /*   toFile.append(safeToString.safeToString(prk.getKeyError(), "errors"));
        //сохраняем пояс часовой*/
        toFile.append(safeToString.safeToString(prk.getBelt(), "belt"));
        toFile.append(safeToString.safeToString(prk.getBeltTime(), "timeBelt"));
        toFile.append(safeToString.safeToString(prk.getLang(), "lang"));
        toFile.append(safeToString.safeToString(prk.getSessionCount(), "sessions"));
      //  toFile.append(safeToString.safeToString(prk.getKwFromGetofUrl(), "kw"));
        toFile.append(safeToString.safeToString(prk.getSize(), "size"));
        //сохраняем дни недели
     //   toFile.append(safeToString.safeOwnData(prk.getWeekVisit()));
        //сохраняем время визита
        toFile.append(safeToString.safeToString(prk.getVisitHourOfDay(), "hours"));
        //сохраняем браузеры
        toFile.append(safeToString.safeToString(prk.getBrowser(), "browser"));
        toFile.append("\n");
    }
}
