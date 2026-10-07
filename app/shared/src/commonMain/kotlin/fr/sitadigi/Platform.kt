package fr.sitadigi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform