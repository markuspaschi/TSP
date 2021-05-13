package main.algorithms

import main.tsp_utils.Tour

class TwoOpt {

    companion object {
        private var improvement = false


        fun switchTwoEdges(tour: Tour, i: Int, k: Int): Tour {

            improvement = true
            val bestDistance = tour.getDistance()

            tour.reverseSubList(i, k)

            if (bestDistance < tour.getDistance()) {
                // reverse switch!
                tour.reverseSubList(i, k)
                improvement = false
            } else if (bestDistance == tour.getDistance())
                improvement = false

            return tour


            /**
             * First idea was to swap two random cities --> worse than used algorithm
             **/

            /*
                improvement = true

                val bestDistance = tour.getDistance()

                val randomCity1 = (Math.random() * tour.getSize()).toInt()
                val randomCity2 = (Math.random() * tour.getSize()).toInt()

                tour.reverseSubList(randomCity1, randomCity2)

                if (bestDistance < tour.getDistance()) {
                    // reverse switch!
                    tour.reverseSubList(randomCity1, randomCity2)
                    improvement = false
                } else if (bestDistance == tour.getDistance())
                    // Same bestDistance. We had no improvement. No need to update UI in other threads :)
                    improvement = false

                return tour
            */
        }

        fun isImproved(): Boolean {
            return improvement
        }

    }
}