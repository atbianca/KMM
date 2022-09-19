package com.example.kotlinmultiplatform

class Greeting {
    private val platform: Platform = getPlatform()

        fun greeting(): String {
            return "There are only 122 left until New Year! 🎅🏼 "
        }

}