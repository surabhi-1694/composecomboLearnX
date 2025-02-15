package com.example.bottomnavigationbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeListScreen(modifier: Modifier, args: ScreenList){
    var dummyList = ArrayList<String>()
    dummyList.add("a")
    dummyList.add("b")
    dummyList.add("c")
    dummyList.add("d")
    dummyList.add("e")
    dummyList.add("f")
    dummyList.add("e")

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)
        .windowInsetsPadding(WindowInsets.statusBars),
    ) {

        Text(text = "Hello ${args.name}")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Hello ${args.age}")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Hello ${args.address}")
        Spacer(modifier = Modifier.height(5.dp))


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(dummyList){ index, item ->
                LazyItemLayout(index,item)

            }
        }
    }
}

@Composable
fun LazyItemLayout(index:Int,item:String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "lazyitem ${index}")
    }
}
