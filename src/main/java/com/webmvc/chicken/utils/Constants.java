package com.webmvc.chicken.utils;

public class Constants {

    public static String FACEBOOK_APP_ID = "606938923966761";

    public static String FACEBOOK_APP_SECRET = "504540e35de3b3c82d8e3a0fb28a53d5";

    public static String FACEBOOK_REDIRECT_URL = "https://group6project.herokuapp.com/fb-login";

//    public static String FACEBOOK_REDIRECT_URL = "http://localhost:8081/chiken_war_exploded/fb-login";

    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

    public static String GOOGLE_CLIENT_ID = "1020369265111-u30b21k5ghkap6g6bh666r6j3q1qhvni.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "https://group6project.herokuapp.com/gg-login";

//    public static String GOOGLE_REDIRECT_URI = "http://localhost:8081/chiken_war_exploded/gg-login";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    private Constants() {

    }

}
