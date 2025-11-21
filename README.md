# 📱 EstiamApp - Application Mobile Android

Application Android moderne développée en **Kotlin** avec **Jetpack Compose** et **Material 3**, démontrant une architecture propre et l'implémentation complète des fonctionnalités modernes d'une application professionnelle.

## 🎯 Contexte

Projet réalisé dans le cadre du cours **E5 WMD - Développement Mobile Android** avec pour objectif de créer une application complète intégrant authentification, gestion de données locale et cloud, API REST, notifications et tests unitaires.

---

## ✨ Fonctionnalités Principales

### 🔐 Authentification
- Login et Register avec Firebase Authentication
- Validation UI en temps réel (email formaté, mot de passe ≥ 6 caractères)
- Protection des écrans (redirection automatique si non connecté)
- Gestion de session persistante

### 🗄️ Gestion des Données
- **Room Database** : Stockage local avec entités User, DAO et Flow reactif
- **Firestore** : Synchronisation cloud en temps réel
- **API REST** : Consommation d'API publique (escuelajs) avec Retrofit + Moshi

### 📱 Interface Utilisateur
- **Jetpack Compose** avec Material 3
- Navigation multi-écrans avec Bottom Navigation Bar
- Thème personnalisé Ocean (bleu/violet/turquoise vibrants)
- Pull-to-Refresh et pagination au scroll
- États UI gérés (loading, success, error, empty)
- Previews Light/Dark/Phone/Tablet

### 🔔 Notifications
- Notifications locales avec canal personnalisé
- Firebase Cloud Messaging (FCM) pour les push notifications
- Gestion des permissions Android 13+

### ⚙️ Tâches en Arrière-Plan
- WorkManager avec tâches OneTime
- Contraintes réseau (WiFi) et batterie (Charging)
- Notifications à l'exécution

### 🌍 Internationalisation
- Support FR/EN avec fichiers strings.xml
- Sélecteur de langue dans Settings
- Persistance du choix utilisateur

### 🧪 Tests Unitaires
- **49 tests unitaires** couvrant ViewModels, Repositories et DAO
- JUnit + Mockito + Coroutines Test
- Couverture des cas nominaux et d'erreur

---

## 🏗️ Architecture

Le projet suit le pattern **MVVM** (Model-View-ViewModel) avec une séparation claire des responsabilités :

```
app/
├── data/
│   ├── local/          # Room Database (UserEntity, UserDao, AppDatabase)
│   ├── remote/         # Retrofit (ApiService, ApiClient)
│   ├── model/          # DTOs (ProductDto, UserDto, CategoryDto)
│   └── *Repository.kt  # Couche d'accès aux données
│
├── ui/
│   ├── auth/           # AuthViewModel, RequireAuth
│   ├── screens/        # Écrans Compose
│   ├── components/     # Composants réutilisables
│   ├── products/       # ProductCard, ProductsViewModel
│   ├── users/          # UserCard, UsersViewModel
│   ├── previews/       # Previews Light/Dark/Tablet
│   └── theme/          # Thème personnalisé
│
├── notifications/      # NotificationHelper, FirebaseMessagingService
├── work/              # WorkManager (NotifyWorker, Schedulers)
└── App.kt             # Application class
```

---

## 🛠️ Technologies Utilisées

### Langage & Framework
- **Kotlin** 2.0.21
- **Jetpack Compose** (BOM 2024)
- **Material 3**

### Firebase
- **Firebase Authentication** (Email/Password)
- **Firestore Database**
- **Firebase Cloud Messaging (FCM)**

### Base de Données Locale
- **Room** 2.6+ (avec Flow)

### Réseau
- **Retrofit** 2.9+ (Client HTTP)
- **Moshi** (Parsing JSON)
- **OkHttp** (Logging Interceptor)

