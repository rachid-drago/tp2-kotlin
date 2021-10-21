package fr.mbds.neighbors.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.mbds.neighbors.NavigationListener
import fr.mbds.neighbors.R
import fr.mbds.neighbors.adapters.ListNeighborsAdapter
import fr.mbds.neighbors.data.NeighborRepository
import fr.mbds.neighbors.data.service.ListNeighborHandler
import fr.mbds.neighbors.models.Neighbor

class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        button = view.findViewById(R.id.floating_action_button)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.title1)
        }

        button.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighbourFragment())
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshData()
    }

    fun refreshData() {
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(recyclerView.context)
        builder.setTitle("Attention")
        builder.setMessage("Are u sure ?")
        builder.setPositiveButton("Oui") { dialog, which ->
            NeighborRepository.getInstance().delNeighbour(neighbor)
            refreshData()
        }
        builder.setNegativeButton("Non") { dialog, which -> }
        builder.show()
    }
}
