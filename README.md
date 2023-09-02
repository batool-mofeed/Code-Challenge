Deloitte-Code-Challenge-Android-App 
=============================

## The main used architectures
* MVVM
* Data Repository
* Usecases

## Design Patterns
* Hilt for dependency injection
* Singleton
* Observer (LiveData)
* StateFlow

## Database
* Shared preferences for user session data
* Room database for storing articles (offline mode)

### Used Jetpack Architecture Components
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - Declaratively bind observable data to UI elements.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Notify views when underlying database changes.
* [Room](https://developer.android.com/topic/libraries/architecture/room) - Fluent SQLite database access.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI-related data in a lifecycle-conscious way.
* [Navigation Component](https://developer.android.com/guide/navigation) - Bottom navigation bar.

## 3rd Party Libraries
* [Retrofit](https://square.github.io/retrofit/) - A HTTP client for Android.
* [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - An OkHttp interceptor which logs HTTP request and response data.
* [GSON](https://github.com/google/gson) - A serialization/deserialization library to convert Objects into JSON and back.
* [Mockito](https://site.mockito.org) - Mocking framework for unit tests.

## Building the App

First, clone the repo:

`git clone https://github.com/batool-mofeed/Code-Challenge.git`

Building the app then depends on your build tools.

### Android Studio 

(These instructions were tested with Android Studio version Eel)

* Navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

## Using the App
 
Code Challenge
The app consist of a splash, registration screen,
dashboard and more in bottom navbar (for authenticated only). In addition, it support localization
for English and Arabic.
## Splash
• Handles navigation in the app (authenticated vs unauthenticated)
• Handles navigation after changing language
## Registration
• Form that takes ID – email – phone number – date of birth
• Upon registering user details are stored and persisted
• Make sure validation are implied to the fields and error are handled
## Dashboard
• Register in NY times developer portal (https://developer.nytimes.com/docs/mostpopular- product/1/overview)
• Use the most viewed service (https://api.nytimes.com/svc/mostpopular/v2/viewed/30.json?apikey=)
• Show a section list of data
• Section list is divided by date which will be in relative format (e.g. “1 day ago”)
• Include a search bar that filter the result based on the title
• Make sure to include a swipe down to refresh which reset the data
## More
• Shows the stored user details
• Logout button which clears the session and the user details
The code challenge will be evaluated on the below:
Code quality: Usage of patterns, Language skills, Project structure.
Project support offline of the internet using room database to store articles.

