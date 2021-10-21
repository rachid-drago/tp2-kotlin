package fr.mbds.neighbors.data

import fr.mbds.neighbors.data.service.*
import fr.mbds.neighbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours

    fun delNeighbour(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)
    fun addNeighbour(n: Neighbor) {
        apiService.createNeighbour(n)
    }

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
