# MZD 2차 면접 과제

### 과제 환경

- JAVA 21
- SpringBoot 3.2.3
- Mysql
- JPA



### ERD

![image](https://github.com/Kyoungwookkim/membership-management_backend/assets/104562141/5fa299bf-b2db-4a79-a2df-870751347cba)



### API

|    구분     |    내용     | Method |                                 URI                                 |
|:---------:|:---------:|:------:|:-------------------------------------------------------------------:|
|    회원     | 회원 생성 API |  POST  |                      /api/account                      |
|    회원     | 회원 삭제 API | DELETE |                      /api/account                      |
|  회원 프로필   |   회원 프로필 생성 API    |  POST  |        /api/user/profile                 |
|  회원 프로필   |   회원 프로필 수정 API    | PUT  |          /api/user/profile                 |
|  회원 프로필   |    회원 프로필 삭제 API    | DELETE |       /api/user/profile                 |
|    조회     |   회원 전체 조회 API    |  GET   | /api/search/user/list?keyword={keyword}&size={size}&page={page}  |
|    조회     |    회원 상세 조회 API    |  GET   |           /user/list/detail?userNum={userNum}             |
