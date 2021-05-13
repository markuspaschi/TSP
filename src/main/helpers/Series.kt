package main.helpers

import javafx.scene.chart.XYChart
import main.tsp_utils.Tour

class Series(var tour: Tour) {

    private var series0 = XYChart.Series<Any,Any>()

    // Series 0 is used for points only --> set thickness of lines to 0px
    // Series 1 will be drawn on top so it looks like only lines are applied :)

    fun generateSeries(): XYChart.Series<Any, Any> {
        for (i in 0 until tour.getSize())
            series0.data.add(XYChart.Data(tour.getCity(i).x, tour.getCity(i).y))

        //Add the starting city as "final" city
        series0.data.add(XYChart.Data(tour.getCity(0).x, tour.getCity(0).y))

        return series0
    }

}