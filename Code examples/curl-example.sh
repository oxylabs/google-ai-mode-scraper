curl 'https://realtime.oxylabs.io/v1/queries' \
--user 'USERNAME:PASSWORD' \
-H 'Content-Type: application/json' \
-d '{
        "source": "google_ai_mode",
        "query": "best health trackers under $200",
        "render": "html",
        "parse": true
    }'
