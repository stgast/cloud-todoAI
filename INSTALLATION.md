# Cloud TodoAI - Інструкція по запуску

## ⚙️ Вимоги

- **Docker** та **Docker Compose** (версія 3.8+)
- **Java 21** (для локальної розробки)
- **Node.js 18+** та **npm** (для фронтенду)
- **Git**
- **PostgreSQL** (якщо запускатися без Docker)

## 🚀 Швидкий старт (рекомендовано)

### 1. Клонування репозиторію

```bash
git clone https://github.com/G1oZ4m/cloud-todoAI.git
cd cloud-todoAI
```

### 2. Запуск всіх сервісів через Docker Compose

```bash
# Білд та запуск контейнерів
docker-compose up --build

# Або в фоновому режимі
docker-compose up -d --build
```

### 3. Перевірка статусу сервісів

```bash
docker-compose ps
```

**Очікуваний вивід:**
```
NAME                COMMAND                  SERVICE             STATUS              PORTS
postgres            "docker-entrypoint..."   postgres            Up 2 minutes        5432/tcp
user-service       "java -jar app.jar"      user-service        Up 2 minutes        0.0.0.0:8081->8081/tcp
booking-service    "java -jar app.jar"      booking-service     Up 2 minutes        0.0.0.0:8082->8082/tcp
frontend           "nginx -g daemon off"    frontend            Up 2 minutes        0.0.0.0:5173->5173/tcp
```

### 4. Відкрийте у браузері

- **Frontend**: http://localhost:5173
- **User API Swagger**: http://localhost:8081/swagger-ui.html
- **Booking API Swagger**: http://localhost:8082/swagger-ui.html

---

## 🛠️ Локальна розробка (без Docker)

### Крок 1: Запуск PostgreSQL

Опціон 1 - Docker контейнер:
```bash
docker run --name postgres-cloud \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin123 \
  -e POSTGRES_DB=cloud_todoai \
  -p 5432:5432 \
  -d \
  postgres:15-alpine
```

Опціон 2 - Локальна установка PostgreSQL:
```sql
-- Створити БД вручну
CREATE DATABASE cloud_todoai;
CREATE USER admin WITH PASSWORD 'admin123';
GRANT ALL PRIVILEGES ON DATABASE cloud_todoai TO admin;
```

### Крок 2: Запуск User Service

```bash
cd user-service

# Установка залежностей
mvn clean install

# Запуск сервісу
mvn spring-boot:run

# Або через IDE - запустити UserApplication.java
```

**Сервіс буде доступний на:** http://localhost:8081

### Крок 3: Запуск Booking Service

```bash
cd booking-service

# Установка залежностей  
mvn clean install

# Запуск сервісу
mvn spring-boot:run

# Або через IDE - запустити DemoApplication.java
```

**Сервіс буде доступний на:** http://localhost:8082

### Крок 4: Запуск Frontend

```bash
# У кореневій папці проекту

# Установка залежностей
npm install

# Запуск dev сервера
npm run dev

# Або білд для production
npm run build
```

**Frontend буде доступний на:** http://localhost:5173

---

## 📋 API Тестування

### Curl Приклади

#### 1. Реєстрація користувача

```bash
curl -X POST http://localhost:8081/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "SecurePassword123",
    "name": "Іван Франко",
    "phone": "+380501234567"
  }'
```

**Відповідь:**
```json
{
  "id": 1,
  "email": "john@example.com",
  "name": "Іван Франко",
  "phone": "+380501234567",
  "active": true
}
```

#### 2. Вхід користувача

```bash
curl -X POST http://localhost:8081/api/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "SecurePassword123"
  }'
```

**Відповідь:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "user": {
    "id": 1,
    "email": "john@example.com",
    "name": "Іван Франко",
    "phone": "+380501234567",
    "active": true
  }
}
```

#### 3. Створення резервації

```bash
curl -X POST http://localhost:8082/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "serviceName": "Консультація",
    "bookingDate": "2026-04-15T14:00:00",
    "description": "Консультація з фахівцем з питань розвитку"
  }'
