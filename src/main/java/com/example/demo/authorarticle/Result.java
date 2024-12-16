package com.example.demo.authorarticle;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.json.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Result {

    public static void main(String[] args) {
        topArticles("epaga", 3).forEach(System.out::println);
    }

    public static List<String> topArticles(String username, int limit) {
        List<Article> articles = new ArrayList<>();

        int page = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            String apiUrl = String.format("https://jsonmock.hackerrank.com/api/articles?author=%s&page=%d", username, page);
            String response = getApiResponse(apiUrl);

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

            JsonArray data = jsonObject.getAsJsonArray("data");
            long totalPages = jsonObject.get("total_pages").getAsLong();
            for(JsonElement element: data) {
                JsonObject article = element.getAsJsonObject();
                String title = article.has("title") && !article.get("title").isJsonNull() ? article.get("title").getAsString() : null;
                String storyTitle = article.has("story_title") && !article.get("story_title").isJsonNull() ? article.get("story_title").getAsString() : null;
                int numComments = article.has("num_comments") && !article.get("num_comments").isJsonNull() ? article.get("num_comments").getAsInt() : 0;
                articles.add(new Article(title != null ? title : storyTitle, numComments));
            }

            if (page < totalPages) {
                page++;
            } else {
                hasNextPage = false;
            }
        }

        articles.sort(Comparator.comparingInt(Article::getNumComments).reversed());


        return articles.stream().limit(limit).map(Article::getName).toList();
    }

    private static String getApiResponse(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();


    }
}
