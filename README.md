# 🍲 Recipe App

Welcome to the **Recipe App**, a modern, feature-rich Android application designed to help users explore delicious recipes, check supermarket pricing, watch recipe tutorials, and now—**generate dynamic recipes instantly using AI.**

## ✨ Features

- **AI Recipe Generator**: Provide a list of ingredients you currently have, and the app leverages the **OpenAI API** to instantly suggest a custom, detailed recipe for you.
- **Recipe Showcase**: Browse through curated recipes like *Crab Cake Sandwiches*, *Chewy Chocolate Chip Cookies*, and more.
- **Price Comparison**: Quickly check total estimated prices for select recipes across standard retailers like Walmart and Target.
- **Video Tutorials**: Watch embedded recipe creation videos natively within the app.

## 🛠 Tech Stack

- **Platform**: Android (Java & XML)
- **Architecture**: **MVVM (Model-View-ViewModel)** with LiveData for reactive state management.
- **UI Components**: Material 3 Design, including `Exposed Dropdown Menus` and `NestedScrollView`.
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

The project emphasizes separation of concerns by using the **MVVM Pattern**, meaning data management is handled by ViewModels while fragments focus exclusively on the UI:

- `HomeViewModel.java`: Handles selection logic, manages recipe data across navigation, and exposes `LiveData` for reactive UI updates.
- `HomeFragment.java`: Uses Material 3 components and observes the `HomeViewModel` to display recipe details seamlessly and restore state when navigating between tabs.
- `AiRecipeFragment.java`: Manages the concurrent HTTP operations connecting to OpenAI.
- `ItemPriceFragment.java`: Encapsulates pricing analysis and data injection into UI components cleanly.
- `SectionsPagerAdapter.java`: Cleanly marshals the fragment back-stack logic for the `ViewPager`.

---
*Created as part of an Android Development showcase. Contributions, forks, and feedback are always welcome!*
