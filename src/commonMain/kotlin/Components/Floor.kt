package Components

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs


class Floor (private val startX: Double, private val startY:Double): Container() {

    suspend fun init(): Image {

        val bitmap = resourcesVfs["minecraft_floor.jpg"].readBitmap()
        return image(bitmap).xy(startX, startY)
    }
}