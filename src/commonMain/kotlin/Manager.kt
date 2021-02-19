import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Text
import com.soywiz.korge.view.centerOnStage
import com.soywiz.korge.view.text
import com.soywiz.korio.async.delay

class Manager (private val container : Container){

    enum class GameStatus{
        NOT_STARTED,
        RUNNING,
        FINISHED,
        RESTART
    }

    var isRunning = false
    var status = GameStatus.NOT_STARTED
    var message: Text? = null
    var gameOverMessage: Text? = null

    fun start(){
        isRunning = true
        status = GameStatus.RUNNING


    }
    fun finish(){
        isRunning = false
        status = GameStatus.FINISHED
        if(message == null && gameOverMessage == null) {
            gameOverPrint()
        }


    }
    suspend fun restart(){
        print("igohere")
        removeMessage()
        status = GameStatus.RESTART
        delay(TimeSpan(500.0))
        start()
    }

     fun gameOverPrint() {
        val gameOverContainer = container.text("You loose")
        gameOverContainer.centerOnStage()
        gameOverContainer.y += 50
        val text = container.text("Press Space to restart (not working actually please restart program")
        text.centerOnStage()
        text.y += 100
        container.addChild(text)
        container.addChild(gameOverContainer)
        message = text
        gameOverMessage = gameOverContainer
    }

     fun removeMessage() {
        container.removeChild(gameOverMessage)
        container.removeChild(message)
        message = null
        gameOverMessage = null
    }
}


