package com.peya.app.rpg.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtils {

    private static final Gson GSON = new Gson();

    private static final Gson PP_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private JSONUtils() {
    }

    public static String toJson(Object obj) {
        return toJson(obj, false);
    }

    public static String toJson(Object obj, boolean pretty) {
        return (pretty ? PP_GSON : GSON).toJson(obj);
    }
}
