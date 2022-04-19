package otus.homework.coroutines.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import otus.homework.coroutines.CatsView
import otus.homework.coroutines.R
import otus.homework.coroutines.Result

class MainActivity : AppCompatActivity() {

    private val catsViewModel by viewModels<CatsViewModel>()

//    lateinit var catsPresenter: CatsPresenter

//    private val diContainer = DiContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = layoutInflater.inflate(R.layout.activity_main, null) as CatsView
        setContentView(view)
//        catsViewModel.attachView(view)
        catsViewModel.getFactsAndImages()
        view.onClick = {
            catsViewModel.getFactsAndImages()
        }
        catsViewModel.result.observe(this, {
            when (it) {
                is Result.Success -> view.populate(it.arguments)
                is Result.Error -> view.showToast(it.error)
            }
        })

//        catsPresenter = CatsPresenter(diContainer.factService, diContainer.imageService)
//        view.presenter = catsPresenter
//        catsPresenter.attachView(view)
//        catsPresenter.onInitComplete()
    }

//    override fun onStop() {
//        if (isFinishing) {
//            catsPresenter.detachView()
//            catsViewModel.detachView()
//        }
//        super.onStop()
//    }

}