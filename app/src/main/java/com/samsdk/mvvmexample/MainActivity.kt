package com.samsdk.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.samsdk.mvvmexample.adapter.RvAdapter
import com.samsdk.mvvmexample.databinding.ActivityMainBinding
import com.samsdk.mvvmexample.viewmodel.RvViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var rvAdapter: RvAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        rvAdapter = RvAdapter()
        initRecyclerView()
        apiCall()
    }

    private fun initRecyclerView() = binding.recyclerView.apply {
        val itemDecoration = DividerItemDecoration(applicationContext, VERTICAL)
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = rvAdapter
        addItemDecoration(itemDecoration)
    }

    private fun apiCall() {
        val viewModel = ViewModelProvider(this)[RvViewModel::class.java]
        viewModel.getList().observe(this) { list ->
            if (list != null) {
                binding.progressBar.isVisible = false
                rvAdapter.submitList(list.items)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        binding.searchButton.setOnClickListener {
            val query = binding.editQuery.text.toString().trim()
            if (query.isNotEmpty()) {
                binding.progressBar.isVisible = true
                viewModel.makeApiCall(query)
            } else {
                Toast.makeText(this, "Enter Data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}