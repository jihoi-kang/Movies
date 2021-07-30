import java.text.SimpleDateFormat
import java.util.*

object Configs {
    const val applicationId = "com.jay.movies"

    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = 30

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val tmdbApiKey = "29863c47b79cdbd8c0b24afc4e67ff9c"
    val buildTime = Date().format("yyyy-MM-dd'T'HH:mm'Z'", TimeZone.getTimeZone("UTC"))
    const val archivesBaseName = "movies-$versionName($versionCode)"
}

@Suppress("SimpleDateFormat")
private fun Date.format(format: String, tz: TimeZone) = SimpleDateFormat(format).apply {
    timeZone = tz
}.format(this)