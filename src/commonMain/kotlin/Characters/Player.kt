package Characters

import com.soywiz.klock.seconds
import com.soywiz.korge.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing

class Player() : Container(){
    private lateinit var mario : Sprite
    private val yLimit = 150.0
    private val initialY = 300.0


    suspend fun init(){
        mario = this.buildMario()

    }

    private suspend fun buildMario() : Sprite{
        val image = resourcesVfs["mario_sprite.png"].readBitmap()
        val sprite = sprite(image)
        sprite.xy(40.0, initialY)
    }


}


