package main.tsp_utils

import kotlin.math.abs

class City(var x: Int = (Math.random() * 100).toInt(), var y: Int = (Math.random() * 100).toInt()) {

    fun distanceTo(city: City): Double {
        val xDistance = abs(city.x - x)
        val yDistance = abs(city.y - y)

        return Math.sqrt(((xDistance * xDistance) + (yDistance * yDistance)).toDouble())
    }


}