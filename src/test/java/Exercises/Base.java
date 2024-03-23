package Exercises;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.*;

import javax.swing.*;

public class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ActionBot bot;
    protected String baseUrl;

    @BeforeEach
    public void setup()
    {

        ChromeOptions chromeOptions = new ChromeOptions();
       // chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);
        bot = new ActionBot(driver, baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void teardown()
    {
        driver.quit();
    }

    protected String createPoll() {
        HttpClient client = HttpClient.newHttpClient();
        String urlId = null;
        String body = """
                {
                  "0": {
                    "json": {
                      "title": "Monthly coffee!",
                      "location": "Kitchen",
                      "description": "",
                      "user": {
                        "name": "Emilia",
                        "email": "test@test.com"
                      },
                      "timeZone": "Europe/Madrid",
                      "options": [
                        {
                          "startDate": "2023-07-11T13:00:00",
                          "endDate": "2023-07-11T13:30:00"
                        }
                      ]
                    }
                  }
                }
                """;
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .uri(URI.create("http://localhost:3000" + "/api/trpc/polls.create?batch=1"))
                    .header("accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArray = new JSONArray(response.body());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            urlId = jsonObject.getJSONObject("result")
                    .getJSONObject("data")
                    .getJSONObject("json")
                    .getString("urlId");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return urlId;
    }
    protected void deletePoll(String urlId) {
        HttpClient client = HttpClient.newHttpClient();
        String body = "{\n" +
                "  \"0\": {\n" +
                "    \"json\": {\n" +
                "      \"urlId\": \"" + urlId + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .uri(URI.create("http://localhost:3000" + "/api/trpc/polls.delete?batch=1"))
                    .header("accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
