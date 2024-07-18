package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface {
                    ArtSpaceApp()
                }

            }
        }
    }
}

@Composable
fun ArtSpaceApp()
{
    var index by remember {
        mutableStateOf(1)
    }
    var imageId:Int
    var author:String
    var desc:String
    var date:String

    when(index)
    {
        1->{imageId=R.drawable.first
            author= stringResource(id = R.string.first_author)
            desc= stringResource(id = R.string.first_desc)
            date= stringResource(id = R.string.first_date)
        }
        2->{imageId=R.drawable.second
            author= stringResource(id = R.string.second_author)
            desc= stringResource(id = R.string.second_desc)
            date= stringResource(id = R.string.second_date)}
        3->{imageId=R.drawable.third
            author= stringResource(id = R.string.third_author)
            desc= stringResource(id = R.string.third_desc)
            date= stringResource(id = R.string.third_date)}
        4->{imageId=R.drawable.four
            author= stringResource(id = R.string.four_author)
            desc= stringResource(id = R.string.four_desc)
            date= stringResource(id = R.string.four_date)}
        else->{imageId=R.drawable.five
            author= stringResource(id = R.string.five_author)
            desc= stringResource(id = R.string.five_desc)
            date= stringResource(id = R.string.five_date)}
    }

    ArtSpacePage(image = imageId,desc=desc, author = author, publish = date,index=index, onIndexNext = {if(index<5) {index++}},onIndexPrevious={ if(index>1) {index--}})
}
@Composable
fun ArtSpacePage(modifier: Modifier=Modifier,@DrawableRes image:Int,desc:String,author:String,publish:String,index:Int,onIndexNext:()->Unit,onIndexPrevious:()->Unit)
{

    Column(modifier= modifier
        .fillMaxSize()
        .fillMaxWidth()
        .padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
        Spacer(modifier = modifier.height(32.dp))
        Surface (modifier=modifier.border(1.dp, color = Color(0x40AEA3A9)), shadowElevation = 10.dp){
            Image(painter = painterResource(id =image), contentDescription = desc, contentScale = ContentScale.FillWidth, modifier = modifier
                .width(420.dp)
                .height(420.dp)
                .padding(horizontal = 8.dp))
        }
        Surface(tonalElevation = 10.dp) {
            Column (modifier = modifier
                .padding(12.dp).fillMaxWidth().height(150.dp)){
                Text(text = desc, fontSize = 20.sp, modifier = modifier.padding(top = 28.dp))
                Row(modifier = modifier.padding(bottom = 28.dp, top = 12.dp)) {
                    Text(text = author, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = modifier.width(8.dp))
                    Text(text = "("+publish+")", fontSize = 20.sp)
                }
            }
        }

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 52.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { onIndexPrevious() },modifier=modifier.padding(horizontal = 8.dp), enabled = index>1) {
                Text(text = stringResource(id = R.string.previous))
            }
            Button(onClick = { onIndexNext() },modifier=modifier.padding(horizontal = 8.dp), enabled = index<5) {
                Text(text = stringResource(id = R.string.next) )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}