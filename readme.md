### spring boot 이미지 생성 후 실행
```bash
docker build -t ddang-auction .
docker run -p 8080:8080 --name ddang-auction-container -d ddang-auction
```
