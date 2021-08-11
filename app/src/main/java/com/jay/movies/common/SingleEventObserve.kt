package com.jay.movies.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@MainThread
inline fun <T> LiveData<Event<T>>.eventObserve(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit,
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { event ->
        event.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}