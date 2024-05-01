
# ⚾️ 기술 과제

## 기술 스택
- Java 17
- Spring Boot 3.1.11
- Spring Data JPA
- MapStruct
- H2 Database


## 구현 파일 구조
```yaml

src
  ├── SolutionApplication.java
  ├── question1 # 1번 문제 솔루션
  │   ├── category
  │   │   ├── controller
  │   │   │   └── CategoryController.java
  │   │   ├── dto
  │   │   │   ├── CategoriesResponseDto.java
  │   │   │   ├── CategoryDto.java
  │   │   │   └── CategoryResponseDto.java
  │   │   ├── entity
  │   │   │   └── Category.java
  │   │   ├── mapper
  │   │   │   └── CategoryMapper.java
  │   │   ├── repository
  │   │   │   └── CategoryRepository.java
  │   │   └── service
  │   │       └── CategoryService.java
  │   └── exception
  │       └── ExceptionMessage.java
  └── question2 # 2번 문제 솔루션
      ├── CoinGame.java
      └── exception
          └── ExceptionMessage.java

resources
    ├── application.yml
    └── data.sql #초기 데이터

test #테스트 코드
  ├── question1
  │   └── category
  │       └── service
  │           └── CategoryServiceTest.java #1번 문제 테스트
  └── question2 
	  └── CoinGameTest.java #2번 문제 테스트




```


## 주요 기능
### Question 1
- 카테고리 검색 
  - 해당 카테고리를 포함한 하위 카테고리를 모두 JSON 형식으로 응답합니다.
  - 카테고리명을 입력하여 해당 카테고리를 검색할 수 있습니다.
    - 카테고리명이 존재하지 않는 경우 `IllegalArgumentException`
  - 카테고리 식별자(ID)를 입력하여 해당 카테고리를 검색할 수 있습니다.
    - 식별자가 존재하지 않는 경우 `IllegalArgumentException`
    - 식별자가 숫자가 아닌 경우 `IllegalArgumentException`
- 카테고리 생성
  - 사용자는 새로운 카테고리를 생성할 수 있습니다.
  - 익명게시판의 경우, 모두 동일한 child_id를 가집니다.

### Question 2
- 주어진 동전 배열과 합계를 사용하여 동전의 합계를 만드는 방법의 수를 찾습니다.
- 입력값의 유효성을 검증합니다.
  - 동전 배열에 음수나 0이 포함되어 있는 경우 `IllegalArgumentException`
    -  음수와 0이 가능한 경우, 경우의 수가 무한대로 늘어나기 때문
  - 합계가 1보다 작은 경우 `IllegalArgumentException`
  - 동전의 최소값보다 합계가 작은 경우 `IllegalArgumentException`
- 동적 프로그래밍 알고리즘을 사용하여 조합을 만드는 방법 수를 계산합니다.



## 응답 검증

- 카테고리명 조회
```
GET /categories/남자 HTTP/1.1
Host: localhost:8080
```

```json
{
    "category": [
        {
            "id": 1,
            "name": "남자",
            "childId": 1,
            "parentIdx": 0,
            "children": [
                {
                    "id": 3,
                    "name": "엑소",
                    "childId": 3,
                    "parentIdx": 1,
                    "children": [
                        {
                            "id": 6,
                            "name": "공지사항",
                            "childId": 6,
                            "parentIdx": 3,
                            "children": []
                        },
                        {
                            "id": 7,
                            "name": "첸",
                            "childId": 7,
                            "parentIdx": 3,
                            "children": []
                        },
                        {
                            "id": 8,
                            "name": "백현",
                            "childId": 8,
                            "parentIdx": 3,
                            "children": []
                        },
                        {
                            "id": 9,
                            "name": "시우민",
                            "childId": 9,
                            "parentIdx": 3,
                            "children": []
                        }
                    ]
                },
                {
                    "id": 4,
                    "name": "방탄소년단",
                    "childId": 4,
                    "parentIdx": 1,
                    "children": [
                        {
                            "id": 10,
                            "name": "공지사항",
                            "childId": 10,
                            "parentIdx": 4,
                            "children": []
                        },
                        {
                            "id": 11,
                            "name": "익명게시판",
                            "childId": 11,
                            "parentIdx": 4,
                            "children": []
                        },
                        {
                            "id": 12,
                            "name": "뷔",
                            "childId": 12,
                            "parentIdx": 4,
                            "children": []
                        }
                    ]
                }
            ]
        }
    ]
}

```

- 카테고리 식별자 조회
```
GET /categories?id=3 HTTP/1.1
Host: localhost:8080
```

```json

{
    "id": 3,
    "name": "엑소",
    "childId": 3,
    "parentIdx": 1,
    "children": [
        {
            "id": 6,
            "name": "공지사항",
            "childId": 6,
            "parentIdx": 3,
            "children": []
        },
        {
            "id": 7,
            "name": "첸",
            "childId": 7,
            "parentIdx": 3,
            "children": []
        },
        {
            "id": 8,
            "name": "백현",
            "childId": 8,
            "parentIdx": 3,
            "children": []
        },
        {
            "id": 9,
            "name": "시우민",
            "childId": 9,
            "parentIdx": 3,
            "children": []
        }
    ]
}
```



- 카테고리 생성
```
POST /categories/save HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 44

{
    "name": "레드벨벳",
    "parentIdx": "2"
}
```


## H2 DB - 초기 데이터
주어진 카테고리 구분 예시를 초기 데이터로 사용했습니다.



