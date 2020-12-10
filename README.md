# movies
Display the movie list.

## Screen shots
<p align="center">
<img src="/preview/image_01.png" width="24%"/>
<img src="/preview/image_02.png" width="24%"/>
<img src="/preview/image_03.png" width="24%"/>
<img src="/preview/image_04.png" width="24%"/>
</p>

## How to set tmdb api key
Get your [API key](https://www.themoviedb.org) and add in your `local.properties` file.
```xml
tmdb_api_key=YOUR_API_KEY
```

## Tech stack used
- [Kotlin](https://kotlinlang.org/) language
- JetPack
  - [AAC ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
  - [Navigation](https://developer.android.com/guide/navigation)
- Architecture
  - MVVM architecture(View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Retrofit](https://github.com/square/retrofit)
- [Glide](https://github.com/bumptech/glide)