### Autres
- **WorkManager** 2.9+ (Tâches en arrière-plan)
- **Coil** (Chargement d'images)
- **Navigation Compose**
- **Coroutines** + **Flow**

### Tests
- **JUnit** 4.13.2
- **Mockito** 5.7.0
- **Coroutines Test** 1.7.3

---

## 📦 Installation

### Prérequis
- Android Studio Hedgehog (2023.1.1) ou supérieur
- JDK 11 ou supérieur
- Gradle 8.0+
- Appareil/Émulateur Android API 26+ (Android 8.0)

### Étapes

1. **Cloner le repository**
```bash
git clone https://github.com/dassoah33/EstiamAppKotlin.git
cd EstiamAppKotlin
```

2. **Configurer Firebase**
   - Le fichier `google-services.json` est déjà inclus
   - Firebase Authentication, Firestore et FCM sont pré-configurés

3. **Ouvrir dans Android Studio**
   - File → Open → Sélectionner le dossier du projet
   - Attendre la synchronisation Gradle

4. **Lancer l'application**
   - Connecter un appareil ou lancer un émulateur
   - Cliquer sur Run (▶️)

---

## 🧪 Tests Unitaires

Le projet contient **49 tests unitaires** répartis ainsi :

| Module | Fichier | Tests |
|--------|---------|-------|
| ViewModels | AuthViewModelTest | 9 |
| ViewModels | ProductsViewModelTest | 9 |
| ViewModels | UsersViewModelTest | 8 |
| Data | UserDaoTest | 9 |
| Repositories | ProductRepositoryTest | 6 |
| Repositories | UserRepositoryTest | 8 |

### Lancer les tests

Dans Android Studio :
```
Clique droit sur app/src/test/ → Run 'Tests in...'
```

Ou en ligne de commande :
```bash
./gradlew test
```

Tous les tests doivent passer ✅

---

## 🎨 Thème Personnalisé

L'application utilise un thème **Ocean** moderne avec :
- **Mode Clair** : Bleu électrique, Violet, Turquoise
- **Mode Sombre** : Bleu néon, Violet pastel, Turquoise néon
- **5 couleurs accent** : Orange, Rose, Jaune, Vert, Violet

---

## 📋 Checklist des Fonctionnalités du Sujet

- ✅ Authentification Firebase Email/Password
- ✅ Validation UI (email formaté, password ≥ 6)
- ✅ Navigation multi-écrans (Bottom Bar)
- ✅ Room Database locale
- ✅ Firestore Database cloud
- ✅ API REST avec Pull-to-Refresh et Pagination
- ✅ Notifications locales
- ✅ Firebase Cloud Messaging (FCM)
- ✅ WorkManager (OneTime + contraintes)
- ✅ Localisation FR/EN
- ✅ Logs structurés (Logcat)
- ✅ Tests unitaires (49 tests)
- ✅ Previews Light/Dark/Phone/Tablet
- ✅ Architecture MVVM propre

---

## 🔍 Points Techniques Notables

### Validation UI
- Validation en temps réel avec feedback immédiat
- Pattern email avec `android.util.Patterns`
- Désactivation des boutons si validation échoue

### Gestion d'État
- StateFlow pour les états authentification
- Flow pour les données Room
- États UI explicites (loading/success/error/empty)

### Navigation Sécurisée
- Composant `RequireAuth` pour protéger les écrans
- Redirection automatique vers Login si non connecté
- Sauvegarde et restauration d'état

### Pagination
- Chargement par chunks de 5 éléments
- Détection automatique du scroll en fin de liste
- Pull-to-refresh pour actualiser

---

## 📝 Logs & Debug

L'application utilise un système de logs structuré avec tags :
- `Auth` : Événements d'authentification
- `FCM` : Notifications Firebase
- `API` : Requêtes réseau
- `WorkManager` : Tâches en arrière-plan
- `MainActivity` : Lifecycle de l'activité principale

---

## 🚀 Améliorations Futures

- [ ] Injection de dépendances (Hilt)
- [ ] Tests d'intégration (Espresso)
- [ ] CI/CD avec GitHub Actions
- [ ] Couverture de code avec JaCoCo
- [ ] Animations Compose avancées
- [ ] Mode hors-ligne avec cache

---

## 👨‍💻 Auteur

**[Votre Nom]**
- GitHub: [@dassoah33](https://github.com/dassoah33)
- Email: contact@dassoah.com

---

## 📄 Licence

Ce projet est développé dans un cadre éducatif pour le cours E5 WMD.

---

## 🙏 Remerciements

- [Firebase](https://firebase.google.com/) pour les services backend
- [Escuela JS API](https://api.escuelajs.co/) pour l'API publique de test
- [Material Design 3](https://m3.material.io/) pour les guidelines UI
- [Jetpack Compose](https://developer.android.com/jetpack/compose) pour le framework UI moderne

---

**📅 Date de remise :** 16 novembre 2025  
**🎓 Fait :** E5 WMD - Développement Mobile Android