A modern Android application that tracks the real-time location of the **International Space Station (ISS)**. Built with **Clean Architecture** and **MVI (Model-View-Intent)** for maintainability and scalability.

## 🛠️ Tech Stack

- **Kotlin** - Primary language
- **Clean Architecture** - Separation of concerns
- **MVI (Model-View-Intent)** - Unidirectional data flow
- **Coroutines & Flow** - Asynchronous programming
- **Firebase Authentication** - Secure user authentication
- **Hilt** - Dependency injection
- **Retrofit** - API calls
- **Jetpack Compose** (optional) - Modern UI

## 📌 Features

- 🔭 **Track ISS Location** in real time
- 🌍 **View ISS Coordinates** (Latitude, Longitude)
- 🔐 **User Authentication** via Firebase
- 📡 **Network Calls** using Retrofit
- 🔄 **Reactive State Management** with Flow
- 🏗 **Scalable & Modular** Clean Architecture

## 🏗 Architecture Overview

The project follows **Clean Architecture** principles:


## Prerequisites

Before running the app, ensure you have the following:

1. **Android Studio**:
    - Download and install the latest version of [Android Studio](https://developer.android.com/studio).

2. **Firebase Project**:
    - Create a Firebase project in the [Firebase Console](https://console.firebase.google.com/).
    - Add a App to your Firebase project.
    - Make sure package name and Debug signing certificate SHA-1 fingerprint are configured correctly.
    - Enable **Google Sign-In** in the Firebase Authentication section.
    - Add an Android app to your Firebase project and download the `google-services.json` file.
    - Place the `google-services.json` file in the `app` directory of the project.
    - Add WEB_CLIENT_ID in Constants.kt

3. **Steps to Generate Debug SHA-1 Fingerprint**:
    - On macOS/Linux, open the terminal. 
    - On Windows, open Command Prompt or PowerShell.
    - keytool -list -v \
      -alias androiddebugkey -keystore ~/.android/debug.keystore or follow the instructions here: https://developers.google.com/android/guides/client-auth#maclinux
    - macOS/Linux: The debug keystore is located at ~/.android/debug.keystore. 
    - Windows: The debug keystore is located at %USERPROFILE%\.android\debug.keystore. 
    - Enter Keystore Password: When prompted for a password, enter android (the default password for the debug keystore). 
    - Copy the SHA-1 Fingerprint:
    - Look for the SHA1 field in the output. It will look something like this: SHA1: AB:CD:EF:12:34:56:78:90:AB:CD:EF:12:34:56:78:90:AB:CD:EF:12
    - Copy the SHA-1 value.

4. **Get WEB_CLIENT_ID**:
    - Login to [Google Cloud Console](https://console.cloud.google.com/) using your same Google account.
    - Navigate to APIs & Services > Credentials.
    - See OAuth 2.0 Client IDs - Click Web client if present and Copy Client ID
    - Otherwise, Create a new OAuth 2.0 Client ID from CREATE CREDENTIALS -> OAuth 2.0 Client ID
    - Select Web Application.
    - Configure the Client ID and Client Secret.
    - Click Create.
    - Copy the Client ID
---

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/iss-tracker-app.git
   cd iss-tracker-app
