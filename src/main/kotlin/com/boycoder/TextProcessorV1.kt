package com.boycoder

import java.io.File


class TextProcessorV1 {
    fun processText(text: String): List<WordFreq> {
        return text.clean()
            .split(" ")
            .getWordCount()
            .mapToList { WordFreq(it.key, it.value) }
            .sortedByDescending { it.frequency }
    }

    private fun <T> Map<String, Int>.mapToList(transform: (Map.Entry<String, Int>) -> T): MutableList<T> {
        val list = mutableListOf<T>()
        for (entry in this) {
            val freq = transform(entry)
            list.add(freq)
        }
        return list
    }

    fun Map<String, Int>.sortByFrequency(): MutableList<WordFreq> {
        val list = mutableListOf<WordFreq>()
        for (entry in this) {
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

    private fun List<String>.getWordCount(): Map<String, Int> {
        val map = HashMap<String, Int>()
        for (element in this) {
            if (element == "") continue
            val trim = element.trim()
            val count = map.getOrDefault(trim, 0)
            map[trim] = count + 1
        }
        return map
    }

    // 转换成扩展函数
    fun String.clean(): String {
        return this.replace("[^A-Za-z]".toRegex(), " ")
            .trim()
    }

    fun processFile(file: File): List<WordFreq> {
        val text = file.readText(Charsets.UTF_8)
        return processText(text)
    }}

data class WordFreq(val word: String, val frequency: Int)
