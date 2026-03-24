package com.example.littlelemon.composables

import android.R.attr.text
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R

@Composable
fun Onboarding(navController: NavHostController){
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)

    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Let's get to know you")

        TextField(
            value = firstName,
            onValueChange = { newText -> firstName = newText },
            label = { Text("First Name") },
        )

        TextField(
            value = lastName,
            onValueChange = { newText -> lastName = newText },
            label = { Text("Last Name") },
        )

        TextField(
            value = email,
            onValueChange = { newText -> email = newText },
            label = { Text("Email Address") },
        )
    }

    Button(
        onClick =
            { if (validateInput(context, firstName, lastName, email)){
                Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                navController.navigate("Home")
            } else {
                Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
            }
        }
    ) {
        Text("Register")
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(navController = rememberNavController())
}

private fun validateInput(context: Context, firstName: String, lastName: String, email: String): Boolean{
    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
        return false
    } else {
        saveUserData(context, firstName, lastName, email)
        return true
    }
}

private fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("firstName", firstName)
    editor.putString("lastName", lastName)
    editor.putString("email", email)
    editor.apply()
}
