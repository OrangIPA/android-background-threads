package id.sch.smktelkommlg.siswa.backgroundthreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import id.sch.smktelkommlg.siswa.backgroundthreads.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingMain.root)

        val btnStart = bindingMain.btnStart
        val tvStatus = bindingMain.tvStatus
        val tvHidden = bindingMain.hiddenTv

        tvHidden.visibility = View.GONE

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        btnStart.setOnClickListener{
            executor.execute{
                try {
                    // Simulate process in background thread
                    for (i in 1..10){
                        Thread.sleep(500)
                        val percentage = i * 10
                        handler.post{
                            // Update the UI
                            if (percentage == 100){
                                tvStatus.text = getString(R.string.task_completed)
                                tvHidden.visibility = View.VISIBLE
                            }else {
                                tvStatus.text = String.format(getString(R.string.compressing), percentage)
                            }
                        }
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}