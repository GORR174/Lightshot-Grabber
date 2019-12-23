package ru.catstack.lightshotgrabber

import ru.catstack.lightshotgrabber.network.ImageDownloader
import ru.catstack.lightshotgrabber.network.ImageParser
import java.io.File
import java.util.*

fun main() {
    println("LightShot Grabber $VERSION by CatStack (2019)")
    println("When you upload image to prnt.sc, you will get URL like this: https://prnt.sc/xxxxxx")
    println("Enter Image ID (xxxxxx) here:")
    val folder = File("imagesOut/")
    if (!folder.exists())
        folder.mkdir()

    val imageParser = ImageParser()
    val imageDownloader = ImageDownloader(folder)

    val initialImageID = readLine()!!.replace(" ", "")

    val imageID = ImageID(initialImageID)
    val random = Random()
    var imagesCounter = 0
    do {
        print("($imagesCounter) $imageID - ")

        val imageURL = imageParser.parseImageUrl(imageID.toString())
        if (!imageDownloader.downloadImage(imageURL, imageID.toString())) {
            print("Images are over. Wait a while ant press any key to continue")
            readLine()
        } else {
            println("successful")
            imageID.inc()
            imagesCounter++
        }
        Thread.sleep(100 + random.nextInt(400).toLong())
    } while (imageID.toString() != "000000")
}