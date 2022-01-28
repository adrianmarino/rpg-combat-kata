package com.game.rpg.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {

    private static final Gson GSON = new Gson();

    private static final Gson PP_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private JSONUtil() {
    }

    public static String toJson(Object obj) {
        return toJson(obj, false);
    }

    public static String toJson(Object obj, boolean pretty) {
        return (pretty ? PP_GSON : GSON).toJson(obj);
    }
}
