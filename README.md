# Cloud TodoAI - Система резервування в хмурі 🚀

## � Опис п---

## 📦 Установка та запуск

### Локально (з Docker Compose)

```bash
# Клонування репозиторію
git clone https://github.com/G1oZ4m/cloud-todoAI.git
cd cloud-todoAI

# Запуск всіх сервісів
docker-compose up --build

# Перевірка статусу сервісів
docker-compose ps
```

**Сервіси будуть доступні:**
- Frontend: http://localhost:5173
- User Service: http://localhost:8081
- Booking Service: http://localhost:8082
- PostgreSQL: localhost:5432

### Локально (без Docker - розробка)

#### Backend (User Service)
```bash
cd user-service
mvn clean install
mvn spring-boot:run
```

#### Backend (Booking Service)
```bash
cd booking-service
mvn clean install
mvn spring-boot:run
```

#### Frontend
```bash
npm install
npm run dev
```

---

## 📡 API Endpoints

### User Service (Port 8081)

| Метод | Endpoint | Опис |
|-------|----------|------|
| POST | `/api/users/register` | Реєстрація користувача |
| POST | `/api/users/login` | Вхід (JWT token) |
| GET | `/api/users/{id}` | Отримати дані користувача |
| PUT | `/api/users/{id}` | Оновити профіль |
| DELETE | `/api/users/{id}` | Видалити користувача |

### Booking Service (Port 8082)

| Метод | Endpoint | Опис |
|-------|----------|------|
| POST | `/api/bookings` | Створити резервацію |
| GET | `/api/bookings` | Отримати список резервацій |
| GET | `/api/bookings/{id}` | Деталі резервації |
| PUT | `/api/bookings/{id}` | Оновити резервацію |
| DELETE | `/api/bookings/{id}` | Скасувати резервацію |
| POST | `/api/bookings/recommend` | AI-рекомендація часу |

---

## 🤖 Інтеграція AI

### Поточні AI-функції:
- [ ] Генерація описів послуг
- [ ] Рекомендація часу резервування
- [ ] Аналіз вибору користувача

### Використані AI-інструменти:
- **Cursor IDE** - генерація кодових каркасів
- **GitHub Copilot** - доповнення коду
- **OpenAI API** - рекомендації та обробка тексту

---

## 🧪 Тестування

### Запуск тестів

```bash
# User Service
cd user-service
mvn test

# Booking Service
cd booking-service
mvn test
```

### Тести що потрібно додати:
- [ ] Юніт-тести для сервісів
- [ ] Інтеграційні тести для API
- [ ] E2E-тести для frontend
- [ ] Тести безпеки (CORS, JWT)

---

## 📊 Гарантія якості

| Критерій | Статус | Примітка |
|----------|--------|---------|
| Функціональність | 🟡 | В процесі |
| Використання AI | 🟡 | Планується OpenAI |
| Архітектура | 🟢 | Мікросервіси готові |
| Документація | 🟡 | Розробляється |
| Тестування | 🔴 | Не розпочато |
| DevOps | 🟡 | Docker готовий |

## 🔧 Структура проекту

### User Service (`user-service/`)
```
src/main/java/com/example/user/
├── entity/
│   └── User.java              # JPA Entity для користувача
├── dto/
│   ├── UserDTO.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   └── RegisterRequest.java
├── repository/
│   └── UserRepository.java     # JPA Repository
├── service/
│   └── UserService.java        # Бізнес-логіка
├── controller/
│   └── UserController.java     # REST API endpoints
├── security/
│   └── JwtTokenProvider.java   # JWT генерування та валідація
└── config/
    └── SecurityConfig.java     # Spring Security конфіг
```

### Booking Service (`booking-service/`)
```
src/main/java/com/example/demo/
├── entity/
│   └── Booking.java            # JPA Entity для резервацій
├── dto/
│   ├── BookingDTO.java
│   └── CreateBookingRequest.java
├── repository/
│   └── BookingRepository.java   # JPA Repository
├── service/
│   ├── BookingService.java      # CRUD логіка
│   └── AIRecommendationService.java  # AI рекомендації
├── controller/
│   └── BookingController.java   # REST API endpoints
└── config/
    └── AppConfig.java           # Application configuration
```

