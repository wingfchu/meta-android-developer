package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R

@Composable
fun Profile(navController: NavHostController){
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)

    )
    Column() {
        Text(text = "Profile Information:")
        Text(text = "First Name")
        Text(text = "Last Name")
        Text(text = "Email")
    }
    Button(onClick = {
            clearUserData(context)
            navController.navigate("Onboarding")
        }
    ) {
        Text(text = "Log out")
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}

private fun clearUserData(context: Context){
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPreferences.edit().clear().apply()

}
