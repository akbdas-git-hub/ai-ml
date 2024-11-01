import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Require
 *  <dependency>
 *             <groupId>org.json</groupId>
 *             <artifactId>json</artifactId>
 *             <version>20240303</version>
 *  </dependency>
 *
 *  Java 11 and above
 */


public class HankerRankTeamScores {

    public static void testMethod1(int year,String team) throws Exception{
        int team1Goals=0;
        String baseUrl="https://jsonmock.hackerrank.com/api/football_matches?";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest=HttpRequest.newBuilder().uri(new URI(baseUrl+"year="+year+"&team1="+team+"&page=1")).GET().build();

        HttpResponse<String> hr= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JSONObject ja=new JSONObject(hr.body());
        int totalCnt = (int)ja.get("total_pages");
        for (int i=0;i<totalCnt;++i) {
            HttpRequest hr1=HttpRequest.newBuilder().uri(new URI(baseUrl+"year="+year+"&team1="+team+"&page="+i)).GET().build();

            HttpResponse<String> hrCnt= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JSONObject ja1=new JSONObject(hr.body());
            JSONArray jArray = ((JSONArray)ja1.get("data"));
            for(int j=0; j<jArray.length(); ++j) {
                team1Goals += Integer.valueOf(
                        (String) ((JSONObject)jArray.get(j)).get("team1goals")
                    );

            }



        }

        httpRequest=HttpRequest.newBuilder().uri(new URI(baseUrl+"year="+year+"&team2="+team+"&page=1")).GET().build();

         hr= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
         ja=new JSONObject(hr.body());
         totalCnt = (int)ja.get("total_pages");
        for (int i=0;i<totalCnt;++i) {
            HttpRequest hr1=HttpRequest.newBuilder().uri(new URI(baseUrl+"year="+year+"&team2="+team+"&page="+i)).GET().build();

            HttpResponse<String> hrCnt= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JSONObject ja1=new JSONObject(hr.body());
            JSONArray jArray = ((JSONArray)ja1.get("data"));
            for(int j=0; j<jArray.length(); ++j) {
                team1Goals += Integer.valueOf(
                        (String) ((JSONObject)jArray.get(j)).get("team2goals")
                );

            }



        }


    }

    public static void main(String[] args) {
        try {
            testMethod1(2011, "Barcelona");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
