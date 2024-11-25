package com.example.settingsnosuchelementissue

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform