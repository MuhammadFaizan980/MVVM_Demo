package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.model.MyModel
import com.example.mvvmdemo.repository.MyRepository

class MaiActivityViewModel : ViewModel() {
    private var list: MutableLiveData<MutableList<MyModel>> = MutableLiveData()
    private var repo: MyRepository? = null
    fun init() {
        if (list != null) {
            return
        }
        repo = MyRepository.getInstance()
        list = repo!!.getData()
    }

    fun getMessages(): MutableLiveData<MutableList<MyModel>> {
        return list
    }

    fun addDay(day: MyModel) {
        val mList: MutableList<MyModel> = list.value!!
        mList.add(day)
        list.postValue(mList)
    }

}
