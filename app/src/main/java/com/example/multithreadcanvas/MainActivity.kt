package com.example.multithreadcanvas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        val arr = Array<Int>(barsView.height / 2) { random.nextInt(BarsView.MAX_VALUE) }
        val arr2 = arr.copyOf()
        val arr3 = arr.copyOf()
        doSorting(0, arr)
        doSorting(1, arr2)
        doSorting(2, arr3)
    }

    fun doSorting(idx: Int, arr: Array<Int>) {
        barsView.setData(idx, arr)
        thread {
            val comp = Comparator<Int> { o1, o2 -> Thread.sleep(1); o2 - o1 }
            val notifier = { arr: Array<Int> ->
                val arrCopy = arr.copyOf()
                runOnUiThread {
                    barsView.setData(idx, arrCopy)
                }
            }

            val sorter = when (idx) {
                0 -> InsertSorter<Int>(comp, notifier)
                1 -> BubbleSorter<Int>(comp, notifier)
                else -> HeapSort<Int>(comp, notifier)
            }
            val a = thread(start = true) {
                sorter.sort(arr)
                runOnUiThread {
                    barsView.setData(idx, arr)
                }
            }
        }
    }
}
