package otus.homework.coroutines.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import otus.homework.coroutines.*
import java.lang.Exception

class CatsPresenter(
    private val catsService: CatsFactsService,
    private val imageService: CatsImagesService,
) {

    private val catsScope = CoroutineCatsScope()

    private var _catsView: ICatsView? = null

    fun onInitComplete() {
        catsScope.launch(CoroutineExceptionHandler { _, _ ->
            CrashMonitor.trackWarning()
        }) {
            try {
                val catFacts = catsService.getCatFact()
                val catImage = imageService.getCatImage()
                _catsView?.populate(Arguments(catFacts, catImage))
            } catch (e: java.net.SocketTimeoutException) {
                _catsView?.showToast("Не удалось получить ответ от сервером")
            } catch (e: Exception) {
                CrashMonitor.trackWarning()
                _catsView?.showToast(e.toString())
            }
        }

    }

    fun attachView(catsView: ICatsView) {
        _catsView = catsView
    }

    fun detachView() {
        catsScope.cancel()
        _catsView = null
    }

}