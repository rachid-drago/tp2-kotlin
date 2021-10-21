package fr.mbds.neighbors.data.service

import fr.mbds.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}