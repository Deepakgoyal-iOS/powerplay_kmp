package com.example.powerplaykmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform