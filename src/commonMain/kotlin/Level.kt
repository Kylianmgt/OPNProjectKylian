import Characters.Ennemy
import Components.Floor
import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addFixedUpdater
import com.soywiz.korge.view.position
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random.Default.nextDouble

class Level (private val game: Manager): Container() {

    val originX = 1300.0
    private var floor: MutableList<Image> = mutableListOf()
    private var ennemies : MutableList<Image> = mutableListOf()
    private val floorY = 600.0
    private var speedFactor: Double = 10.0
    private val speedIncrement = 0.005
    private val xSpacer = 300.0

    suspend fun init() {
        createWorld()
        updater()
    }


    suspend fun createWorld() {
        var floorX = 0.0;
        val y = 500.0
        var ennemyX = originX
        for (i in 0..9) {
            floor.add(Floor(floorX, floorY).init())
            ennemies.add(Ennemy(ennemyX,y).create())
            ennemyX += 1000
            floorX += 225


        }
        floor.forEach {
            addChild(it)
        }
        ennemies.forEach {
            addChild(it)
        }
    }


    fun updater() {
        addFixedUpdater(60.timesPerSecond) {
            if (game.status == Manager.GameStatus.RESTART) {
                //destroyWorld()
                //speedFactor = 10.0
            }
            if (game.isRunning) {
                //speedFactor += speedIncrement
                val floorIterator = this.floor.iterator()
                while (floorIterator.hasNext()) {
                    val floor = floorIterator.next()
                    val x = floor.x - (1 * speedFactor)
                    floor.position(x, floor.y)

                    if ((x + 100 < 0)) {
                        floorIterator.remove()
                        removeChild(floor)
                        addFloorTile()
                    }
                }
                val ennemyIterator = this.ennemies.iterator()
                while(ennemyIterator.hasNext()) {
                    val ennemy = ennemyIterator.next()
                    val x = ennemy.x - (1 * speedFactor)
                    ennemy.position(x, ennemy.y)
                    if(x < 0) {
                        ennemyIterator.remove()
                        removeChild(ennemy)
                        addEnnemy()
                    }
                }
            }

        }
    }
    private fun addEnnemy() {
        if(game.isRunning) {
            GlobalScope.launch {
                val newEnnemy = Ennemy(originX + randomInRange(0.0, xSpacer), 320.0).create()
                addChild(newEnnemy)
                ennemies.add(newEnnemy)
            }
        }
    }
    private fun addFloorTile() {
        if(game.isRunning) {
            GlobalScope.launch {
                val newFloor = Floor(1400.0, floorY).init()
                addChild(newFloor)
                floor.add(newFloor)
            }
        }
    }
    fun randomInRange(min: Double, max:Double): Double {
        return nextDouble() * (max - min) + min
    }
}