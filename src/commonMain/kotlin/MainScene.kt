import Characters.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container

class MainScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val gameManager = Manager(this)
        val level = Level(gameManager)
        addChild(level)
        level.init()
        val player = Player(gameManager)
        addChild(player)
        player.init()
    }
}
