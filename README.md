# 레이어드 아키텍처 학습

<img src="https://img.shields.io/badge/openjdk_21-000000?style=for-the-badge&logo=openjdk&logoColor=white" alt="openjdk-17">
<img src="https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="spring-boot">
<img src="https://img.shields.io/badge/h2-4479A1?style=for-the-badge&logo=wikidata&logoColor=white" alt="h2">
<img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white" alt="swagger">
<img src="https://img.shields.io/badge/jpa-20336B?style=for-the-badge&logoColor=white" alt="jpa">
<img src="https://img.shields.io/badge/slf4j (with logback)-40AEF0?style=for-the-badge&logoColor=white" alt="slf4j">
<img src="https://img.shields.io/badge/MyBatis-ECD53F?style=for-the-badge&logoColor=white" alt="MyBatis">

---

## 프로젝트 주요 기능과 목적

- 비즈플랫폼개발팀의 **백엔드 프로젝트 아키텍처**를 정의합니다.
- 신규 프로젝트 진행 시, 해당 Template을 이용하여 구축합니다.

### ✨ [지켜야할 기본 규칙](https://dwedi.atlassian.net/wiki/spaces/BPD/pages/615875030/-+Spring) 필독 ! ✨

### ✨TDD을 기반으로 코딩되어야 합니다. ✨

### ✨직접 main 브랜치에 push 할 수 없습니다. ✨

### ✨Pull Request 를 통한 Merge 가 기본이며, 예외는 없습니다. ✨

---

## 프로젝트 구조

![img.png](readme-image/layered-architecture.png)

### 의존관계

- **UI Layer**는 **Service Layer**만 의존해야한다.
- **Service Layer**는 **Repository Layer**만 의존해야한다.
    - 예외) **Application Layer**는 **Infrastructure Layer**에 의존할 수도 있다.

### 계층별 구성

- layeredarchitecture
    - Presentation Layer
        - Controller
    - Application Layer
        - Service
    - Domain
        - Entity
        - Domain
    - Infrastructure Layer
        - Repository
- common
    - config
    - constants
    - dto
    - exception

### 실제 프로젝트 구조

![img.png](readme-image/dongwha-project-structure.png)

---

## 설치 방법

1. JDK 21 설치
2. Git pull
3. 어플리케이션 실행

---

## API 명세서

http://localhost:8080/swagger-ui/index.html

API 문서 다운로드 (yaml) : http://localhost:8080/api-docs.yaml  
API 문서 다운로드 (json) : http://localhost:8080/api-docs

---

## 라이브러리 정보

- spring-boot-starter (latest)
- spring-boot-starter-web (latest)
- spring-boot-starter-data-jpa (latest)
- spring-boot-starter-test (latest)
- spring-boot-starter-validation (latest)
- springdoc-openapi-starter-webmvc-ui (2.6.0)
- mybatis-spring-boot-starter (3.0.3)
- lombok (latest)
- h2 (latest)
- junit-platform-launcher (latest)
- jjwt-api (0.11.5)
- jjwt-impl (0.11.5)
- jjwt-jackson (0.11.5)
- mockito-core (latest)
- junit-jupiter (latest)
- junit-jupiter-engine (latest)
