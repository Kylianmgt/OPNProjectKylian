import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.Container
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

    fun start(){
        isRunning = true
        status = GameStatus.RUNNING


    }
    fun finish(){
        isRunning = false
        status = GameStatus.FINISHED

    }
    suspend fun restart(){
        status = GameStatus.RESTART
        delay(TimeSpan(500.0))
        start()
    }


}
