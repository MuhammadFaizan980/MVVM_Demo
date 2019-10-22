package com.example.mvvmdemo.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.model.MyModel

class MyRepository {
    private var dataSet: MutableLiveData<MutableList<MyModel>> = MutableLiveData()
    private var list: ArrayList<MyModel> = ArrayList()

    companion object {
        private var instance: MyRepository? = null
        fun getInstance(): MyRepository {
            if (instance == null) {
                instance = MyRepository()
            }
            return instance!!
        }
    }

    fun getData(): MutableLiveData<MutableList<MyModel>> {
        if (list.isEmpty()) {
            addValues()
        }
        dataSet.postValue(list)
        return dataSet
    }

    private fun addValues() {
        val obj1 = MyModel()
        obj1.message = "Sunday"
        val obj2 = MyModel()
        obj2.message = "Monday"
        val obj3 = MyModel()
        obj3.message = "Tuesday"
        list.add(obj1)
        list.add(obj2)
        list.add(obj3)
    }

}