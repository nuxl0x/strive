package nux.strive

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform