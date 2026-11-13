<?php

// API parameters.
$payload = array(
    'query' => 'best health trackers under $200',
    'run_js' => true,
    'output' => array('json'),
    'location' => 'US'
);

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://webapi.oxylabs.io/v1/scrape/google/aimode');
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($payload));
curl_setopt($ch, CURLOPT_HTTPHEADER, array(
    'Content-Type: application/json',
    // Replace with your API key.
    'Authorization: Bearer YOUR_API_KEY'
));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_TIMEOUT, 150); // Headless rendering can take a while.

// Get a response.
$result = curl_exec($ch);
if ($result === false) {
    echo curl_error($ch) . PHP_EOL;
    exit(1);
}
curl_close($ch);

// Print the response to stdout.
echo $result . PHP_EOL;

// Save the response to a JSON file.
file_put_contents('response.json', json_encode(json_decode($result), JSON_PRETTY_PRINT));
