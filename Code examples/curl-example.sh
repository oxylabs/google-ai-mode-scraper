curl 'https://webapi.oxylabs.io/v1/scrape/google/aimode' \
--max-time 150 \
-H 'Authorization: Bearer YOUR_API_KEY' \
-H 'Content-Type: application/json' \
-d '{
        "query": "best health trackers under $200",
        "run_js": true,
        "output": ["json"],
        "location": "US"
    }'
