package nux.strive.ui.components.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.window.Dialog
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme

@Composable
fun CustomTaskDialog(
    onInteractionFinished: () -> Unit,
) {
    var nameInput by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onInteractionFinished() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.6f),
            colors = CardDefaults.cardColors(StriveTheme.colors.surface),
            border = BorderStroke(1.dp, StriveTheme.colors.stroke),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
            ) {
                Column {
                    Text(
                        text = "Create a Custom Task",
                        color = StriveTheme.colors.text,
                        style = StriveText.Semibold,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // subject name
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        label = { Text(
                            text = "Task Name",
                            color = StriveTheme.colors.text,
                            style = StriveText.Normal,
                            fontSize = 14.sp
                        ) },
                        placeholder = { Text("Enter your task name here...") },
                        textStyle = StriveText.Normal,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = StriveTheme.colors.text,
                            unfocusedTextColor = StriveTheme.colors.greyText,
                            focusedContainerColor = StriveTheme.colors.bg,
                            unfocusedContainerColor = StriveTheme.colors.surface,

                            focusedBorderColor = StriveTheme.colors.accent,
                            unfocusedBorderColor = StriveTheme.colors.stroke,
                        ),
                        singleLine = true,

                    )
                }
            }
        }
    }
}