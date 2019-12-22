package ru.catstack.lightshotgrabber.network

import ru.catstack.lightshotgrabber.userAgents
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class ImageDownloader(private val folder: File) {
    private val random = Random()

    fun downloadImage(url: String, imageName: String) {
        val urlConnection = URL(url).openConnection()
        urlConnection.setRequestProperty("User-Agent", userAgents[random.nextInt(
            userAgents.size)])

        urlConnection.getInputStream().use {
            val filePath = Paths.get("${folder.path}/$imageName.png")
            if (!Files.exists(filePath)) {
                Files.copy(it, filePath)
            }
        }
    }
}