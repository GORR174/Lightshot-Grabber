package ru.catstack.lightshotgrabber.network

import org.jsoup.Jsoup
import ru.catstack.lightshotgrabber.userAgents
import java.util.*

class ImageParser {
    private val random = Random()

    fun parseImageUrl(imageID: String): String {
        val document = Jsoup.connect("https://prnt.sc/$imageID")
            .userAgent(userAgents[random.nextInt(userAgents.size)])
            .get()
        return document.getElementsByClass("screenshot-image")[0].attr("src")
    }
}