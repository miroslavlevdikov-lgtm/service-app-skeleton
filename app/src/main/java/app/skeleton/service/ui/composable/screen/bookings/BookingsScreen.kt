package app.skeleton.service.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.skeleton.service.R
import app.skeleton.service.ui.composable.shared.DataBasedContainer
import app.skeleton.service.ui.composable.shared.DataEmptyContent
import app.skeleton.service.ui.state.BookingUiState
import app.skeleton.service.ui.state.DataUiState
import app.skeleton.service.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel()
) {
    val bookingsState by viewModel.bookingsState.collectAsState()

    var canceledBookingNumber by remember { mutableStateOf("") }
    var shouldShowDialog by remember { mutableStateOf(false) }

    BookingsContent(
        bookingsState = bookingsState,
        modifier = modifier,
        onCancelBookingButtonClick = { bookingNumber ->
            canceledBookingNumber = bookingNumber
            shouldShowDialog = true
        }
    )

    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.cancelBooking(canceledBookingNumber)
                    canceledBookingNumber = ""
                    shouldShowDialog = false
                }) {
                    Text("Yes, cancel it")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    canceledBookingNumber = ""
                    shouldShowDialog = false
                }) {
                    Text("No, keep it")
                }
            },
            title = { Text(text = "Cancel Booking") },
            text = {
                Text(
                    text = "Are you sure you want to cancel this booking?",
                    textAlign = TextAlign.Justify,
                )
            },

            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.primary,
            textContentColor = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun BookingsContent(
    bookingsState: DataUiState<List<BookingUiState>>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    Column(modifier = modifier) {

        DataBasedContainer<List<BookingUiState>>(
            dataState = bookingsState,

            dataPopulated = {
                BookingsPopulated(
                    bookings = (bookingsState as DataUiState.Populated).data,
                    onCancelBookingButtonClick = onCancelBookingButtonClick,
                )
            },

            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.bookings_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun BookingsPopulated(
    bookings: List<BookingUiState>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    BookingList(
        bookings = bookings,
        modifier = modifier,
        onCancelBookingButtonClick = onCancelBookingButtonClick,
    )
}

@Composable
fun BookingList(
    bookings: List<BookingUiState>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(bookings) { booking ->
            BookingItem(
                booking = booking,
                onCancelBookingButtonClick = onCancelBookingButtonClick,
            )
        }
    }
}