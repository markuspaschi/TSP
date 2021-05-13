package main.algorithms

import javafx.application.Platform
import javafx.scene.chart.LineChart
import main.tsp_utils.Tour
import main.tsp_utils.TourManager
import main.ui.Main

class GreedyThread(private var lineChart: LineChart<Any, Any>) : Thread() {

    private var tour = Tour(TourManager.instance)

    override fun run() {
        try {

            var counter = 0

            for (i in 0 until tour.getSize()) {
                val fromCity = tour.getCity(i)

                var bestDestinationCity = tour.getCity(i) // will be overridden as soon as we enter next loop
                var bestDistance = Double.MAX_VALUE

                for (z in i + 1 until tour.getSize()) {


                    counter++
                    Platform.runLater {
                        setIteration(counter)
                    }

                    if (fromCity.distanceTo(tour.getCity(z)) < bestDistance) {
                        bestDistance = fromCity.distanceTo(tour.getCity(z))
                        bestDestinationCity = tour.getCity(z)

                    }
                }
                tour.setCity(i + 1, bestDestinationCity)

            }

            Platform.runLater {
                Main.getInstance().showTour(lineChart, tour, true)
                setDistance(tour.getDistance())
            }

            // Keep the thread alive as long as we are drawing --> javafx glitches if thread is killed
            Thread.sleep((tour.getSize() * main.helpers.LineChart.drawDelay * 1.1).toLong())

            Platform.runLater { threadFinished() }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun setIteration(iteration: Int) {
        Main.getInstance().controller.greedyIteration.text = iteration.toString()
    }

    private fun setDistance(distance: Double) {
        Main.getInstance().controller.greedyDistance.text = distance.toString()
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