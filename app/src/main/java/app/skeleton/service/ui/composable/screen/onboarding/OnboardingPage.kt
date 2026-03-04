package app.skeleton.service.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun OnboardingPage(onboardingContent: OnboardingContent) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imageRef, titleRef, descRef) = createRefs()

        Image(
            painter = painterResource(id = onboardingContent.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(onboardingContent.titleRes),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .constrainAs(titleRef) {
                    top.linkTo(imageRef.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 20.dp)
        )

        Text(
            text = stringResource(onboardingContent.descriptionRes),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .constrainAs(descRef) {
                    top.linkTo(titleRef.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 20.dp)
        )
    }
}