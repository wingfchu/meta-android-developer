package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Shapes
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.para_text
import com.example.littlelemon.ui.theme.section_title
import com.example.littlelemon.ui.theme.stroke
import com.example.littlelemon.ui.theme.yellow
import androidx.core.content.edit

@Composable
fun Profile(navController: NavHostController){
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: "Wendy"
    val lastName = sharedPreferences.getString("lastName", "") ?: "Chu"
    val email = sharedPreferences.getString("email", "") ?: "hello@world.com"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )

        //Personal information
        Text(
            text = stringResource(id = R.string.personal_info),
            style = section_title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 16.dp),
            textAlign = TextAlign.Start,
        )

        //user input

        //first name
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(stringResource(R.string.caption_first_name), style = para_text)
            OutlinedTextField(
                value = firstName,
                onValueChange = { },
                readOnly = true,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        //last name
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(stringResource(R.string.caption_last_name), style = para_text)
            OutlinedTextField(
                value = lastName,
                onValueChange = { },
                label = { Text(stringResource(id = R.string.caption_last_name)) },
                readOnly = true,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        //email
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(stringResource(R.string.caption_email), style = para_text)
            OutlinedTextField(
                value = email,
                onValueChange = {  },
                label = { Text(stringResource(id = R.string.caption_email)) },
                readOnly = true,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                clearUserData(context)
                navController.navigate("Onboarding")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = Shapes.medium,
            border = BorderStroke(1.dp, stroke),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = yellow,
                contentColor = charcoal
            )
        ) {
            Text(stringResource(R.string.button_logout),
                style = section_title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}

private fun clearUserData(context: Context){
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPreferences.edit { clear() }

}
