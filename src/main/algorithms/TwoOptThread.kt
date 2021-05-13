package main.algorithms

import javafx.application.Platform
import javafx.scene.chart.LineChart
import main.tsp_utils.Tour
import main.tsp_utils.TourManager
import main.ui.Main

class TwoOptThread(private var lineChart: LineChart<Any, Any>) : Thread() {

    override fun run() {
        try {

            var tour = Tour(TourManager.instance)
            var counter = 0

            for (i in 0..10) {

                for (j in 0..TourManager.instance.numberOfCities())
                    for (k in j + 1..TourManager.instance.numberOfCities()) {

                        counter++
                        if (counter % 10 == 0) {
                            // Save counter --> Cause when UI thread will be updated, counter can be higher already
                            // just for UX experience
                            val savedCounter = counter
                            Platform.runLater {
                                setIteration(savedCounter)
                            }
                        }

                        tour = TwoOpt.switchTwoEdges(tour, j, k)
                        if (TwoOpt.isImproved()) {
                            Platform.runLater {

                                // If the tour is improved --> display new Tour in the UI

                                Main.getInstance().showTour(lineChart, tour, false)
                                setDistance(tour.getDistance())
                                setCurrentOptimumIteration(counter)

                            }

                            Thread.sleep(50)
                        }


                    }
            }

            Platform.runLater { threadFinished() }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun setIteration(iteration: Int) {
        Main.getInstance().controller.twoOptIteration.text = iteration.toString()
    }

    private fun setDistance(distance: Double) {
        Main.getInstance().controller.twoOptDistance.text = distance.toString()
    }

    private fun setCurrentOptimumIteration(iteration: Int) {
        Main.getInstance().controller.twoOptCurrentOptimumIteration.text = iteration.toString()
    }


    private fun threadFinished() {
        Main.getInstance().controller.setStartButton()
    }

    override fun start() {
        super.start()
        Main.getInstance().controller.setStopButton()
    }

    override fun interrupt() {
        super.interrupt()
        Main.getInstance().controller.setStartButton()
    }


}