package com.episi.androidapp_s12_calculadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.episi.androidapp_s12_calculadora.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by activityViewModels {
        CalculatorViewModelFactory(requireContext())
    }

    // ✅ Ya no depende de onAttach ni de MainActivity
    private val onItemSelected: (String) -> Unit = { operation ->
        viewModel.selectHistoryItem(operation)
        requireActivity().findViewById<View>(R.id.fragmentContainer).visibility = View.GONE
        parentFragmentManager.popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.historyRecycler.layoutManager = LinearLayoutManager(requireContext())

        // ✅ Usa el onItemSelected definido arriba (no nulo)
        viewModel.history.observe(viewLifecycleOwner) { operations ->
            binding.historyRecycler.adapter = HistoryAdapter(operations, onItemSelected)
        }

        viewModel.loadHistory()

        binding.btnClearHistory.setOnClickListener {
            viewModel.clearHistory()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
