package com.boycoder

import java.io.File


class TextProcessorV1 {
    fun processText(text: String): List<WordFreq> {
        // 步骤1
        val cleaned = clean(text)
        // 步骤2
        val words = cleaned.split(" ")
        // 步骤3
        val map = getWordCount(words)
        // 步骤4
        val list = sortByFrequency(map)

        return list
        // 简略写法
        // sortByFrequency(getWordCount(clean(text).split(" ")))
    }

    fun sortByFrequency(map: Map<String, Int>): MutableList<WordFreq> {
        val list = mutableListOf<WordFreq>()
        for (entry in map) {
            if (entry.key == "") continue
            val freq = WordFreq(entry.key, entry.value)
            // ①
            list.add(freq)
        }

        // ②
        list.sortByDescending {
            it.frequency
        }
        return list
    }

    fun getWordCount(list: List<String>): Map<String, Int> {
        val map = hashMapOf<String, Int>()

        for (word in list) {
            // ①
            if (word == "") continue
            val trim = word.trim()
            // ②
            val count = map.getOrDefault(trim, 0)
            map[trim] = count + 1
        }
        return map
    }

    fun clean(text: String): String {
        return text.replace("[^A-Za-z]".toRegex(), " ")
            .trim()
    }

    fun processFile(file: File): List<WordFreq> {
        val text = file.readText(Charsets.UTF_8)
        return processText(text)
    }}

data class WordFreq(val word: String, val frequency: Int)
