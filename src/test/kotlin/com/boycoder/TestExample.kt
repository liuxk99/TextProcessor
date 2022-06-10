package com.boycoder

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class TestExample {
    @Test
    fun test() {
        assertEquals(1, 1)
    }

    @Test
    fun testProcessText() {
        val text = "Kotlin is my favorite language. I love Kotlin!"
        val processor = TextProcessorV1()
        val results = processor.processText(text)
        assertEquals(2, results[0].frequency)
    }

    @Test
    fun testProcessText01() {
        val processor = TextProcessorV1()
        processor.processFile(File("./test-text.txt"))
    }
}