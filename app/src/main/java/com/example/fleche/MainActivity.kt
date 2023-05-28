package com.example.fleche

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleche.ui.theme.FlecheTheme

class MainActivity : ComponentActivity() {

    private var isBluetoothConnected = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlecheTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp), color = MaterialTheme.colorScheme.background) {
                    Column {
                        var t = LocalContext.current
                        BluetoothPills(isBluetoothConnected = isBluetoothConnected)
                        Text("PROJET SAE 4")
                        FlecheSlider()
                        Button(onClick = {
                                if (isBluetoothConnected.value) {
                                    // TODO : Lancer le jeu
                                } else {
                                    Toast.makeText(t, "Bluetooth non connecté", Toast.LENGTH_SHORT).show()
                                }
                            }) {
                            Text("Commencer")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BluetoothPills(isBluetoothConnected: MutableState<Boolean> = mutableStateOf(false)){
    Row(){
        Switch(checked = isBluetoothConnected.value, onCheckedChange = {
            isBluetoothConnected.value = !isBluetoothConnected.value

        })
        Column(){
            Text("Bluetooth")
            Text(if (isBluetoothConnected.value) "Connected" else "Disconnected")
        }
    }

}

@Composable
fun FlecheSlider(){
    var sliderPosition by remember {
        mutableStateOf(1f)
    }
    Text("Nombre de flèches : ${sliderPosition.toInt()}")
    Slider(value = sliderPosition, onValueChange = {sliderPosition = it}, valueRange = 1F..10F)
}


@Preview
@Composable
fun BluetoothPillsPreview(){
    FlecheTheme {
        BluetoothPills()
    }
}

