import json
import requests


# API parameters.
payload = {
    'query': 'best health trackers under $200',
    'run_js': True,
    'output': ['json'],
    'location': 'US',
}

# Get a response.
response = requests.post(
    'https://webapi.oxylabs.io/v1/scrape/google/aimode',
    # Replace with your API key.
    headers={'Authorization': 'Bearer YOUR_API_KEY'},
    json=payload,
    timeout=150,  # Headless rendering can take a while.
)

# Print the response to stdout.
print(response.json())

# Save the response to a JSON file.
with open('response.json', 'w') as file:
    json.dump(response.json(), file, indent=2)
