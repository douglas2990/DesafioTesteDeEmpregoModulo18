package com.douglas2990.aulas.desafiotestedeempregomodulo18

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.aulas.desafiotestedeempregomodulo18.adapter.CatsAdapter
import com.douglas2990.aulas.desafiotestedeempregomodulo18.adapter.ImageCatsAdapter
import com.douglas2990.aulas.desafiotestedeempregomodulo18.databinding.FragmentFirstBinding
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Image
import com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate.CatsScreenState
import com.douglas2990.aulas.desafiotestedeempregomodulo18.screenstate.ImageCatsScreenState2
import com.douglas2990.aulas.desafiotestedeempregomodulo18.viewModel.CatsViewModel
import com.douglas2990.aulas.desafiotestedeempregomodulo18.viewModel.ImageCatsViewModel
import com.douglas2990.aulas.desafiotestedeempregomodulo18.viewModel.ImagesCatsViewModel2

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: CatsViewModel by viewModels()
    private val viewModelImages: ImageCatsViewModel by viewModels()
    private val viewModelImages2: ImagesCatsViewModel2 by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)

        //title_cats()
        //image_cats()
        image_cats2()

            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun title_cats(){
        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is CatsScreenState.Success

                when (state){
                    is CatsScreenState.Loading -> {}
                    is CatsScreenState.Success ->
                        binding.recyclerFirst.adapter = CatsAdapter(state.data)
                    is CatsScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            })
    }


    private fun image_cats(){

        binding.recyclerFirst.layoutManager = GridLayoutManager(
            context,3,RecyclerView.VERTICAL, false
        )

        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is CatsScreenState.Success

                when (state){
                    is CatsScreenState.Loading -> {}
                    is CatsScreenState.Success -> {
                        //binding.recyclerFirst.adapter = CatsAdapter(state.data[0])
                        val listaUrlImagens = ArrayList<Image>()
                        //val listaUrlImagens = ArrayList<state.data>
                        //val listaUrlImagens = state.data.toTypedArray()

                        //binding.textQuantos.text = state.data.size.toString()
                        for (i in 0 ..< state.data.size) {
                            //binding.recyclerFirst.adapter = ImageCatsAdapter(state.data[i].images)
                            for (y in 0 ..< state.data[i].images.size){
                                listaUrlImagens.add(state.data[i].images[y])
                                //binding.recyclerFirst.adapter = ImageCatsAdapter(state.data[i].images[y])
                            }

                        }
                        binding.recyclerFirst.adapter = ImageCatsAdapter(listaUrlImagens)

                    }

                    is CatsScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            })
    }
    private fun image_cats2(){

        binding.recyclerFirst.layoutManager = GridLayoutManager(
            context,3,RecyclerView.VERTICAL, false
        )

        viewModelImages2.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is ImageCatsScreenState2.Success

                when (state){
                    is ImageCatsScreenState2.Loading -> {}
                    is ImageCatsScreenState2.Success -> {
                        binding.recyclerFirst.adapter = ImageCatsAdapter(state.data)
                    }

                    is ImageCatsScreenState2.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            })
    }

}



