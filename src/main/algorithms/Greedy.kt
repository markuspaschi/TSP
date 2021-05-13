package main.algorithms

import main.tsp_utils.Tour
import main.tsp_utils.TourManager

class Greedy(tourManager: TourManager) {

    private var tour = Tour(tourManager)

    fun generateGreedyPath(): Tour {

        for (i in 0 until tour.getSize()) {
            val fromCity = tour.getCity(i)

            var bestDestinationCity = tour.getCity(i) // will be overridden as soon as we enter next loop
            var bestDistance = Double.MAX_VALUE

            for (z in i + 1 until tour.getSize()) {

                if (fromCity.distanceTo(tour.getCity(z)) < bestDistance) {
                    bestDistance = fromCity.distanceTo(tour.getCity(z))
                    bestDestinationCity = tour.getCity(z)
                }
            }
            tour.setCity(i + 1, bestDestinationCity)
        }

        return tour
    }
}