### Frontend (`src/`)
```
src/
├── App.jsx                      # Основний компонент
├── main.jsx                     # Entry point
├── index.css                    # Tailwind стилі
├── components/
│   ├── Header.jsx               # Заголовок
│   ├── UserForm.jsx             # Форма реєстрації
│   ├── BookingForm.jsx          # Форма резервацій
│   └── BookingList.jsx          # Список резервацій
├── services/
│   ├── api.service.js           # Axios конфіг
│   └── index.js                 # API сервісні функції
└── config/
    └── api.config.js            # API endpoints конфіг
```

---

### Тиждень 3: Дискета архітектури ✅
- [x] Diagram архітектури
- [x] docker-compose.yml
- [x] Dockerfile для сервісів
- [x] README з документацією

### Тиждень 4: CRUD + База даних ✅
- [x] JPA entities для User
- [x] JPA entities для Booking
- [x] REST endpoints (повні CRUD)
- [x] Міграції БД (автоматичні через Hibernate)

### Тиждень 5: AI-інтеграція 🔄
- [x] AI Recommendation Service (базова логіка)
- [x] RestTemplate для міжсервісної комунікації
- [ ] OpenAI API connection (потребує API key)
- [ ] Поліпшена рекомендаційна система

### Тиждень 6: Тестування 🔄
- [ ] JUnit5 тести
- [ ] Інтеграційні тести
- [ ] Swagger документація

### Тиждень 7: Deploy + Monitoring 🔄
- [x] CI/CD pipeline (GitHub Actions)
- [ ] AWS/GCP deploy
- [ ] Логування та моніторинг

### Тиждень 8: Фінал 🔄
- [ ] Презентація результатів
- [ ] Фінальний звіт
- [ ] Деплой у production

---

## 🧪 Вже реалізовано

### Backend 🎯
- ✅ User Service із JWT аутентифікацією
- ✅ Booking Service з CRUD операціями  
- ✅ PostgreSQL інтеграція
- ✅ REST API endpoints (Swagger/OpenAPI документація)
- ✅ AI Recommendation Service (базова версія)
- ✅ PasswordEncoder (BCrypt)
- ✅ Docker контейнеризація

### Frontend 🎨
- ✅ React + Tailwind CSS дизайн
- ✅ Форми для реєстрації та резервацій
- ✅ Axios HTTP клієнт із JWT перехопленням
- ✅ API сервісні функції

### DevOps 🚀
- ✅ Docker Compose оркестрація (3 сервіси + PostgreSQL)
- ✅ GitHub Actions CI/CD pipeline
- ✅ Nginx конфіг для фронтенду
- ✅ Environment variables конфіг

---

## 🔧 Структура проекту

### User Service (`user-service/`)
```
src/main/java/com/example/user/
├── entity/
│   └── User.java              # JPA Entity для користувача
├── dto/
│   ├── UserDTO.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   └── RegisterRequest.java
├── repository/
│   └── UserRepository.java     # JPA Repository
├── service/
│   └── UserService.java        # Бізнес-логіка
├── controller/
│   └── UserController.java     # REST API endpoints
├── security/
│   └── JwtTokenProvider.java   # JWT генерування та валідація
└── config/
    └── SecurityConfig.java     # Spring Security конфіг
```

### Booking Service (`booking-service/`)
```
src/main/java/com/example/demo/
├── entity/
│   └── Booking.java            # JPA Entity для резервацій
├── dto/
│   ├── BookingDTO.java
│   └── CreateBookingRequest.java
├── repository/
│   └── BookingRepository.java   # JPA Repository
├── service/
│   ├── BookingService.java      # CRUD логіка
│   └── AIRecommendationService.java  # AI рекомендації
├── controller/
│   └── BookingController.java   # REST API endpoints
└── config/
    └── AppConfig.java           # Application configuration
```

### Frontend (`src/`)
```
src/
├── App.jsx                      # Основний компонент
├── main.jsx                     # Entry point
├── index.css                    # Tailwind стилі
├── components/
│   ├── Header.jsx               # Заголовок
│   ├── UserForm.jsx             # Форма реєстрації
│   ├── BookingForm.jsx          # Форма резервацій
│   └── BookingList.jsx          # Список резервацій
├── services/
│   ├── api.service.js           # Axios конфіг
│   └── index.js                 # API сервісні функції
└── config/
    └── api.config.js            # API endpoints конфіг
```

---

## 🚀 Швидкий старт

### Docker Compose (рекомендовано)

