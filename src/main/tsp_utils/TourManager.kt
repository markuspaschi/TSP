package main.tsp_utils

class TourManager private constructor(){
    var cityList = mutableListOf<City>()

    private object Holder { val INSTANCE = TourManager() }

    companion object {
        val instance: TourManager by lazy { Holder.INSTANCE }
    }

    fun clear(){
        cityList.clear()
    }


    fun addCity(city: City) {
        cityList.add(city)
    }

    fun getCity(index: Int): City {
        return cityList[index]
    }

    fun numberOfCities(): Int {
        return cityList.size
    }

    fun shuffleList(){
        cityList.shuffle()
    }

}