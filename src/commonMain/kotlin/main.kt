import com.soywiz.klock.seconds
import com.soywiz.korge.*
import com.soywiz.korge.input.mouse
import com.soywiz.korge.scene.Module
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.SizeInt
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing

suspend fun main() = Korge(Korge.Config(module = MainModule))
object MainModule: Module() {
	override val mainScene = MainScene::class
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainScene() }
	}

}







