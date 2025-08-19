package com.tanvir3488.finaltest.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tanvir3488.finaltest.R
import com.tanvir3488.finaltest.data.api.ApiService
import com.tanvir3488.finaltest.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject



@AndroidEntryPoint
class PostFragment : Fragment() {

    val viewModel by viewModels<PostViewModel>()
    @Inject
    lateinit var apiService: ApiService
    lateinit var binding: FragmentPostBinding
    lateinit var postAdapter: PostAdapter
    lateinit var bottomSheet: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(layoutInflater)

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheet.isDraggable = true
        bottomSheet.state= BottomSheetBehavior.STATE_COLLAPSED
        bottomSheet.peekHeight = 100 // Or any visible height you want when collapsed
        bottomSheet.isHideable = true
        postAdapter = PostAdapter{
            bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            binding.tvTitle.text=it.title
            binding.tvBody.text=it.body
        }
        binding.post.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            //addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.postList.collect {
                    postAdapter.submitList( it)
                }



            }
        }


        return binding.root
    }


}
