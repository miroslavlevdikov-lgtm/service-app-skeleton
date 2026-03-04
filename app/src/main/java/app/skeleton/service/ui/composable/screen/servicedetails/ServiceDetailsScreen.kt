package app.skeleton.service.ui.composable.screen.servicedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.skeleton.service.R
import app.skeleton.service.data.model.ServiceModel
import app.skeleton.service.ui.composable.shared.DataBasedContainer
import app.skeleton.service.ui.composable.shared.DataEmptyContent
import app.skeleton.service.ui.state.DataUiState
import app.skeleton.service.ui.viewmodel.ServiceDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServiceDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val serviceState by viewModel.serviceState.collectAsState()

    ServiceDetailsContent(
        serviceState = serviceState,
        modifier = modifier,
        onNavigateToCheckout = onNavigateToCheckout
    )
}

@Composable
private fun ServiceDetailsContent(
    serviceState: DataUiState<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        DataBasedContainer<ServiceModel>(
            dataState = serviceState,

            dataPopulated = {
                ServicesDetailsPopulated(
                    service = (serviceState as DataUiState.Populated).data,
                    onNavigateToCheckout = onNavigateToCheckout,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.services_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ServicesDetailsPopulated(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = service.imageRes),
                contentDescription = stringResource(R.string.service_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(275.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
                Text(
                    text = service.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.price, service.price),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = service.description,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                onClick = { onNavigateToCheckout(service.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ) {
                Text(
                    text = stringResource(R.string.button_book_consultation_text),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}