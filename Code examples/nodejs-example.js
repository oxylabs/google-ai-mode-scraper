// Requires Node.js 18 or later.
const fs = require('fs');

// API parameters.
const payload = {
  query: 'best health trackers under $200',
  run_js: true,
  output: ['json'],
  location: 'US',
};

async function main() {
  // Get a response.
  const response = await fetch('https://webapi.oxylabs.io/v1/scrape/google/aimode', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      // Replace with your API key.
      Authorization: 'Bearer YOUR_API_KEY',
    },
    body: JSON.stringify(payload),
    signal: AbortSignal.timeout(150000), // Headless rendering can take a while.
  });
  const data = await response.json();

  // Print the response to stdout.
  console.dir(data, { depth: null });

  // Save the response to a JSON file.
  fs.writeFileSync('response.json', JSON.stringify(data, null, 2));
}

main();
