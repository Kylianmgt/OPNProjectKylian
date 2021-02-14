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


class Coordinates(val x: Double, val y: Double)
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
    private var speedFactor = 10.0

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
        player.addUpdater {
            if(gameManager.isRunning) {
                val coordinates = this@Player.playerManager(this.x, this.y)
                player.position(coordinates.x, coordinates.y)
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


    private fun playerManager(startingX : Double, startingY : Double) : Coordinates {
        val x: Double = startingX
        var y: Double = startingY

        when (status) {
            PlayerStatus.JUMP_UP -> {
                y -= 1 * speedFactor
                if(y <= yLimit) {
                    status = PlayerStatus.JUMP_DOWN
                }
            }

            PlayerStatus.JUMP_DOWN -> {
                y += 1 * speedFactor
                if(y >= initialY) {
                    status = PlayerStatus.RUNNING
                }
            }

        }

        return Coordinates(x,y)
    }


}


