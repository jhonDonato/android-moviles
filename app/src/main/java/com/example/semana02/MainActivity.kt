package com.example.semana02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semana02.ui.theme.SEMANA02Theme

// Modelo de datos (con tipo)
data class Registro(
    val descripcion: String,
    val monto: Double,
    val fecha: String,
    val tipo: String // "Gasto" o "Cancelado"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SEMANA02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaRegistros()
                }
            }
        }
    }
}

@Composable
fun PantallaRegistros() {
    val registros = listOf(
        Registro("Supermercado", 120.50, "2025-08-25", "Gasto"),
        Registro("Taxi", 9.00, "2025-08-26", "Gasto"),
        Registro("Cena", 30.00, "2025-08-27", "Gasto"),
        Registro("Pago-Préstamo", 10.0, "2025-08-20", "Cancelado"),
        Registro("Pago-Chamba", 300.0, "2025-08-22", "Cancelado")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        registros.forEach { reg ->
            RegistroCard(registro = reg)
        }
    }
}

@Composable
fun RegistroCard(registro: Registro) {
    val colorMonto = if (registro.tipo == "Gasto") Color.Red else Color.Green

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Descripción (nombre)
            Text(
                text = registro.descripcion,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Fila: Fecha (izq) y Monto (der)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = registro.fecha,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "S/ ${"%.2f".format(registro.monto)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorMonto
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPantalla() {
    SEMANA02Theme {
        PantallaRegistros()
    }
}
