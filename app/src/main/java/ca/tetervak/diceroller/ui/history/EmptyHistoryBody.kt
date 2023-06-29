package ca.tetervak.diceroller.ui.history

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.ui.theme.AppTheme

@Composable
fun EmptyHistoryBody(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.empty_history),
            fontSize = 28.sp,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .border(
                    width = 2.dp, color = Color.Gray
                )
                .padding(all = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingBodyPreview(){
    AppTheme {
        EmptyHistoryBody()
    }
}