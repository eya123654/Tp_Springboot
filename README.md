# TP Spring Boot - Gestion de bibliothèque

Ce projet couvre les trois TPs en une base fonctionnelle complète :

## TP1 - CRUD de livres
- Application Spring Boot
- Base H2 en mémoire
- Entité `Book`
- Repository JPA
- Service métier
- Contrôleur REST
- Données initiales
- Tests d'intégration

Endpoints disponibles :
- `GET /api/books`
- `GET /api/books/{id}`
- `POST /api/books`
- `PUT /api/books/{id}`
- `DELETE /api/books/{id}`

## TP2 - Amélioration métier
- Entités `Author` et `Category`
- Relation entre les livres, les auteurs et les catégories
- Recherche multi-critères (`title`, `author`, `category`)
- Validation métier via annotations JPA/Bean Validation

Endpoints supplémentaires :
- `GET /api/books/search?title=...&author=...&category=...`
- `GET /api/authors`
- `GET /api/categories`

## TP3 - Front-end et sécurité
- Interface web simple avec HTML/JS
- Connexion via formulaire Spring Security
- Comptes prédéfinis :
  - `user / user123`
  - `admin / admin123`
- API sécurisée par rôle

## Lancer le projet
```bash
./mvnw spring-boot:run
```

Puis ouvrir :
- `http://localhost:8081/`
- `http://localhost:8081/login.html`
- `http://localhost:8081/h2-console`
