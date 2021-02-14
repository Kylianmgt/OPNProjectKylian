package Characters

import Manager
import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.keys
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing

class Player(val gameManager : Manager) : Container(){


    enum class PlayerStatus{
        RUNNING,
        JUMP_UP,
        JUMP_DOWN,
        DEAD,

    }

    private lateinit var player : Sprite
    private val yLimit = 150.0
    private val initialY = 500.0
    private var status = PlayerStatus.RUNNING

    suspend fun init() {
        player = this.buildPlayer()

        keys {
            down(Key.SPACE) {
                if (gameManager.status == Manager.GameStatus.NOT_STARTED) {
                    gameManager.start()
                }
                if (gameManager.status == Manager.GameStatus.FINISHED) {
                    gameManager.restart()
                } else {
                    val playerY = player.y
                    if (playerY >= 200) {
                        status = PlayerStatus.JUMP_UP

                    }

                }
            }
        }
    }
     suspend fun buildPlayer() : Sprite{
        val image = resourcesVfs["mario_sprite.png"].readBitmap()
        val sprite = sprite(image)
        sprite.xy(40.0, initialY)
         sprite.width = 100.0
         sprite.height =  100.0
        return sprite
    }


    private fun playerManager(startingX : Double, startingY : Double) : Int {
        return 0
    }


}


