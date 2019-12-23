package ru.catstack.lightshotgrabber

import ru.catstack.lightshotgrabber.network.ImageDownloader
import ru.catstack.lightshotgrabber.network.ImageParser
import java.io.File
import java.util.*

fun main() {
    println("LightShot Grabber $VERSION by CatStack (2019)")
    println("When you upload image to prnt.sc, you will get URL like this: https://prnt.sc/xxxxxx")
    println("Enter Image ID (xxxxxx) or image URl here:")
    val folder = File("imagesOut/")
    if (!folder.exists())
        folder.mkdir()

    val imageParser = ImageParser()
    val imageDownloader = ImageDownloader(folder)

    var initialImageID = readLine()!!.replace(" ", "")

    if (initialImageID.lastIndexOf("/") != -1)
        initialImageID = initialImageID.substring(initialImageID.lastIndexOf("/") + 1)

    val imageID = ImageID(initialImageID)
    val random = Random()
    var imagesCounter = 0
    var failedAttempts = 0
    while (failedAttempts < 3) {
        print("($imagesCounter) $imageID - ")
        val imageURL = imageParser.parseImageUrl(imageID.toString())
        if (!imageDownloader.downloadImage(imageURL, imageID.toString())) {
            println("error")
            failedAttempts++
        } else {
            println("successful")
            failedAttempts = 0
        }
        imageID.inc()
        imagesCounter++
        Thread.sleep(100 + random.nextInt(400).toLong())
    }
    println("It seems the pictures are over. Please try again later.")

}