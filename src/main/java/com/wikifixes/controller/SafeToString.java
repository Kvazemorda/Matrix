package com.wikifixes.controller;

import java.util.TreeMap;

public class SafeToString {
    public static TreeMap<String, Integer> os, browser, errors, hours, belt, timeBelt, lang, sessions, size, kw;

    public SafeToString() {

        os = new TreeMap<>();
        browser = new TreeMap<>();
        errors = new TreeMap<>();
        hours = new TreeMap<>();
        belt = new TreeMap<>();
        timeBelt = new TreeMap<>();
        lang = new TreeMap<>();
        sessions = new TreeMap<>();
        size = new TreeMap<>();
        kw = new TreeMap<>();

    }

    public String safeOwnData(int keyParams){
        return keyParams + ",";
    }

    public String safeToString(String keyOfPrk, String keyParams) {
        if(keyOfPrk == null){
            keyOfPrk = "null";
        }
        TreeMap<String, Integer> list = null;
        if(keyParams.equals("os")){
            list = os;
        }
        if(keyParams.equals("browser")){
            list = browser;
        }
        if(keyParams.equals("errors")){
            list = errors;
        }
        if(keyParams.equals("hours")){
            list = hours;
        }
        if(keyParams.equals("belt")){
            list = belt;
        }
        if(keyParams.equals("timeBelt")){
            list = timeBelt;
        }
        if(keyParams.equals("lang")){
            list = lang;
        }
        if(keyParams.equals("sessions")){
            list = sessions;
        }
        if(keyParams.equals("size")){
            list = size;
        }
        if(keyParams.equals("kw")){
            list = kw;
        }

        StringBuilder string = new StringBuilder();
            if(findInMap(keyOfPrk, list)){
                string.append(list.get(keyOfPrk));
            }else{
                addFileToErrorsMap(keyOfPrk, list);
                string.append(list.get(keyOfPrk));
            }
            if(keyParams.equals("browser")){
                string.append("");
            }else {
                string.append(",");
            }
        return string.toString();
    }

    private static void addFileToErrorsMap(String keyError, TreeMap<String, Integer> treeMap) {
        treeMap.put(keyError, treeMap.size() + 1);

    }

    public static boolean findInMap(String keyError, TreeMap<String, Integer> treeMap){
        if(keyError == null){
            keyError = "";
        }
        if(keyError.contains("Android"))
            keyError = "Android";

        if(treeMap.containsKey(keyError)){
            return true;
        }else {
            return false;
        }
    }
}
