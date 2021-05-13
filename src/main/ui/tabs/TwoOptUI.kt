package main.ui.tabs

import main.algorithms.TwoOptThread
import main.ui.Main

class TwoOptUI {
    companion object {

        private var twoOptLineChart = Main.getInstance().controller.twoOptLineChart
        private var algorithmThread = TwoOptThread(twoOptLineChart)

        fun startAlgorithm() {

            if (algorithmThread.state == Thread.State.TIMED_WAITING) {
                algorithmThread.interrupt()
            } else {
                algorithmThread = TwoOptThread(twoOptLineChart)
                algorithmThread.start()
            }
        }
    }
}