```bash
# Клонування репозиторію
git clone https://github.com/G1oZ4m/cloud-todoAI.git
cd cloud-todoAI

# Запуск всіх сервісів
docker-compose up --build

# Перевірка сервісів
docker-compose ps

# Зупинка
docker-compose down
```

**Сервіси будуть доступні:**
- Frontend: http://localhost:5173
- User API: http://localhost:8081/swagger-ui.html
- Booking API: http://localhost:8082/swagger-ui.html
- PostgreSQL: localhost:5432 (admin/admin123)

### Локально (для розробки)

#### 1. База даних
```bash
# Запуск лише PostgreSQL
docker run --name postgres-cloud \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin123 \
  -e POSTGRES_DB=cloud_todoai \
  -p 5432:5432 \
  postgres:15-alpine
```

#### 2. User Service
```bash
cd user-service
mvn clean install
mvn spring-boot:run
# Доступно на http://localhost:8081
```

#### 3. Booking Service
```bash
cd booking-service
mvn clean install
mvn spring-boot:run
# Доступно на http://localhost:8082
```

#### 4. Frontend
```bash
npm install
npm run dev
# Доступно на http://localhost:5173
```

---

## 🧪 Тестування API

### User Service Examples

**Реєстрація:**
```bash
curl -X POST http://localhost:8081/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "name": "Іван Франко",
    "phone": "+380123456789"
  }'
```

**Вхід:**
```bash
curl -X POST http://localhost:8081/api/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

**Отримати користувача:**
```bash
curl -X GET http://localhost:8081/api/users/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Booking Service Examples

**Створити резервацію:**
```bash
curl -X POST http://localhost:8082/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "serviceName": "Консультація",
    "bookingDate": "2026-04-01T10:00:00",
    "description": "Консультація з фахівцем"
  }'
```

**Отримати резервації користувача:**
```bash
curl -X GET http://localhost:8082/api/bookings/user/1
```

**Оновити статус резервації:**
```bash
curl -X PATCH http://localhost:8082/api/bookings/1/status?status=CONFIRMED \
  -H "Content-Type: application/json"
```

---

## 📋 CI/CD

Проект використовує **GitHub Actions** для автоматичної:
- ✅ Компіляції Java проектів
- ✅ Запуску тестів
- ✅ Білду Docker образів
- ✅ Перевірки docker-compose конфігу

Дивіться `.github/workflows/build.yml` для деталей.

---

### AWS EC2

```bash
# SSH на інстанс
ssh -i key.pem ubuntu@your-ec2-ip

# Клонування та запуск
git clone https://github.com/G1oZ4m/cloud-todoAI.git
cd cloud-todoAI
docker-compose up -d
```

### GCP Cloud Run

```bash
# Білд Docker image
docker build -t gcr.io/your-project/cloud-todoai .

# Push на GCP
docker push gcr.io/your-project/cloud-todoai

# Deploy
gcloud run deploy cloud-todoai --image gcr.io/your-project/cloud-todoai
```

---

## 📝 Git Workflow

### Branches
- `main` - production
- `develop` - розробка
- `feature/xxx` - нові функції
- `bugfix/xxx` - виправлення

### Commits
```bash
git commit -m "feat: додавання резервацій через API"
git commit -m "fix: виправлення помилки в JWT"
git commit -m "docs: оновлення README"
```

---

## 🐛 Вирішення проблем

### Docker контейнеры не запускаються
```bash
docker-compose logs -f
docker-compose down -v
docker-compose up --build
```

### PostgreSQL недоступна
```bash
# Перевірка статусу
docker-compose ps

# Переперезавантаження
docker-compose restart postgres
```

### Port вже використовується
```bash
# Змінити порт у docker-compose.yml
ports:
  - "5173:5173"  # Змінити перше число
```

---

