package com.example.android2o.lazyrowcoloumn

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android2o.R
import com.example.android2o.uipackage.SpacerValue


/**
 *  mostly use for recyclerview or display list
 *
 *  Column with vertical scroll will load all 500 items at once which is obviously not a good choice
 *
 *
 *  where as
 *  lazycolumn with items will load items only visible in screen let sat 1 to 14 and then on scroll other items will load.
 * */
var TAG = "lazylist "


@Composable
fun ListDemo() {
//    SimpleList()
    LazyList()


}

@Composable
fun LazyList() {
    Column(modifier = Modifier.fillMaxSize()) {
        /**
         *  list with pojo type
         * */
        LazyColumn(
            content = {
                itemsIndexed(getLazyItemPOJOLIST(), itemContent = { index, item ->
                    Log.e("LIST ITEM ", "List_item $index")
                    ItemRaw(index, item)
                })
            },
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .border(2.dp, color = Color.Green, shape = RoundedCornerShape(2.dp))
        )

        SpacerValue(5)
        /**
         *  list with pojo type , frame layout / BOX design raw
         *  implement box concept after learning it
         * */
        LazyColumn(content = {
            itemsIndexed(getLazyItemPOJOLIST(), itemContent = { index, item ->
                ImageItemRaw(index, item)
            })
        })

        /* *
        *  static list with type
        * */
        /*val testList = listOf<String>("1","2","3","4")

        LazyColumn(content = {
            itemsIndexed(testList, itemContent = {
                index, item ->
                TextItem("ITEM WITTH LIST $item")
            })
        })*/

        /**
         * static list
         * */
        /*LazyColumn(content = {
            items(200, itemContent = {
                TextItem(text = "Index $it")
            })

        })*/

    }

}

@Composable
fun ImageItemRaw(index: Int, item: lazyItemPOJO) {
    val context = LocalContext.current
    var isPlusEnabled by remember { mutableStateOf(true) }

    /**
    // main moto is to display text over image with gradient effect as background
     * so in xml we can use framelayout to display one view over another BUT in COMPOSE we use Box for the same
     * XML : need drawable to create gradient effect and set it to BG BUT
     * compose : we can set Brush composable with gradient effect as background to the Box
     * */
    //todo box concept
        Box(modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(0.5f)
            .height(200.dp)) {
            //main BG image
            Image(
                painter = painterResource(R.drawable.test_flower),
                contentDescription = "composer Imagge",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
                    .clip(RoundedCornerShape(5))
                    .padding(5.dp)
            )
            //top end plus button
            FilledIconButton(onClick = {
                Toast.makeText(context,"PLus Icon Clicked ",Toast.LENGTH_LONG).show()
                isPlusEnabled = !isPlusEnabled
            },
                shape = CircleShape,
                modifier = Modifier.size(40.dp)
                    .padding(6.dp)
                    .align(Alignment.TopEnd),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = if (isPlusEnabled) Color.Green else Color.Gray, // Change color based on state
                    contentColor = if (isPlusEnabled) Color.Red else Color.White
                )

            ) {
                Icon(Icons.Default.Add, contentDescription = "content description",Modifier.padding(4.dp))

            }
            //top start image
            OutlinedButton(onClick = {},
                shape = CircleShape,
                modifier = Modifier.size(40.dp)
                    .padding(5.dp),
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(2.dp,Color.Magenta)

            ) {
                Image(painter = painterResource(R.drawable.composer), contentDescription = "plus")

            }
            //blue gradient shadow from bottom
            Box(modifier = Modifier.fillMaxSize().background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent,Color.DarkGray), startY = 300f
                )
            ))

            //here is text with background of gradient effect
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color.Cyan,
                                Color.Blue
                            ), start = Offset.Zero, end = Offset.Infinite
                        )
                    )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.login) + "$index", color = Color.White
                )
            }
        }
    }
@Composable
fun ItemRaw(index: Int, item: lazyItemPOJO) {
    val context = LocalContext.current

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            Toast
                .makeText(context, "Entire Raw Item $index is Clicked", Toast.LENGTH_LONG)
                .show()
        }
    ) {
        Image(
            painter = painterResource(id = item.profileImage),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .scale(0.5f)
        )
        SpacerValue(5)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalAlignment = Alignment.Start
        ) {
            item.firstName?.let { TextItem(it) }
            SpacerValue(5)
            item.lastName?.let { TextItem(it) }
            SpacerValue(5)
            item.middleName?.let { TextItem(it) }
        }

    }


}

@Composable
fun SimpleList() {
    var scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        for (i in 1..500) {
            TextItem(text = "Item $i")
        }
    }
}

@Composable
fun TextItem(text: String) {
    Log.e(TAG, "ItemValue $text")

    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        textAlign = TextAlign.Start,
        fontSize = 16.sp,
        color = Color.Blue
    )
}
