package com.example.finalsubmissionnewbie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var  rvDestination: RecyclerView
    private val list = ArrayList<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvDestination = findViewById(R.id.rv_destination)
        rvDestination.setHasFixedSize(true)

        list.addAll(getListDestinations())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile_page -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListDestinations(): ArrayList<Destination> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDestination = ArrayList<Destination>()
        for (i in dataName.indices) {
            val destination = Destination(dataName[i], dataDescription[i], dataPhoto.getResourceId(i,-1))
            listDestination.add(destination)
        }
        return listDestination
    }

    private fun showRecyclerList() {
        rvDestination.layoutManager = LinearLayoutManager(this)
        val listDestinationAdapter = ListDestinationAdapter(list)
        rvDestination.adapter = listDestinationAdapter

        listDestinationAdapter.setOnItemClickCallback(object : ListDestinationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Destination) {
                showSelectedDestination(data)
            }
        })
    }

    private fun showSelectedDestination(destination: Destination) {
        val moveIntent = Intent(this@MainActivity, DestinationDetailActivity::class.java)
        moveIntent.putExtra(DestinationDetailActivity.EXTRA_DESTINATION, destination)
        startActivity(moveIntent)
    }
}