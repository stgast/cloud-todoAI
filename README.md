# Cloud Booking System (Mikroserwisy)

## 📌 Opis projektu

Projekt polega na stworzeniu aplikacji chmurowej umożliwiającej rezerwację wizyt online.
Użytkownicy mogą przeglądać dostępne usługi, wybierać dogodny termin oraz zarządzać swoimi rezerwacjami.

Aplikacja została zaprojektowana w architekturze mikroserwisowej, co pozwala na łatwą skalowalność, modularność oraz niezależny rozwój poszczególnych komponentów systemu.

---

## 🎯 Cel projektu

Celem projektu jest stworzenie nowoczesnej aplikacji chmurowej wykorzystującej architekturę mikroserwisową oraz narzędzia wspomagające proces programowania.

Projekt ma na celu:

* zrozumienie działania mikroserwisów
* naukę pracy z technologiami chmurowymi
* implementację REST API
* rozwój umiejętności pracy zespołowej

---

## 🧱 Architektura systemu

System składa się z kilku niezależnych mikroserwisów:

* **Frontend** – interfejs użytkownika (React lub HTML + JavaScript)
* **Booking Service** – zarządzanie rezerwacjami wizyt
* **User Service** – rejestracja i logowanie użytkowników
* **Service Catalog** – lista dostępnych usług

Każdy mikroserwis posiada własną logikę biznesową i komunikuje się poprzez REST API.

System jest przygotowany do wdrożenia w środowisku chmurowym (Railway).

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
