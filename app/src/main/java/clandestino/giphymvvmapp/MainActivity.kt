package clandestino.giphymvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import clandestino.giphymvvmapp.databinding.ActivityMainBinding
import clandestino.giphymvvmapp.network.GiphyService
import clandestino.giphymvvmapp.ui.binding.AppDataBindingComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
    }
}