# 🍲 AI Recipe Assistant App

Welcome to the **Recipe App**, a modern, feature-rich Android application designed to help users explore delicious recipes, check supermarket pricing, watch recipe tutorials, and now—**generate dynamic recipes instantly using AI.**

![Recipe App Interface](https://github.com/user-attachments/assets/bb2118d1-97b4-4dbc-adf8-ec7d5af2236a)

## ✨ Features

- **AI Recipe Generator**: Provide a list of ingredients you currently have, and the app leverages the **OpenAI API** to instantly suggest a custom, detailed recipe for you.
- **Recipe Showcase**: Browse through curated recipes like *Crab Cake Sandwiches*, *Chewy Chocolate Chip Cookies*, and more.
- **Price Comparison**: Quickly check total estimated prices for select recipes across standard retailers like Walmart and Target.
- **Video Tutorials**: Watch embedded recipe creation videos natively within the app.

## 🛠 Tech Stack

- **Platform**: Android (Java & XML)
- **Architecture**: Modular Fragments with a `ViewPager` tab layout.
- **Network / API Integration**: HTTP REST requests for AI interaction utilizing native `HttpURLConnection` and Java Executors for background processing.
- **Build System**: Kotlin DSL (`build.gradle.kts`) with Gradle properties secrets management.

## 🚀 Getting Started

### Prerequisites
- Android Studio / Android SDK (API 34)
- An active OpenAI API Key.

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/Recipe-App.git
   cd Recipe-App
   ```

2. **Configure API Keys and SDK:**
   Open the `/RecipeApp` folder. You will need a `local.properties` file in the root of the app folder. Android Studio usually generates this, but to populate your API key, ensure it looks like this:
   ```properties
   openai.api.key=YOUR_OPENAI_API_KEY_HERE
   ```
   *Note: This repository is configured to keep your API key secure by reading from this local property during build time.*

3. **Build the Project:**
   Open the project in Android Studio, sync your Gradle files, and hit **Run**. Alternatively, you can build from CLI:
   ```bash
   ./gradlew assembleDebug
   ```

## 📂 Project Architecture Showcase

The project emphasizes separation of concerns by using the Fragment-based UI, meaning every core piece of functionality operates smoothly inside the broader `MainActivity` host:
- `AiRecipeFragment.java`: Manages the concurrent HTTP operations connecting to OpenAI.
- `SectionsPagerAdapter.java`: Cleanly marshals the fragment back-stack logic.
- `ItemPriceFragment.java`: Encapsulates string array processing and data injection into UI components cleanly.

---
*Created as part of an Android Development showcase. Contributions, forks, and feedback are always welcome!*