```

#### 4. Отримання резервацій користувача

```bash
curl -X GET http://localhost:8082/api/bookings/user/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### 5. Оновлення статусу резервації

```bash
curl -X PATCH "http://localhost:8082/api/bookings/1/status?status=CONFIRMED" \
  -H "Content-Type: application/json"
```

---

## 🐛 Troubleshooting

### Проблема: "Port already in use"

**Рішення:**
```bash
# На Windows - знайти та завершити процес
netstat -ano | findstr :5173
taskkill /PID <PID> /F

# На Linux/Mac
lsof -i :5173
kill -9 <PID>
```

### Проблема: "Database connection refused"

**Рішення:**
```bash
# Перевіритить що PostgreSQL запущений
docker ps | grep postgres

# Перезапустіть контейнер
docker-compose restart postgres
```

### Проблема: "Maven build fails"

**Рішення:**
```bash
# Очистіть Maven кеш
mvn clean

# Видаліть .m2 папку
rm -rf ~/.m2/repository

# Спробуйте знову
mvn install
```

### Проблема: Frontend не з'єднується з API

**Рішення:**
1. Перевіріть що backend сервіси запущені
2. Перевіріть що CORS налаштований правильно
3. Перевіріть console в браузері на помилки

### Проблема: "Connection refused" при запуску Docker

**Рішення:**
```bash
# Переконайтесь що Docker daemon запущений
docker ps

# Якщо Docker не запущений, запустіть його (залежить від ОС)
# Windows: Docker Desktop
# Linux: sudo systemctl start docker
```

---

## 🧪 Запуск тестів

### User Service

```bash
cd user-service
mvn test
```

### Booking Service

```bash
cd booking-service
mvn test
```

### Frontend (не реалізовано)

```bash
npm test
```

---

## 🔄 Зупинка сервісів

### Docker Compose

```bash
# Зупинити контейнери (із збереженням даних)
docker-compose down

# Повна очистка (видалення томів)
docker-compose down -v
```

### Локальні сервіси

- Натисніть `Ctrl+C` у кожному терміналі де запущений сервіс

---

## 📝 .env Конфігурація

Створіть `.env` файл у кореневій папці (опціонально):

```env
# Frontend
VITE_API_USER_URL=http://localhost:8081
VITE_API_BOOKING_URL=http://localhost:8082

# Database
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin123
POSTGRES_DB=cloud_todoai

# OpenAI (опціонально для AI features)
OPENAI_API_KEY=your_api_key_here
```

Або скопіюйте з `.env.example`:
```bash
cp .env.example .env
```

---

## 📚 Корисні команди

```bash
# Переглянути логи контейнеру
docker-compose logs -f user-service

# Запустити конкретний контейнер
docker-compose up postgres user-service

# Вхід в контейнер
docker-compose exec postgres psql -U admin -d cloud_todoai

# Білд Docker образів без запуску
docker-compose build

# Перезапустити сервіс
docker-compose restart booking-service

# Видалити невикористовувані образи
docker image prune
```

---

## ✅ Контрольний список запуску

- [ ] Git клонований
- [ ] Docker та Docker Compose встановлені
- [ ] `docker-compose up --build` виконана успішно
- [ ] Всі 4 контейнери мають статус "Up"
- [ ] Frontend доступний на http://localhost:5173
- [ ] User API доступна на http://localhost:8081
- [ ] Booking API доступна на http://localhost:8082
- [ ] Можна зареєструватися та створити резервацію

---

## 🆘 Потребуєте допомоги?

1. Перевіріть логи контейнерів: `docker-compose logs`
2. Переглянете README.md для деталей про проект
3. Перевіріть GitHub Issues
4. Вивантажте問題 на GitHub Discussions

---

**Версія:** 1.0.0  
**Остання оновлення:** 26.03.2026
