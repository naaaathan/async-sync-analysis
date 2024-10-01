docker stop $(docker ps -q)
docker system prune -f

rm -rf ./data-async/

./gradlew clean build -DskipTests

docker-compose build async-app --no-cache
docker-compose up -d async-app

echo "end docker stuff"
echo "starting aws"

##wait some seconds

sleep 1

./aws/create-stack.sh

echo "end aws stuff"

cd gatling && ./mvnw gatling:test