package app.skeleton.service.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.skeleton.service.R
import app.skeleton.service.ui.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int
)

private val onboardingPagesContent = listOf<OnboardingContent>(
    OnboardingContent(
        titleRes = R.string.page_1_title,
        descriptionRes = R.string.page_1_description,
        imageRes = R.drawable.ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.page_2_title,
        descriptionRes = R.string.page_2_description,
        imageRes = R.drawable.ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.page_2_title,
        descriptionRes = R.string.page_2_description,
        imageRes = R.drawable.ic_launcher_background,
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    OnboardingScreenContent(
        modifier = modifier,
        onOnboardingComplete = viewModel::setOnboarded,
    )
}

@Composable
private fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    onOnboardingComplete: () -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = { onboardingPagesContent.size }
    )
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) { page ->
            OnboardingPage(
                onboardingContent = onboardingPagesContent[page]
            )
        }

        ControlPanel(
            isLastPage = pagerState.currentPage == pagerState.pageCount - 1,
            onNextClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                } else {
                    onOnboardingComplete()
                }
            },
            onSkipClick = onOnboardingComplete
        )
    }
}

@Composable
private fun ControlPanel(
    isLastPage: Boolean,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 30.dp)
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = onSkipClick,
        ) {
            Text(
                text = stringResource(R.string.skip_button_title),
                fontSize = 20.sp,
            )
        }

        Button(
            onClick = onNextClick,
        ) {
            Text(
                text = if (isLastPage)
                    stringResource(R.string.start_button_title)
                else
                    stringResource(R.string.next_button_title),
                fontSize = 20.sp,
            )
        }
    }
}