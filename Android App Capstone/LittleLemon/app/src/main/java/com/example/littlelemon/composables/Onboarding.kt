package com.example.littlelemon.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.para_text
import com.example.littlelemon.ui.theme.section_title
import com.example.littlelemon.ui.theme.stroke
import com.example.littlelemon.ui.theme.yellow
import androidx.core.content.edit
import com.example.littlelemon.ui.theme.Shapes

@Composable
fun Onboarding(navController: NavHostController){
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

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

        //Let's get to know you
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(green)
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.welcome_text),
                style = para_text,
                color = cloud,
                fontSize = 25.sp
            )
        }

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
                onValueChange = { newText -> firstName = newText },
                label = { Text(stringResource(id = R.string.caption_first_name)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
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
                onValueChange = { newText -> lastName = newText },
                label = { Text(stringResource(id = R.string.caption_last_name)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
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
                onValueChange = { newText -> email = newText },
                label = { Text(stringResource(id = R.string.caption_email)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        submitForm(context, firstName, lastName, email, navController)
                    }
                )
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                submitForm(context, firstName, lastName, email, navController)
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
            Text(stringResource(R.string.button_register),
                style = section_title)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(navController = rememberNavController())
}

private fun submitForm(context: Context, firstName: String, lastName: String, email: String, navController: NavHostController){
    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
        Toast.makeText(context, R.string.register_unsuccessful, Toast.LENGTH_SHORT).show()
    } else {
        saveUserData(context, firstName, lastName, email)
        navController.navigate("Home")
        Toast.makeText(context, R.string.register_successful, Toast.LENGTH_SHORT).show()
    }
}

private fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPreferences.edit {
        putString("firstName", firstName)
        putString("lastName", lastName)
        putString("email", email)
    }
}
