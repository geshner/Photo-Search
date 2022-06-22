# Photo Search

This repository contains a sample app to retrieve pictures
from [Pexels API](https://www.pexels.com/api/), based on key word informed by user.

## Usage
1. Get a Pexels API key
2. Clone this repository
3. Create a lib.cpp file in the following directory
    ```
    app/src/main/cpp
    ```
4. Insert the following code in lib.cpp file and replace with your Pexels API key.
   ```objectivec
       #include <jni.h>
       #include <string>
    
       extern "C"
    
       JNIEXPORT jstring JNICALL
       Java_br_com_geshner_photosearch_utils_Keys_pexelsApiKey(JNIEnv *env, jobject /* this */) {
            std::string pexels_key = "Your Pexels API key";
            return env->NewStringUTF(pexels_key.c_str());
       }
   ```
   