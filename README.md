Checking the thread that each controller request runs in:

curl -v http://localhost:8080/deferredRes

curl -v http://localhost:8080/async

curl -v http://localhost:8080/sync

mvn jetty:run

