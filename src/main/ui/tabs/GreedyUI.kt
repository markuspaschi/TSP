package main.ui.tabs

import main.algorithms.GreedyThread
import main.ui.Main

class GreedyUI {

    companion object {

        private var greedyLineChart = Main.getInstance().controller.greedyLinechart
        private var greedyThread = GreedyThread(greedyLineChart)

        fun startAlgorithm() {

            if (greedyThread.state == Thread.State.TIMED_WAITING) {
                greedyThread.interrupt()
            } else {
                greedyThread = GreedyThread(greedyLineChart)
                greedyThread.start()
            }

            /*
            val tm = TourManager.instance
            val tour = Greedy(tm).generateGreedyPath()

            Main.getInstance().showTour(greedyLineChart, tour, true)
            */

        }
    }
}