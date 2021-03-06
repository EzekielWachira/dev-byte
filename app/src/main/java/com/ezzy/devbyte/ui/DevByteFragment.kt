package com.ezzy.devbyte.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezzy.devbyte.R
import com.ezzy.devbyte.adapter.DevByteAdapter
import com.ezzy.devbyte.databinding.FragmentDevByteBinding
import com.ezzy.devbyte.domain.models.Video
import com.ezzy.devbyte.viewmodel.DevByteViewModelFactory
import com.ezzy.devbyte.viewmodel.DevbyteViewModel

class DevByteFragment : Fragment() {

    private var _binding: FragmentDevByteBinding? = null
    private val binding get() = _binding!!

    private var viewModelAdapter: DevByteAdapter? = null

    private val viewModel: DevbyteViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        val devByteViewModelFactory = DevByteViewModelFactory(activity.application)
        ViewModelProvider(
            this, devByteViewModelFactory
        ).get(DevbyteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playList.observe(viewLifecycleOwner, Observer<List<Video>> {
            it?.apply {
                viewModelAdapter?.videos = it
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDevByteBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModelAdapter = DevByteAdapter(VideoClick {
            val packageManager = context?.packageManager ?: return@VideoClick

            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if (intent.resolveActivity(packageManager) == null){
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    private val Video.launchUri : Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

class VideoClick(val block: (Video) -> Unit) {
    /**
     * Called when a video is clicked
     *
     * @param video the video that was clicked
     */
    fun onClick(video: Video) = block(video)
}
