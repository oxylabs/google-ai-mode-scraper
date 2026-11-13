using System;
using System.IO;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

class Program
{
    static async Task Main()
    {
        // Replace with your API key.
        const string ApiKey = "YOUR_API_KEY";

        // API parameters.
        var payload = new
        {
            query = "best health trackers under $200",
            run_js = true,
            output = new[] { "json" },
            location = "US"
        };

        // Headless rendering can take a while.
        var client = new HttpClient { Timeout = TimeSpan.FromSeconds(150) };
        var request = new HttpRequestMessage(HttpMethod.Post, "https://webapi.oxylabs.io/v1/scrape/google/aimode");
        request.Headers.Add("Authorization", "Bearer " + ApiKey);
        request.Content = new StringContent(JsonSerializer.Serialize(payload), Encoding.UTF8, "application/json");

        // Get a response.
        var response = await client.SendAsync(request);
        var content = await response.Content.ReadAsStringAsync();

        // Print the response to stdout.
        Console.WriteLine(content);

        // Save the response to a JSON file.
        using var document = JsonDocument.Parse(content);
        File.WriteAllText("response.json", JsonSerializer.Serialize(document.RootElement, new JsonSerializerOptions { WriteIndented = true }));
    }
}
