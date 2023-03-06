package com.mrq.steps.model

data class StepType<T>(
    var step: T,
    var isDone: Boolean,
)
