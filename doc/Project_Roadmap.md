# Bondhu Pharmacy - Project Roadmap

## Phase 1: MVP (Minimum Viable Product) - Launch Tomorrow
**Goal:** Get the app to invitees quickly to build a user base and start taking basic orders.
*   **Platform:** PWA (Progressive Web App) - Users can add it to their home screen directly from the browser, acting like a native app.
*   **Core Features:**
    *   Basic Medicine Catalog with Prices (Hardcoded JSON or simple Firebase Firestore database).
    *   Search functionality.
    *   Add to Cart & Wishlist.
    *   Simple Checkout Form (Name, Phone, Address, Medicine details).
    *   Cash on Delivery (COD) / Manual Payment Instructions.
    *   Push Notifications (via Firebase Cloud Messaging / OneSignal) to keep users updated on new stock or offers.

## Phase 2: Growth & Automation (Next 1-2 Months)
**Goal:** Automate the ordering process and build a better user experience.
*   **User Authentication:** Phone number login via Firebase Auth (OTP).
*   **Order Tracking:** Real-time status updates (Pending -> Confirmed -> Shipped -> Delivered).
*   **Admin Dashboard:** A web-based admin panel to manage products, prices, stock, and view incoming orders.
*   **Prescription Upload:** Allow users to upload a photo of their prescription for complex orders.
*   **Native App Build:** Package the PWA using Capacitor into a native `.apk` for wider distribution.

## Phase 3: Scaling & Marketing (3-6 Months)
**Goal:** Expand business, introduce offers, and fully automate payments.
*   **Payment Gateway Integration:** Automate bKash, Nagad, and Card payments (e.g., SSLCommerz, aamarPay).
*   **Marketing Tools:** Promo codes, referral systems, and loyalty points.
*   **Advanced Notifications:** Personalized notifications for medicine reminders or prescription refills.
*   **AI/Bot Integration:** Basic chatbot for common customer queries.
