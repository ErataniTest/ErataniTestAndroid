package com.gunawan.eratani.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding
    private var handlerAnimation    = Handler(Looper.getMainLooper())
    private var beat                = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbAnimation.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.tbAnimation.title = getString(R.string.animation)

        binding.etBeat.setText("")

        binding.tbAnimation.setNavigationOnClickListener {
            finish()
        }

        binding.ibPlay.setOnClickListener {
            val tempBeat = binding.etBeat.text.toString()
            if (tempBeat.isNotBlank() && tempBeat.toInt() > 0) {
                beat = (60 / tempBeat.toInt()) * 1000
                runnable.run()
                Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Jumlah detak per menit masih kosong", Toast.LENGTH_SHORT).show()
            }

        }

        binding.ibStop.setOnClickListener {
            handlerAnimation.removeCallbacks(runnable)
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
        }
    }

    private var runnable = object : Runnable {
        override fun run() {
            binding.ivAnimation1.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000)
                .withEndAction {
                    binding.ivAnimation1.scaleX = 1f
                    binding.ivAnimation1.scaleY = 1f
                    binding.ivAnimation1.alpha = 1f
                }

            binding.ivAnimation2.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(700)
                .withEndAction {
                    binding.ivAnimation2.scaleX = 1f
                    binding.ivAnimation2.scaleY = 1f
                    binding.ivAnimation2.alpha = 1f
                }

            handlerAnimation.postDelayed(this, beat.toLong())
        }
    }
}