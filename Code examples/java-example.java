// Requires Java 11 or later. Run with: java java-example.java
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // API parameters.
        String payload = "{"
                + "\"query\": \"best health trackers under $200\","
                + "\"run_js\": true,"
                + "\"output\": [\"json\"],"
                + "\"location\": \"US\""
                + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://webapi.oxylabs.io/v1/scrape/google/aimode"))
                .timeout(Duration.ofSeconds(150)) // Headless rendering can take a while.
                .header("Content-Type", "application/json")
                // Replace with your API key.
                .header("Authorization", "Bearer YOUR_API_KEY")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        // Get a response.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response to stdout.
        System.out.println(response.body());

        // Save the response to a JSON file.
        Files.writeString(Path.of("response.json"), response.body());
    }
}
