package com.mrq.steps.ui.adapters

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object StepOperation {


    class SetupStepView<T>() {
        var currentStep: Int = 1
        lateinit var adapter: StepAdapter<T>
        lateinit var recyclerView: RecyclerView

        lateinit var context: Context
        var layoutResStep: Int = 0
        var layoutResStepDone: Int = 0

        constructor(
            context: Context,
            layoutResStep: Int,
            layoutResStepDone: Int,
        ) : this() {
            this.context = context
            this.layoutResStep = layoutResStep
            this.layoutResStepDone = layoutResStepDone
        }

        fun build() {
            adapter = object : StepAdapter<T>(
                context,
                layoutResStep,
                layoutResStepDone,
                currentStep,
            ) {
                override fun onBindStepHolder(holder: ViewDataBinding, model: T, position: Int) {

                }

                override fun onBindDoneHolder(holder: ViewDataBinding, model: T, position: Int) {

                }
            }

//            adapter.setData(list)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
        }
    }

    private fun <T> initView(
        context: Context,
        layoutResStep: Int,
        layoutResStepDone: Int,
        currentStep: Int,
        list: ArrayList<T>,
    ) {
        val adapter = object : StepAdapter<T>(
            context,
            layoutResStep,
            layoutResStepDone,
            currentStep,
        ) {
            override fun onBindStepHolder(holder: ViewDataBinding, model: T, position: Int) {

            }

            override fun onBindDoneHolder(holder: ViewDataBinding, model: T, position: Int) {

            }
        }
    }

}