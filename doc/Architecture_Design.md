# Bondhu Pharmacy - Architecture Design

## 1. High-Level Architecture Overview

The system is designed to be lightweight, scalable, and rapidly deployable. The frontend is entirely decoupled from the backend using a Serverless Architecture.

*   **Client (Frontend):** A Progressive Web App (PWA) built with React.
*   **Backend as a Service (BaaS):** Firebase (Google Cloud) handles the database, authentication, and push notifications without requiring a custom backend server.
*   **Hosting:** GitHub Pages (for rapid MVP deployment and zero hosting cost initially) or Vercel.

---

## 2. Technology Stack

### Frontend
*   **Framework:** React (using Vite for fast building).
*   **Styling:** Tailwind CSS (Rapid UI development matching "Bondhu" aesthetic).
*   **State Management:** Zustand (lighter and easier than Redux for the MVP).
*   **Routing:** React Router DOM.
*   **Icons:** Lucide React or React Icons.

### Backend & Database (Firebase)
*   **Database:** Cloud Firestore (NoSQL database, highly scalable for product catalogs and order management).
*   **Authentication (Phase 2):** Firebase Authentication (Phone number/OTP login).
*   **Storage (Phase 2):** Firebase Cloud Storage (For storing uploaded prescriptions and medicine images).
*   **Notifications:** Firebase Cloud Messaging (FCM) or OneSignal.

---

## 3. UI/UX & Color Design System

The design borrows the premium aesthetic from the "Bondhu" application.

*   **Theme Mode:** Dark Mode by default.
*   **Color Palette:**
    *   **Background (`bg`):** `#0A0C0B`
    *   **Panels/Cards (`panel`):** `#14181A` & `#1C2123`
    *   **Brand/Primary (`teal/blue/primary`):** `#A3E635` (Lime Green)
    *   **Success (`green`):** `#38EC48`
    *   **Text (`txt`):** `#F1F4EF`
    *   **Muted Text (`txtsoft`):** `#D1D7DB` & `#93A08F`
    *   **Borders/Lines (`line`):** `#1E2422`
*   **Typography:** Inter (Sans-serif) and Noto Sans Bengali (for local language support).

---

## 4. Data Models (Firestore Structure)

### `medicines` Collection
Stores the catalog of available medicines.
*   `id` (String)
*   `name` (String)
*   `generic_name` (String)
*   `price` (Number)
*   `category` (String - e.g., Tablet, Syrup, Injection)
*   `in_stock` (Boolean)
*   `image_url` (String - optional for MVP)

### `orders` Collection
Stores customer orders.
*   `id` (String)
*   `customer_name` (String)
*   `customer_phone` (String)
*   `delivery_address` (String)
*   `items` (Array of objects containing medicine ID, name, quantity, price)
*   `total_amount` (Number)
*   `status` (String - `pending`, `confirmed`, `shipped`, `delivered`, `cancelled`)
*   `created_at` (Timestamp)

### `users` Collection (Phase 2)
Stores registered users.
*   `uid` (String - Firebase Auth ID)
*   `phone` (String)
*   `name` (String)
*   `address` (String)

---

## 5. Deployment Strategy

1.  **Code Repository:** GitHub.
2.  **Continuous Integration/Continuous Deployment (CI/CD):** 
    *   A GitHub Action will be set up to automatically build the Vite React app when code is pushed to the `main` branch.
    *   The built output (the `dist/` folder) will be deployed to GitHub Pages.
3.  **App Distribution:** Users will receive a link. Upon opening the link, they will be prompted to "Install App" (Add to Home Screen) via PWA manifest configuration.
