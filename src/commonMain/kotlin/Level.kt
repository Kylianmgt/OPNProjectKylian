import Components.Floor
import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addFixedUpdater
import com.soywiz.korge.view.position
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Level (private val game: Manager): Container() {

    val originX = 1300.0
    private var floor: MutableList<Image> = mutableListOf()
    private val floorY = 600.0
    private var speedFactor: Double = 10.0
    private val speedIncrement = 0.005

    suspend fun init() {
        createWorld()
        updater()
    }


    suspend fun createWorld() {
        var floorX = 0.0;

        for (i in 0..9) {
            floor.add(Floor(floorX, floorY).init())
            floorX += 225


        }
        floor.forEach {
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
}