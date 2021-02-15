package Characters

import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Ennemy(private val startX: Double,private val startY: Double ): Container() {

    suspend fun create(): Image {
        val bitmap = resourcesVfs["gomba.jpg"].readBitmap()
        val img = image(bitmap).xy(startX, startY)
        img.name("ennemy")
        img.width = 100.0
        img.height =  100.0
        return img
    }
}