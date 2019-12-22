package ru.catstack.lightshotgrabber

import java.lang.StringBuilder

class ImageID(imageID: String) {
    private val charIndexesArrayList = ArrayList<Int>()

    override fun toString(): String {
        val result = StringBuilder()
        charIndexesArrayList.forEach {
            result.append(chars[it])
        }
        return result.toString()
    }

    init {
        for (char in imageID) {
            charIndexesArrayList.add(chars.indexOf(char))
        }
    }

    operator fun inc(): ImageID {
        addToIndex(charIndexesArrayList.lastIndex)
        return this
    }

    private fun addToIndex(index: Int) {
        if (index < 0)
            return
        charIndexesArrayList[index] += 1
        if (charIndexesArrayList[index] > chars.lastIndex) {
            charIndexesArrayList[index] = 0
            addToIndex(index - 1)
        }
    }
}