package ca.tetervak.diceroller.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.diceroller.R

@Composable
fun TotalRow(@StringRes labelRes: Int, total: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(labelRes),
            fontSize = 34.sp,
            modifier = Modifier
                .fillMaxWidth(0.7f).wrapContentWidth(align = Alignment.End)
        )
        Text(
            text = total.toString(), fontSize = 34.sp, color = colorResource(R.color.green_500)
        )
    }
}