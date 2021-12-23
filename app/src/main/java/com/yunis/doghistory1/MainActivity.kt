package com.yunis.doghistory1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil

import com.squareup.picasso.Picasso
import com.yunis.doghistory1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogImageDao())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        viewModel.currentlyDisplayedImage.observe(this,
            {
                val mainImage : ImageView = findViewById(R.id.imageView)
                Picasso.get().load(it.imgSrcUrl).into(mainImage)
            })

        binding.button.setOnClickListener {
            val currentImgUrl = viewModel.currentlyDisplayedImage.value?.imgSrcUrl
            val newDogImage = currentImgUrl?.let { it1 -> DogImageEntity(imageUrl = it1) }

            viewModel.getNewDog()
            if (newDogImage != null) {
                viewModel.addDog(newDogImage)
            }
            viewModel.deleteMostRecentDog()
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }



}
