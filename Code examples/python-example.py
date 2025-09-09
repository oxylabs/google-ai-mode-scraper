import json
import requests


# API parameters.
payload = {
    'source': 'google_ai_mode',
    'query': 'best health trackers under $200',
    'render': 'html',
    'parse': True,
    'geo_location': 'United States'
}

# Get a response.
response = requests.post(
    'https://realtime.oxylabs.io/v1/queries',
    # Replace with your credentials.
    auth=('USERNAME', 'PASSWORD'),
    json=payload,
)

# Print the response to stdout.
print(response.json())

# Save the response to a JSON file.
with open('response.json', 'w') as file:
    json.dump(response.json(), file, indent=2)
