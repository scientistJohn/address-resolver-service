mvn install
docker build -t andrii_test .
docker run -p 9001:9001 -e API_KEY=$API_KEY andrii_test