package com.example.translapptesttask.presentation.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translapptesttask.data.databases.DictionaryDao
import com.example.translapptesttask.data.net.respmodels.TranslatedEntity
import com.example.translapptesttask.databinding.DictItemBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DictionaryRecycleAdapter(
    private val daoDict: DictionaryDao): RecyclerView.Adapter<DictionaryItemHolder>() {
    val disposableHoldersBag = CompositeDisposable()
    var elementCount = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryItemHolder
        =DictionaryItemHolder(
        DictItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    init {
        disposableHoldersBag.add(Single.create<Int> {
                it.onSuccess(daoDict.getWordsCount())
        }   .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            elementCount = it
        }, {
        }))
    }

    override fun onBindViewHolder(holder: DictionaryItemHolder, position: Int) {
        val holderDisposable = Single.create<TranslatedEntity> {
                it.onSuccess(daoDict.findById(position))
        }   .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    holder.onBind(it)
                },{})
        disposableHoldersBag.add(holderDisposable)
    }

    override fun getItemCount(): Int = elementCount
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        disposableHoldersBag.clear()
    }
}