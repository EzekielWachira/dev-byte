package com.ezzy.devbyte.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ezzy.devbyte.R
import com.ezzy.devbyte.databinding.FragmentDevByteBinding
import com.ezzy.devbyte.domain.models.Video

class DevByteFragment : Fragment() {

    private var _binding: FragmentDevByteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDevByteBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
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
