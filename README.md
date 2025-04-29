# PoistenieApp

A simple web application for insurance management written in **Java**, using **Spring Boot**, **Thymeleaf** and **Bootstrap**.

## How to run

1. Import the provided database backup (SQL dump) into your local MySQL or MariaDB server.
2. Configure database connection settings (if necessary) inside the application (`application.properties`).
3. Run the project using your IDE (IntelliJ IDEA, Eclipse...) or directly with:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the application in the browser at:

   ```
   http://localhost:8080
   ```

## Login credentials for testing

- **Admin account**
    - Email: `admin@test.com`
    - Password: `123`

- **User account**
    - Email: `user@test.com`
    - Password: `123`

- **Users registered by the admin**  
  These users were created by an administrator and can access only their own profile:
    - `martinnovak@test.com` / `123`
    - `luciakovacova@test.com` / `123`

*(Password length temporarily lowered to 3 characters for easier testing.)*

# PoistenieApp

Jednoduchá webová aplikácia na správu poistení vytvorená v **Java** pomocou **Spring Boot**, **Thymeleaf** a **Bootstrap**.

## Ako spustiť

1. Naimportuj dodanú zálohu databázy (SQL dump) do svojho MySQL alebo MariaDB servera.
2. Nastav si pripojenie k databáze v projekte (ak bude treba - v `application.properties`).
3. Spusť aplikáciu cez IDE (IntelliJ IDEA, Eclipse...) alebo cez príkazový riadok:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Otvor aplikáciu v prehliadači:

   ```
   http://localhost:8080
   ```

## Prihlasovacie údeje na testovanie

- **Admin účet**
    - Email: `admin@test.com`
    - Heslo: `123`

- **Používateľský účet**
    - Email: `user@test.com`
    - Heslo: `123`

- **Používatelia vytvorení administrátorom**  
  Títo používatelia boli zaregistrovaní adminom a majú prístup iba k detailu svojho vlastného profilu:
    - `martinnovak@test.com` / `123`
    - `luciakovacova@test.com` / `123`

*(Heslá sú zatiaľ nastavené na minimálne 3 znaky pre jednoduché testovanie.)*

