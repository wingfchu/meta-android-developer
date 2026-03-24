package com.example.littlelemon.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuDatabase
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.*
import kotlin.collections.emptyList


@Composable
fun Home(navController: NavHostController){
    Log.d("HomePage", "Composable start")

    val context = LocalContext.current
    val databaseMenuItems by MenuDatabase.getDatabase(context).menuItemDao().getAll().observeAsState(emptyList())
    //val databaseMenuItems = emptyList<MenuItemRoom>()
    val searchPhrase = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ){
            // Header
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            navController.navigate("Profile")
                        }
                )
            } // end of Header

            //Hero section
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = green)
                    .padding(8.dp),
            ) {
                Column{
                    Text(
                        text = stringResource(R.string.title),
                        style = h1,
                        color = yellow,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .background(color = green)
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Left Column (Location, Description)
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {

                    Text(
                        text = stringResource(R.string.location),
                        style = MaterialTheme.typography.titleLarge,
                        color = cloud,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.bodyLarge,
                        color = cloud,
                        modifier = Modifier
                            .padding(end = 8.dp),
                    )

                }
                // Image Column
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {

                    Image(
                        painter = painterResource(R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            //.fillMaxWidth(0.6f)
                            .size(150.dp)
                            .clip(shape = Shapes.medium),

                        )
                }

            }

            // Search TextField
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = green)
                    .padding(bottom = 16.dp)
            ) {

                TextField(
                    value = searchPhrase.value,
                    onValueChange = { searchPhrase.value = it },
                    placeholder = { R.string.enter_search_phrase },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .clip(shape = Shapes.medium),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    },
                    singleLine = true,
                )
            }
            // end of Hero Section

            //Menu List
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 30.dp)
            ) {
                Text(
                    text = stringResource(R.string.order_delivery).uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = charcoal)
            }

            Spacer(modifier = Modifier.width(16.dp))


            //Filtered MenuItems
            val filteredMenuItems = if  (searchPhrase.value.isBlank()) {
                databaseMenuItems
            } else {
                databaseMenuItems.filter { menuItem ->
                    menuItem.title.contains(searchPhrase.value, ignoreCase = true)
                }
            }

            MenuItemList(menuItemList = filteredMenuItems, context)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(navController = rememberNavController())
}

@Composable
fun MenuItemList(menuItemList: List<MenuItemRoom>, context: Context) {
    Spacer(modifier = Modifier
        .width(20.dp)
        .padding(top = 10.dp, bottom = 10.dp))
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        for (menuItem in menuItemList) {
            MenuItem(item = menuItem, context)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom, context: Context) {
    Spacer(modifier = Modifier.width(10.dp))
    HorizontalDivider(color = cloud, thickness = 1.dp)
    Spacer(modifier = Modifier.width(10.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(text = item.title, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp))
            Text(text = item.description,
                modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "$  ${item.price}")
        }
        Column {
            Spacer(modifier = Modifier.width(10.dp))
            GlideImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier.size(100.dp, 100.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

    }
}