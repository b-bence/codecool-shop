package com.codecool.shop.util;

import com.codecool.shop.model.Product;
import com.google.gson.*;

import java.util.List;

public class Util {

    public static String parseProductObjectListToJSON(List<Product> productList) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        String json = gson.toJson(productList);
        return json;
    }
}
