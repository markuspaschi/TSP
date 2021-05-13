package main.tsp_utils

import java.util.*
import kotlin.math.max
import kotlin.math.min

class Tour() {

    constructor(tourManager: TourManager) : this() {
        for (i in 0 until tourManager.numberOfCities()) {
            tour.add(tourManager.getCity(i))
        }
    }

    var tour = mutableListOf<City>()
    private var distance = 0.0

    init {
        // Generate a random Tour in the beginning

    }

    constructor(predefinedTour: MutableList<City>) : this() {
        // Create a tour from a mutable List
        for (i in 0 until predefinedTour.size) {
            tour.add(predefinedTour.get(i))
        }
    }

    fun setCity(index: Int, city: City) {
        //have to remove it first, cause it is causing an empty slot
        val indexBefore = tour.indexOf(city)
        tour.add(index, city)
        tour.removeAt(indexBefore + 1)
        distance = 0.0
    }

    fun getCity(index: Int): City {
        return tour[index]
    }

    fun swapCities(index1: Int, index2: Int) {
        Collections.swap(tour, index1, index2)
        distance = 0.0
    }

    fun reverseSubList(index1: Int, index2: Int) {

        distance = 0.0

        val startIndex = min(index1, index2)
        val endIndex = max(index1, index2)


        val start = tour.subList(0, startIndex)
        var middle = tour.subList(startIndex, endIndex)
        val end = tour.subList(endIndex, tour.size)

        // reverse the middle sublist (between index1 and index2)
        middle = middle.asReversed()

        // Add em up again
        val newTour = mutableListOf<City>()

        newTour.addAll(start)
        newTour.addAll(middle)
        newTour.addAll(end)

        tour = newTour
    }

    fun subTour(index1: Int, index2: Int): MutableList<City> {

        val startIndex = min(index1, index2)
        val endIndex = max(index1, index2)

        val subList = tour.subList(startIndex, endIndex)

        return tour.subList(startIndex, endIndex)

    }

    fun getDistance(): Double {
        if (distance == 0.0) {
            var tourDistance = 0.0

            for (i in 0 until tour.size) {
                val fromCity = getCity(i)

                val destinationCity: City = if (i + 1 < tour.size) {
                    getCity(i + 1)
                } else {
                    getCity(0)
                }
                tourDistance += fromCity.distanceTo(destinationCity)
            }

            distance = tourDistance
        }

        return distance
    }

    fun getSize(): Int {
        return tour.size
    }
}