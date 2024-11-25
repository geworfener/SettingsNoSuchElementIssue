package com.example.settingsnosuchelementissue

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun test() {
        assertTrue(Greeting().greet().contains("Common"), "Check Common is mentioned")
    }
}