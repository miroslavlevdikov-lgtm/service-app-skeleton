package app.skeleton.service.ui.composable.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.skeleton.service.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    SettingsContent(
        companyName = stringResource(R.string.company_name),
        appVersion = stringResource(R.string.app_version),
        website = stringResource(R.string.customer_support_link),
        modifier = modifier
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
        shadowElevation = 4.dp,
    )
}

@Composable
private fun SettingsContent(
    companyName: String,
    appVersion: String,
    website: String,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    color: Color = MaterialTheme.colorScheme.surface,
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
) {
    Box(contentAlignment = Alignment.Center) {
        Surface(
            modifier = modifier,
            shape = shape,
            color = color,
            tonalElevation = tonalElevation,
            shadowElevation = shadowElevation,
            border = border,
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.settings_screen_company_label),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = companyName,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(vertical = 15.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                )

                Column {
                    Text(
                        text = stringResource(R.string.settings_screen_version_label),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = appVersion,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(vertical = 15.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                )

                Column {
                    Text(
                        text = stringResource(R.string.settings_screen_customer_support_label),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = website,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}