import Characters.Player
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container

class MainScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val gameManager = Manager(this)
        val player = Player()
        addChild(player)
        player.init()
    }
}
