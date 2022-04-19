package otus.homework.coroutines.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import otus.homework.coroutines.*
import java.lang.Exception

class CatsViewModel : ViewModel() {

    private val _result: MutableLiveData<Result<Arguments>> = MutableLiveData()
    val result: LiveData<Result<Arguments>> = _result

    private val catsService: CatsFactsService = DiContainer().factService
    private val imageService: CatsImagesService = DiContainer().imageService

    fun getFactsAndImages() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            CrashMonitor.trackWarning()
        }) {
            try {
                val catFacts = catsService.getCatFact()
                val catImage = imageService.getCatImage()
                _result.value = Result.Success(
                    Arguments(
                        catFacts,
                        catImage
                    )
                )
            } catch (e: java.net.SocketTimeoutException) {
                _result.value = Result.Error("Не удалось получить ответ от сервером")
            } catch (e: Exception) {
                CrashMonitor.trackWarning()
                _result.value = Result.Error(e.message)
            }
        }

    }

}