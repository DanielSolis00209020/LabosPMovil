package com.solis.dummydictionary.ui.word.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.solis.dummydictionary.DummyDictionaryApplication
import com.solis.dummydictionary.R
import com.solis.dummydictionary.data.model.Word
import com.solis.dummydictionary.databinding.FragmentAddWordBinding
import com.solis.dummydictionary.ui.word.viewmodel.WordViewModel
import com.solis.dummydictionary.ui.ViewModelFactory


class AddWordFragment : Fragment() {
    private val viewModelFactory by lazy {
        val application = requireActivity().application as DummyDictionaryApplication
        ViewModelFactory(application.getDictionaryRepository())
    }
    private val viewModel: WordViewModel by viewModels() {
        viewModelFactory
    }

    private lateinit var binding: FragmentAddWordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addWordBtn.setOnClickListener {
            val word = Word(binding.newWord.text.toString(), binding.newDefinition.text.toString(), false)
            viewModel.addWord(word)
            binding.newWord.text.clear()
            binding.newDefinition.text.clear()

            // Go back to the previous fragment
            val navController = findNavController()
            navController.navigate(R.id.action_addWordFragment_to_wordListFragment)
        }
    }
}