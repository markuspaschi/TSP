package main.helpers

import javafx.application.Platform
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart

class LineChart(lineChart: LineChart<Any, Any>) {

    companion object {
        val drawDelay = 100L
    }

    private var series0 = XYChart.Series<Any, Any>()
    private var series1 = XYChart.Series<Any, Any>()

    init {
        lineChart.data.clear()
        lineChart.data.add(series0)
        lineChart.data.add(series1)
    }

    fun displaySeries0(series: XYChart.Series<Any, Any>) {
        series0.data.addAll(series.data)
    }

    fun clearSeriesData() {
        series0.data.clear()
        series1.data.clear()
    }

    fun displayPathInstant() {

        val tmpSeries = XYChart.Series<Any, Any>()
        tmpSeries.data.addAll(series0.data)
        series0.data.clear()
        series1.data.addAll(tmpSeries.data)

    }

    private var thread: Thread? = null

    fun displayPathDelayed() {

        thread = Thread {
            try {
                while (series0.data.size > 0) {

                    val objectToRemoveAdd = series0.data[0]
                    Platform.runLater {
                        series0.data.remove(objectToRemoveAdd)
                        series1.data.add(objectToRemoveAdd)

                        /*
                        val tempSeries = XYChart.Series<Any, Any>()
                        tempSeries.data.addAll(series0.data)
                        series0.data.clear()
                        series1.data.addAll(tempSeries.data)
                        */

                    }
                    Thread.sleep(drawDelay)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        thread!!.start()

    }

}