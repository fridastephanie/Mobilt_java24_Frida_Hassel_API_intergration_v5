# Dog Breeds App  

This project was developed to practice **Kotlin**, **Fragments**, **Navigation with Backstack**, **API integration**, and creating adaptive layouts for both **portrait and landscape** orientations in Android development. The app displays dog breeds from an external API, allows users to view details, and manage a list of favorite breeds.  

The project was built in **Android Studio**, and tested on a **Pixel 4 emulator** with **API level 33 "Tiramisu", Android 13.0**.  

## Features  

1. **Home Screen**  
   - Entry point with a welcoming layout.  
   - Buttons for navigating to the breed list and the favorites page.  

2. **Breed List**  
   - Fetches dog breed data from an API using **Retrofit** and **Coroutines**.  
   - Displays all breeds in a **RecyclerView**.  

3. **Breed Detail**  
   - Displays detailed information about a selected breed (image, temperament, lifespan, weight, height).  
   - Users can add breeds to their favorites, which are saved locally using **SharedPreferences**/instance storage. 

4. **Favorites Screen**  
   - Shows the list of favorite breeds.  
   - Option to **clear all favorites**, which also clears the **backstack** and navigates back to the Home screen.  

5. **Backstack Navigation**  
   - Navigation between fragments uses the **Navigation Component**.  
   - Back navigation works as expected (e.g., from detail → list → home).  
   - Clearing favorites resets navigation completely.  

## Project Configuration  

- **Language**: Kotlin  
- **Min SDK**: 31  
- **Target SDK**: 36  
- **Compile SDK**: 36  
- **Java/Kotlin Version**: 11  

### Emulator Information  
- **Emulator Used**: Google Pixel 4  
- **API Level**: 33  

## Dependencies  

The project uses the following key libraries:  
- **Navigation Component** (`androidx.navigation.fragment.ktx`, `androidx.navigation.ui.ktx`)  
- **RecyclerView** (`androidx.recyclerview:recyclerview:1.3.1`)  
- **Retrofit & Gson Converter** (`com.squareup.retrofit2`)  
- **Kotlin Coroutines** (`kotlinx-coroutines-core`, `kotlinx-coroutines-android`)  
- **Glide** (for loading images from API)  
- **Material Components** (UI styling)  

Make sure to run **Gradle Sync** after cloning to install dependencies.  

## Installation Guide  

1. **Clone the repository**:  
   ```bash
   git clone https://github.com/fridastephanie/Mobilt_java24_Frida_Hassel_Inlamning3.git

2. Open in Android Studio
Select Open an existing project.

3. Sync Gradle
Android Studio will automatically download required dependencies.

4. Select Emulator or Device
Recommended: Pixel 4 Emulator (API 33) or a physical device with Android 13+.

5. Run the Project
Click the Run button in Android Studio.

## Screenshots
#### Portrait:
<img width="160" height="350" alt="image" src="https://github.com/user-attachments/assets/55c1ca91-b569-4c26-a627-ee8d60335ed5" />
<img width="160" height="350" alt="image" src="https://github.com/user-attachments/assets/faa230ba-a320-466e-ac05-98d1aaa55190" />
<img width="160" height="350" alt="image" src="https://github.com/user-attachments/assets/ab52e419-1ef4-40d8-b7ff-960f6cad3110" />
<img width="160" height="350" alt="image" src="https://github.com/user-attachments/assets/2bd3f094-7509-4c27-96b2-ea5fa219f886" />  

#### Landscape: 

<img width="350" height="160" alt="image" src="https://github.com/user-attachments/assets/aa2fc8e6-34f2-42d2-94e6-0648fb5a7fca" />
<img width="350" height="160" alt="image" src="https://github.com/user-attachments/assets/dc710e56-b029-42f7-a08f-30712d709307" />
<img width="350" height="160" alt="image" src="https://github.com/user-attachments/assets/d6b7c792-0f8f-4d8d-b354-a31a11fb7e18" />
<img width="350" height="160" alt="image" src="https://github.com/user-attachments/assets/542ff52c-3712-495a-b252-31afef086f4b" />

## Troubleshooting

* If Gradle sync fails, ensure you have Android Studio Hedgehog or newer and JDK 11 installed.
