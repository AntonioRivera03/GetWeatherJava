import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class call {

    //fin contains the parsed data as a hashmap
    private Map<String, String> fin = datasplit();

    //Temp grabs the temperature from the hashmap
    private String temp = fin.get("\"temp_f\"");
    private String local;

    private Map<String, String> datasplit(){
        //datasplit references two methods -> request & conv
        //prompts user for a location
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a location");
        String inp = in.nextLine();

        local = inp;
        //Not sure if its good practice but might be fun to nest request in conv like conv(request(inp))
        String body = request(inp);
        Map<String, String> parsed = conv(body);

        return parsed;
    }

    private String request(String location){

        //request makes an api call to weatherapi
        String apiKey = "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location +"&aqi=yes"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    private Map<String, String> conv(String body){
        //Goes through the response body string and breaks it down and puts into the hashmap.
        //Kinda bad though and needs to be fixed. Location just vanishes because of the way i parse through the json string.
        Map<String, String> dic = new HashMap<String, String>();
        String[] pairs = body.split(",");

        for(int i=0;i<pairs.length;i++){
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            dic.put(keyValue[0], keyValue[1]);
        }
        return dic;
    }

//getter methods yay
    public String getTemp(){
        return temp;
    }
    public Map<String, String> getRequest(){
        return fin;
    }

    public String getLocal(){
        return local;
    }


}
