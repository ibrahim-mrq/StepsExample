package com.mrq.steps_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mrq.steps.model.StepType
import com.mrq.steps.ui.adapters.StepAdapter
import com.mrq.steps_example.databinding.ActivityMainBinding
import com.mrq.steps_example.databinding.ItemStepBinding
import com.mrq.steps_example.databinding.ItemStepDoneBinding
import com.mrq.steps_example.models.Step

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StepAdapter<StepType<Step>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        adapter = object : StepAdapter<StepType<Step>>(
            this,
            R.layout.item_step,
            R.layout.item_step_done,
        ) {
            override fun onBindStepHolder(
                holder: ViewDataBinding,
                model: StepType<Step>,
                position: Int
            ) {
                holder as ItemStepBinding
                holder.title.text = model.step.title
                holder.body.text = model.step.body
                Glide.with(this@MainActivity).load(model.step.photo).into(holder.photo)
                if (position == adapter.getData().size - 1) {
                    holder.view.visibility = View.GONE
                } else {
                    holder.view.visibility = View.VISIBLE
                }

                holder.photo.setOnClickListener {
                    adapter.updateCurrentStep(adapter.currentStep + 1)
                }
            }

            override fun onBindDoneHolder(
                holder: ViewDataBinding,
                model: StepType<Step>,
                position: Int
            ) {
                holder as ItemStepDoneBinding
                holder.title.text = model.step.title
                holder.time.text = model.step.time
                if (position == adapter.getData().size - 1) {
                    holder.view.visibility = View.GONE
                } else {
                    holder.view.visibility = View.VISIBLE
                }
                holder.photo.setOnClickListener {
                    adapter.updateCurrentStep(0)
                }
            }
        }
        adapter.setData(stepList())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setHasFixedSize(true)
    }

    private fun stepList(): ArrayList<StepType<Step>> {
        val list = arrayListOf<StepType<Step>>()
        list.add(
            StepType(
                Step(
                    "Step 1",
                    "Body Step 1",
                    "March 6, 2023",
                    "https://www.advotics.com/wp-content/uploads/2022/02/surat-jalan-01-1-4.png",
                    true
                ),
                true
            )
        )
        list.add(
            StepType(
                Step(
                    "Step 2",
                    "Body Step 2",
                    "March 4, 2023",
                    "https://www.advotics.com/wp-content/uploads/2022/02/surat-jalan-01-1-4.png",
                    true
                ),
                true
            )
        )
        list.add(
            StepType(
                Step(
                    "Step 3",
                    "Body Step 3",
                    "March 2, 2023",
                    "https://bpcdn.co/images/2016/05/pre-school-order.png",
                    true
                ),
                true
            )
        )
        list.add(
            StepType(
                Step(
                    "Step 4",
                    "Body Step 4",
                    "March 10, 2023",
                    "https://browntape.com/wp-content/uploads/2017/09/aa.png",
                    false
                ),
                false
            )
        )
        list.add(
            StepType(
                Step(
                    "Step 5",
                    "Body Step 5",
                    "March 16, 2023",
                    "https://i0.wp.com/marketbusinessnews.com/wp-content/uploads/2019/04/Made-to-order-thumbnail-image-23222.jpg?fit=695%2C439&ssl=1",
                    false
                ),
                false
            )
        )
        return list
    }
}