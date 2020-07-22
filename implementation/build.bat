cd %CD%
docker-compose -f "docker-compose.yml" down

docker-compose -f "docker-compose.yml" up -d --build mysqldb

docker-compose -f "docker-compose.yml" up -d --build adminer

docker-compose -f "docker-compose.yml" up -d --build mysql-workbench

