package clandestino.giphymvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import clandestino.giphymvvmapp.ui.trending.TrendingListFragment
import clandestino.giphymvvmapp.ui.widgets.AppDataBindingComponent
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private val disposibles = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_frame_layout, TrendingListFragment(), "TrendingListFragment")
            .commit()
    }
}