## 📚 Корисні посилання

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [Docker Compose Guide](https://docs.docker.com/compose/)
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [Tailwind CSS](https://tailwindcss.com)
- [OpenAI API](https://platform.openai.com/docs)

---

## 📄 Ліцензія

MIT License - вільно використовувати у навчальних цілях

---

**Останнє оновлення:** 26.03.2026  
**Версія:** 1.0.0-alpha  
**Статус:** 🟡 В розробці

---

## 📊 Model даних

### Booking

* id
* userId
* serviceId
* date
* time

### Userросервісна архітектура для системи резервування (бронювання) послуг з інтеграцією AI. Проект розроблений командою студентів із використанням методики "Vibe Coding" для прискорення розробки за допомогою AI-інструментів.

**Тема проекту:** Система резервування послуг з рекомендаціями на базі AI

---

## 👥 Команда

| Роль | Ім'я | Відповідальність |
|------|------|------------------|
| Лідер проекту | [Ім'я] | Координація, GitHub, презентації |
| Backend Lead | [Ім'я] | Java/Spring, мікросервіси |
| Frontend Lead | [Ім'я] | React, UI/UX |
| DevOps | [Ім'я] | Docker, CI/CD, хмура |

*Заповнити кваліфіковану інформацію про членів команди*

---

## �️ Архітектура

```
┌─────────────────────────────────────────────────────┐
│                   Frontend (React)                   │
│              Port 5173 - Tailwind CSS                │
└──────────────────────┬────────────────────────────────┘
                       │ HTTP/REST
        ┌──────────────┴──────────────┐
        │                             │
┌───────▼────────┐        ┌──────────▼───────┐
│ User Service   │        │ Booking Service   │
│ Port 8081      │        │ Port 8082         │
│ JWT Auth       │        │ CRUD Резервацій   │
└───────┬────────┘        └──────────┬────────┘
        │                            │
        └──────────────┬─────────────┘
                       │ SQL
                ┌──────▼───────┐
                │  PostgreSQL  │
                │  Port 5432   │
                └──────────────┘
```

---

## 🛠️ Технічний стек

| Компонент | Технологія | Версія |
|-----------|-----------|--------|
| **Backend** | Java | 21 LTS |
| **Framework** | Spring Boot | 3.x |
| **ORM** | Hibernate/JPA | - |
| **База даних** | PostgreSQL | 15 |
| **Frontend** | React | 18.2 |
| **Стилізація** | Tailwind CSS | 3.3 |
| **Build Tool** | Vite | 5.0 |
| **Контейнеризація** | Docker | - |
| **Оркестрація** | Docker Compose | 3.8 |

---

## 📊 Model danych

### Booking

* id
* userId
* serviceId
* date
* time

### User

* id
* email
* password

### Service

* id
* name
* duration

---

## 🌐 Endpointy REST

### Booking Service

* GET /bookings – pobranie listy rezerwacji
* POST /bookings – utworzenie nowej rezerwacji
* GET /bookings/health – sprawdzenie działania serwisu

### User Service

* POST /register – rejestracja użytkownika
* POST /login – logowanie użytkownika

### Service Catalog

* GET /services – pobranie listy usług
* POST /services – dodanie nowej usługi

---

## ⚙️ Stos technologiczny

* Backend: Java (Spring Boot)
* Frontend: React / HTML + JavaScript
* Baza danych: PostgreSQL
* DevOps: Docker
* Chmura: Railway
* Narzędzia AI: ChatGPT, GitHub Copilot

---

<img width="1221" height="571" alt="image" src="https://github.com/user-attachments/assets/265852b7-820c-4a4a-9096-ab60f51e0216" />

Diagram przedstawia architekturę mikroserwisową.
Frontend komunikuje się z Booking Service.
Booking Service współpracuje z innymi serwisami oraz bazą danych.
Każdy serwis posiada własną bazę danych, co zapewnia skalowalność systemu.

## 📅 Postęp prac

### Tydzień 1

* wybór tematu projektu
* utworzenie repozytorium GitHub
* przygotowanie dokumentacji (README)
* konfiguracja środowiska oraz narzędzi AI

### Tydzień 2

* zaprojektowanie architektury mikroserwisowej
* przygotowanie modeli danych
* definicja endpointów REST
* stworzenie diagramu systemu

### Tydzień 3

* utworzenie pierwszego mikroserwisu (Booking Service)
* implementacja podstawowych endpointów REST
* dodanie endpointu health-check
* uruchomienie aplikacji lokalnie

---

## 👥 Zespół

* **Stanislav Shary** – Frontend
* **Oleksandr Nechyporenko** – Booking Service
* **Vladyslav Khanchych** – User Service + AI Service

---

## 🔒 Bezpieczeństwo (planowane)

* autoryzacja użytkowników (JWT)
* walidacja danych wejściowych

---

## 🚀 Plany rozwoju

W kolejnych etapach projektu planowane jest:

* integracja z bazą danych PostgreSQL
* implementacja pełnego CRUD
* dodanie autoryzacji użytkowników
* wdrożenie aplikacji w chmurze (Railway)
* konfiguracja CI/CD
