package kr.loner.roomsupertest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    val viewModel by lazy { ViewModelProvider(this).get(TestViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration().build()
//
        val list = mutableListOf<User>()
        repeat(1000) {
            list.add(User(uid = it, firstName = "first$it", lastName = "last$it", age = it))
        }
//            db.userDao().insertListAll(list)
//            Log.d("checkk:첫 init", db.userDao().getAll().size.toString())

        viewModel.listData.observe(this@MainActivity, {
            Log.d("checkkViewModel:첫 init", it.size.toString())
            CoroutineScope(Dispatchers.IO).launch{
                Log.d("checkk", db.userDao().getAll().size.toString())
            }
        })
        viewModel.insertData(list)
        viewModel.getData()


//            db.userDao().updateUsers(User(3,"first3-1"))
//            Log.d("checkk:업데이트 ID",db.userDao().loadAllByIds(intArrayOf(3)).toString())

//            db.userDao().delete(User(3))
//            Log.d("checkk",db.userDao().getAll().size.toString())

    }
}
