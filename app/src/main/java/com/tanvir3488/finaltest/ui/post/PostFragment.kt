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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tanvir3488.finaltest.R
import com.tanvir3488.finaltest.data.api.ApiService
import com.tanvir3488.finaltest.data.dto.response.Post
import com.tanvir3488.finaltest.databinding.DailogBinding
import com.tanvir3488.finaltest.databinding.FragmentPostBinding
import com.tanvir3488.finaltest.ui.post_details.PostDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject



@AndroidEntryPoint
class PostFragment : Fragment() {

    val viewModel by viewModels<PostViewModel>()
    lateinit var binding: FragmentPostBinding
    lateinit var postAdapter: PostAdapter
    lateinit var bottomSheet: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(layoutInflater)

        val navController = findNavController()
        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheet.isDraggable = true
        bottomSheet.state= BottomSheetBehavior.STATE_COLLAPSED
        bottomSheet.peekHeight = 100 // Or any visible height you want when collapsed
        bottomSheet.isHideable = true
        val postDetails = PostDetails()

        postAdapter = PostAdapter{
            postDailog(it)
            val bundle = Bundle().apply {
                putSerializable("post",it)
            }
            navController.navigate(R.id.postDetails,bundle)
            postDetails.arguments = bundle
            navigate(postDetails)
//            lifecycleScope.launch {
//                bottomSheet.state= BottomSheetBehavior.STATE_COLLAPSED
//                delay(200)
//                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
//            }


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


    fun postDailog(post: Post){
        val bottomDialog = BottomSheetDialog(requireContext())
        val binding = DailogBinding.inflate(layoutInflater)
        binding.devicename.text = post.title
        binding.devicename.post {  }
        bottomDialog.setContentView(binding.root)
        bottomDialog.show()
    }


    fun navigate(fragment: Fragment){
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container,fragment)
//            .addToBackStack("fragment")
//            .commit()
    }


}
