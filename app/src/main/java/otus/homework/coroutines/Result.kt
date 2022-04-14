package otus.homework.coroutines

sealed class Result<out T> {
    data class Success<T>(val arguments: T) : Result<T>()
    data class Error(val error: String?) : Result<Nothing>()
}