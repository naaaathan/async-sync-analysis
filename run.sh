docker stop $(docker ps -q)
docker system prune -f

rm -rf ./data-sync/

./gradlew clean build -DskipTests

docker-compose build sync-app --no-cache
docker-compose up -d sync-app

echo "end docker stuff"
echo "starting aws"

##wait some seconds

sleep 5

./aws/create-stack.sh

echo "end aws stuff"

cd gatling && ./mvnw gatling:test