Rezerwacja Wizyty (Mikroserwisy)


📌 Opis projektu
Projekt polega na stworzeniu aplikacji chmurowej do zarządzania zadaniami (To-Do) w architekturze mikroserwisowej.
Aplikacja wykorzystuje sztuczną inteligencję do sugerowania priorytetu oraz kategorii zadań.

🧱 Architektura
System składa się z kilku mikroserwisów:
Task Service – zarządzanie zadaniami (CRUD)
User Service – rejestracja i logowanie użytkowników
AI Service – generowanie priorytetu i kategorii zadań (OpenAI)

🛠 Stos technologiczny
Backend: Java (Spring Boot)
Frontend: React (lub proste HTML + JavaScript)
Baza danych: PostgreSQL (Railway)
AI: OpenAI API
DevOps: Docker, Docker Compose
Chmura: Railway

👥 Zespół
Stanislav Shary – Frontend
Oleksandr Nechyporenko  – Task Service
Vladyslav  Khanchych  – User Service + AI Service

🎯 Cel projektu
Celem projektu jest stworzenie skalowalnej aplikacji chmurowej z wykorzystaniem architektury mikroserwisowej oraz integracji AI.

⚙️ Konfiguracja środowiska
W projekcie wykorzystano następujące narzędzia i środowisko:
IDE: Visual Studio Code / IntelliJ IDEA
System kontroli wersji: Git + GitHub
Backend: Java (Spring Boot)
Konteneryzacja: Docker
Baza danych: PostgreSQL (Railway)

🤖 Narzędzia AI
W trakcie realizacji projektu wykorzystujemy narzędzia sztucznej inteligencji:
GitHub Copilot – do generowania kodu i sugestii
ChatGPT – do wsparcia w projektowaniu architektury oraz debugowaniu
OpenAI API – planowane wykorzystanie w dalszych etapach projektu
AI wspomaga proces tworzenia aplikacji, jednak kod jest weryfikowany i poprawiany przez członków zespołu.

## 🧱 Architektura systemu

Aplikacja została zaprojektowana w architekturze mikroserwisowej.
Każdy mikroserwis odpowiada za określoną funkcjonalność i działa niezależnie.

### Komponenty systemu:

* Frontend – interfejs użytkownika (React lub HTML + JavaScript)
* Booking Service – zarządzanie rezerwacjami wizyt
* User Service – obsługa użytkowników (rejestracja i logowanie)
* Service Catalog – zarządzanie listą dostępnych usług

Komunikacja między serwisami odbywa się za pomocą REST API.

---

## 📦 Modele danych

### Booking

```json
{
  "id": 1,
  "userId": 2,
  "serviceId": 3,
  "date": "2026-03-20",
  "time": "14:00"
}
```

### User

```json
{
  "id": 1,
  "email": "user@test.com",
  "password": "hashed_password"
}
```

### Service

```json
{
  "id": 1,
  "name": "Strzyżenie",
  "duration": 30
}
```

---

## 🌐 Endpointy REST

### Booking Service

* GET /bookings – pobranie wszystkich rezerwacji
* POST /bookings – utworzenie nowej rezerwacji
* DELETE /bookings/{id} – usunięcie rezerwacji

### User Service

* POST /register – rejestracja użytkownika
* POST /login – logowanie użytkownika

### Service Catalog

* GET /services – pobranie listy usług
* POST /services – dodanie nowej usługi

---


## 📊 Opis rozwiązania

System został zaprojektowany w sposób skalowalny i modułowy.
Każdy mikroserwis posiada własną logikę biznesową i może być rozwijany niezależnie.

Architektura umożliwia łatwe wdrożenie aplikacji w środowisku chmurowym (Railway).

<img width="472" height="405" alt="image" src="https://github.com/user-attachments/assets/feb256e5-5fbc-4567-a11b-fdb1c227385f" />


