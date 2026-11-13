package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"os"
	"time"
)

func main() {
	// API parameters.
	payload := map[string]interface{}{
		"query":    "best health trackers under $200",
		"run_js":   true,
		"output":   []string{"json"},
		"location": "US",
	}
	jsonValue, _ := json.Marshal(payload)

	// Headless rendering can take a while.
	client := &http.Client{Timeout: 150 * time.Second}
	request, _ := http.NewRequest("POST", "https://webapi.oxylabs.io/v1/scrape/google/aimode", bytes.NewBuffer(jsonValue))
	request.Header.Set("Content-Type", "application/json")
	// Replace with your API key.
	request.Header.Set("Authorization", "Bearer YOUR_API_KEY")

	// Get a response.
	response, err := client.Do(request)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	defer response.Body.Close()
	responseText, _ := io.ReadAll(response.Body)

	// Print the response to stdout.
	fmt.Println(string(responseText))

	// Save the response to a JSON file.
	var pretty bytes.Buffer
	if err := json.Indent(&pretty, responseText, "", "  "); err == nil {
		os.WriteFile("response.json", pretty.Bytes(), 0644)
	}
}
