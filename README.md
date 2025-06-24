
# 🛍️ Catalog Management System

A full-stack **Product & Promotion Management** application built using:

- ⚛️ React + TypeScript (Frontend)
- 🌱 Spring Boot + Java 8 (Backend)
- 🐬 MySQL (Database)

---

## 📁 Project Structure

```
Sprint3_Project/
├── frontend/    ← React + TypeScript UI
├── backend/     ← Spring Boot REST API
└── README.md
```

---

## 🚀 Features

### 🛒 Product Management

- Add products with name, SKU, category, size, color, price, inventory
- Auto-store product details in multiple relational tables
- View all products in a dynamic table
- Edit or delete products (with backend sync)

### 🎟️ Promotion Management

- Create promo codes by category, product, or order type
- Promo code auto-generates starting with `PROMO`
- View created promos instantly in a table

---

## 🛠️ Tech Stack

| Frontend         | Backend         | Database |
|------------------|------------------|----------|
| React + TypeScript | Spring Boot + Java 8 | MySQL    |
| HTML5 + CSS3     | Spring REST, JPA |          |
| Axios            | Maven            |          |

---

## 🔧 Local Setup

### ⚙️ Backend (Spring Boot)

```bash
cd backend
# Open in IntelliJ or use Maven CLI
mvn clean install
mvn spring-boot:run
```

Update `application.properties` with your MySQL credentials.

### 💻 Frontend (React)

```bash
cd frontend
npm install
npm start
```

Runs at: [http://localhost:3000](http://localhost:3000)

---

## 🔗 API Endpoints

### Products
| Method | Endpoint            | Description         |
|--------|---------------------|---------------------|
| POST   | `/api/products`     | Add new product     |
| GET    | `/api/products`     | List all products   |
| PUT    | `/api/products`     | Update product      |
| DELETE | `/api/products`     | Delete product      |

### Promotions
| Method | Endpoint            | Description             |
|--------|---------------------|-------------------------|
| POST   | `/api/promotions`   | Create new promo code   |
| GET    | `/api/promotions`   | List all promo codes    |

---

## 🎨 UI Highlights

- Responsive layout with sidebar
- Native HTML5 validation tooltips
- Modal popup for promo code
- Hover effects and smooth transitions
- Icons and clean design to reduce user fatigue

---

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/ce871ff9-6aae-4554-8dd0-9fbe5368f160)


---

## 📦 Deployment

You can deploy:
- Frontend: **Netlify**, **Vercel**
- Backend: **Render**, **Railway**


---

