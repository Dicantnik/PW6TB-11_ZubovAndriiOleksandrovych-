package com.example.calc6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calc6.ui.theme.Calc6Theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import kotlin.math.ceil

@Stable
data class ElectricalEquipment(
    var name: String = "",
    var efficiency: String = "",
    var powerFactor: String = "",
    var voltage: String = "",
    var quantity: String = "",
    var nominalPower: String = "",
    var usageCoefficient: String = "",
    var reactivePowerFactor: String = "",
    var totalNominalPower: String = "",
    var current: String = "",
)

@Composable
fun EquipmentForm(equipment: ElectricalEquipment, onUpdate: (ElectricalEquipment) -> Unit) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = equipment.name,
        onValueChange = { onUpdate(equipment.copy(name = it)) },
        label = { Text("Найменування ЕП") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.efficiency,
        onValueChange = { onUpdate(equipment.copy(efficiency = it)) },
        label = { Text("Номінальне значення ККД (ηн)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.powerFactor,
        onValueChange = { onUpdate(equipment.copy(powerFactor = it)) },
        label = { Text("Коефіцієнт потужності (cos φ)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.voltage,
        onValueChange = { onUpdate(equipment.copy(voltage = it)) },
        label = { Text("Напруга навантаження (Uн, кВ)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.quantity,
        onValueChange = { onUpdate(equipment.copy(quantity = it)) },
        label = { Text("Кількість ЕП (n, шт)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.nominalPower,
        onValueChange = { onUpdate(equipment.copy(nominalPower = it)) },
        label = { Text("Номінальна потужність ЕП (Рн, кВт)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.usageCoefficient,
        onValueChange = { onUpdate(equipment.copy(usageCoefficient = it)) },
        label = { Text("Коефіцієнт використання (КВ)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = equipment.reactivePowerFactor,
        onValueChange = { onUpdate(equipment.copy(reactivePowerFactor = it)) },
        label = { Text("Коефіцієнт реактивної потужності (tgφ)") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun CalculatorScreen() {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var equipmentList by remember {
        mutableStateOf(
            listOf(
                ElectricalEquipment(
                    name = "Шліфувальний верстат",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "4",
                    nominalPower = "20",
                    usageCoefficient = "0.15",
                    reactivePowerFactor = "1.33"
                ),
                ElectricalEquipment(
                    name = "Свердлильний верстат",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "2",
                    nominalPower = "14",
                    usageCoefficient = "0.12",
                    reactivePowerFactor = "1"
                ),
                ElectricalEquipment(
                    name = "Фугувальний верстат",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "4",
                    nominalPower = "42",
                    usageCoefficient = "0.15",
                    reactivePowerFactor = "1.33"
                ),
                ElectricalEquipment(
                    name = "Циркулярна пила",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "1",
                    nominalPower = "36",
                    usageCoefficient = "0.3",
                    reactivePowerFactor = "1.52"
                ),
                ElectricalEquipment(
                    name = "Прес",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "1",
                    nominalPower = "20",
                    usageCoefficient = "0.5",
                    reactivePowerFactor = "0.75"
                ),
                ElectricalEquipment(
                    name = "Полірувальний верстат",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "1",
                    nominalPower = "40",
                    usageCoefficient = "0.2",
                    reactivePowerFactor = "1"
                ),
                ElectricalEquipment(
                    name = "Фрезерний верстат",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "2",
                    nominalPower = "32",
                    usageCoefficient = "0.2",
                    reactivePowerFactor = "1"
                ),
                ElectricalEquipment(
                    name = "Вентилятор",
                    efficiency = "0.92",
                    powerFactor = "0.9",
                    voltage = "0.38",
                    quantity = "1",
                    nominalPower = "20",
                    usageCoefficient = "0.65",
                    reactivePowerFactor = "0.75"
                ),
            )
        )
    }

    var loadUtilizationCoefficient by remember { mutableStateOf("1.25") }
    var loadCoefficientGroup2 by remember { mutableStateOf("0.7") }

    var sumProductNominalPowerUsageCoefficient by remember { mutableStateOf(0.0) }

    var groupUtilizationCoefficient by remember { mutableStateOf("") }
    var effectiveEquipmentCount by remember { mutableStateOf("") }

    var totalActivePower by remember { mutableStateOf("") }
    var totalReactivePower by remember { mutableStateOf("") }
    var totalApparentPower by remember { mutableStateOf("") }
    var totalCurrentGroup by remember { mutableStateOf("") }

    var totalActivePower1 by remember { mutableStateOf("") }
    var totalReactivePower1 by remember { mutableStateOf("") }
    var totalApparentPower1 by remember { mutableStateOf("") }
    var totalCurrentGroup1 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Button(
            onClick = { equipmentList = equipmentList + ElectricalEquipment() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Додати ЕП")
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            equipmentList.forEachIndexed { index, equipment ->
                EquipmentForm(
                    equipment = equipment,
                    onUpdate = { updatedEquipment ->
                        equipmentList = equipmentList.toMutableList().apply {
                            this[index] = updatedEquipment
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Button(
            onClick = {
                var sumUsageCoefficientProduct = 0.0
                var sumNominalPowerProduct = 0.0
                var sumNominalPowerSquared = 0.0

                equipmentList.forEach { equipment ->
                    val n = equipment.quantity.toDouble()
                    val Pn = equipment.nominalPower.toDouble()
                    equipment.totalNominalPower = "${n * Pn}"
                    val Ip = equipment.totalNominalPower.toDouble() / (
                            sqrt(3.0) *
                                    equipment.voltage.toDouble() *
                                    equipment.powerFactor.toDouble() *
                                    equipment.efficiency.toDouble()
                            )
                    equipment.current = Ip.toString()

                    sumUsageCoefficientProduct += equipment.totalNominalPower.toDouble() * equipment.usageCoefficient.toDouble()
                    sumNominalPowerProduct += equipment.totalNominalPower.toDouble()
                    sumNominalPowerSquared += n * Pn * Pn
                }

                sumProductNominalPowerUsageCoefficient = sumUsageCoefficientProduct

                val utilizationCoefficient = sumUsageCoefficientProduct / sumNominalPowerProduct
                groupUtilizationCoefficient = utilizationCoefficient.toString()

                val effectiveCount = ceil(sumNominalPowerProduct * sumNominalPowerProduct / sumNominalPowerSquared)
                effectiveEquipmentCount = effectiveCount.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Груповий коефіцієнт використання: $groupUtilizationCoefficient", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ефективна кількість ЕП: $effectiveEquipmentCount", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loadUtilizationCoefficient,
            onValueChange = { loadUtilizationCoefficient = it },
            label = { Text("Розрахунковий коеф активної потужності (Kr)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Button(
            onClick = {
                val utilizationCoefficient = groupUtilizationCoefficient.toDoubleOrNull() ?: 0.0
                val loadCoefficient = loadUtilizationCoefficient.toDoubleOrNull() ?: 0.0
                val referencePower = 20.0 // Задається за варіантом
                val tanPhi = 1.55 // Задається за варіантом
                val voltageLevel = 0.38

                val activePower = loadCoefficient * sumProductNominalPowerUsageCoefficient
                val reactivePower = utilizationCoefficient * referencePower * tanPhi
                val apparentPower = sqrt(activePower * activePower + reactivePower * reactivePower)
                val groupCurrent = activePower / voltageLevel

                totalActivePower = activePower.toString()
                totalReactivePower = reactivePower.toString()
                totalApparentPower = apparentPower.toString()
                totalCurrentGroup = groupCurrent.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити потужність та струм")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове активне навантаження: $totalActivePower", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове реактивне навантаження: $totalReactivePower", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Повна потужність: $totalApparentPower", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Груповий струм: $totalCurrentGroup", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loadCoefficientGroup2,
            onValueChange = { loadCoefficientGroup2 = it },
            label = { Text("Розрахунковий коеф активної потужності (Kr2)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Button(
            onClick = {
                val utilizationCoefficient = loadCoefficientGroup2.toDoubleOrNull() ?: 0.0
                val activePowerBus = utilizationCoefficient * 752.0
                val reactivePowerBus = utilizationCoefficient * 657.0
                val apparentPowerBus = sqrt(activePowerBus * activePowerBus + reactivePowerBus * reactivePowerBus)
                val busCurrent = activePowerBus / 0.38

                totalActivePower1 = activePowerBus.toString()
                totalReactivePower1 = reactivePowerBus.toString()
                totalApparentPower1 = apparentPowerBus.toString()
                totalCurrentGroup1 = busCurrent.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити шини 0,38 кВ")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Активне навантаження шин: $totalActivePower1", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Реактивне навантаження шин: $totalReactivePower1", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Повна потужність шин: $totalApparentPower1", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Струм шин: $totalCurrentGroup1", style = MaterialTheme.typography.bodyLarge)
    }
}