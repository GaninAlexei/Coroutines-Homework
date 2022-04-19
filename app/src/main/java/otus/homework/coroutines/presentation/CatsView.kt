package otus.homework.coroutines

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import otus.homework.coroutines.presentation.CatsPresenter

class CatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICatsView {

    var presenter : CatsPresenter? = null
    var onClick: (() -> Unit)? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            presenter?.onInitComplete()
            onClick?.invoke()
        }
    }

    override fun populate(arg: Arguments) {
        findViewById<TextView>(R.id.fact_textView).text = arg.fact?.text
        val img = findViewById<ImageView>(R.id.cats_imageView)
        Picasso.get().load(arg.url?.image).into(img)
    }

    override fun showToast(text: String?) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}

interface ICatsView {

    fun populate(arg: Arguments)

    fun showToast(text: String?)
}