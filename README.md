# 🖼️ AboutMe [2024_SEORANGJE_ABOUTME_BE]

**"[익명의 친구가 기록하는 나의 모습, 참여형 사진 아카이빙 플랫폼](https://aboutme.d1pt3wqvnh1bfv.amplifyapp.com)"**

<br>

<img width="420" height="420" alt="image" src="https://github.com/user-attachments/assets/05ef0865-065e-4d9f-8064-078ce1277ee8" />


## 📍 서비스 개요
유저가 공유한 링크를 통해 친구들이 직접 유저의 이미지를 큐레이션하고 짧은 메시지를 남기는 참여형 아카이빙 플랫폼입니다. ‘내가 보는 나’를 넘어 ‘타인이 바라보는 나’의 데이터를 수집하고 시각화하는 경험을 제공합니다.

---

## 🛠️ Tech Stack

| Category | Content |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 2.7.x |
| **Security** | Spring Security, JWT |
| **Database** | MariaDB, MySQL |
| **ORM** | Spring Data JPA |
| **Infrastructure** | AWS S3 (Image Storage), Cloudtype |
| **Etc** | Gradle, Lombok |

---

## 🖥️ 주요 API 

| 기능 | 상세 내용 |
| :--- | :--- |
| **인증 및 인가** | JWT 기반 토큰 인증 시스템 및 최신 Spring Security(`SecurityFilterChain`) 설정 적용 |
| **UUID 기반 초대 시스템** | 유저별 고유 UUID를 생성하여 보안성이 강화된 공유 URL 및 친구 초대 프로세스 구현 |
| **이미지 아카이빙** | 친구가 선택한 6장의 이미지와 메시지를 하나의 트랜잭션으로 처리하는 응답 저장 로직 구현 |
| **파일 관리** | AWS S3 연동을 통한 유저 및 친구 선택 이미지 업로드 및 URL 관리 시스템 구축 |
| **인프라/배포** | 환경 변수 처리를 통한 DB 연동 보안 강화 및 Cloudtype 배포 프로세스 |

---

## 🚀 성과
### 서울여자대학교 축제 '서랑제' 실서비스 배포 및 운영
- 단 3일간의 집중 운영으로 누적 실사용자 100명 돌파
- 이미지 처리 트래픽을 안정적으로 수용하며 서비스 효용성 검증 성공
- 유저 피드백 기반의 빠른 대응으로 서비스 가용성 및 정합성 유지
