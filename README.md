# movies
Display the movie list.

## How to set tmdb api key
Get your [API key](https://www.themoviedb.org) and add in your `local.properties` file.
```xml
tmdb_api_key=YOUR_API_KEY
```

## Tech stack
- [Kotlin](https://kotlinlang.org/)
- JetPack
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Retrofit](https://github.com/square/retrofit)
- [Glide](https://github.com/bumptech/glide)
