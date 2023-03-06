package com.mrq.steps.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
abstract class StepAdapter<T>(
    private val context: Context,
    private var layoutResStep: Int,
    private var layoutResStepDone: Int,
    var currentStep: Int = 0,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_STEP = 1
    private val TYPE_DONE = 0

    private var data = emptyList<T>()

    fun setData(newList: ArrayList<T>) {
        data = newList
        notifyDataSetChanged()
    }

    fun getData(): List<T> {
        return data
    }

    fun updateCurrentStep(newCurrentStep: Int = 1) {
        currentStep = newCurrentStep
        notifyDataSetChanged()
    }

    class StepViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    class StepDoneViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewDataBinding
        return when (viewType) {
            TYPE_STEP -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    layoutResStep,
                    parent,
                    false
                )
                StepViewHolder(binding)
            }
            else -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    layoutResStepDone,
                    parent,
                    false
                )
                StepDoneViewHolder(binding)
            }

        }
    }

    abstract fun onBindStepHolder(holder: ViewDataBinding, model: T, position: Int)

    abstract fun onBindDoneHolder(holder: ViewDataBinding, model: T, position: Int)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = data[position]
        when (holder) {
            is StepViewHolder -> {
                onBindStepHolder(holder.binding, model, position)
            }
            is StepDoneViewHolder -> {
                onBindDoneHolder(holder.binding, model, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > currentStep) TYPE_STEP else TYPE_DONE
    }

    override fun getItemCount(): Int = data.size

}