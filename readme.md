### docker network 생성
```bash
docker network create docker-network
docker network inspect docker-network
```

### docker mysql 이미지 생성후 실행
```bash
docker pull mysql
docker run -d --name mysql-container -p 3306:3306 --network docker-network  -e MYSQL_ROOT_PASSWORD=1234  mysql:latest
docker exec -it mysql-container mysql -u root -p
1234
create database ddang;
exit
```

### spring boot 이미지 생성 후 실행
```bash
docker build -t ddang-auction .
docker run -p 8080:8080 --name ddang-auction-container --network docker-network  -d ddang-auction
```
