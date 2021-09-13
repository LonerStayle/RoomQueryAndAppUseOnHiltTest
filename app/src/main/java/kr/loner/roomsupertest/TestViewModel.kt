package kr.loner.roomsupertest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    app:Application,
    private val database: AppDatabase
):AndroidViewModel(app) {
        private var _listData =  MutableLiveData<List<User>>()
        val listData: LiveData<List<User>>
            get() = _listData


     fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _listData.postValue(database.userDao().getAll())
        }
    }

    fun insertData(list:List<User>){
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().insertListAll(list)
        }
    }
}