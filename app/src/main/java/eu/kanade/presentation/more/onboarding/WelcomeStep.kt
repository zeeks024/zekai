package eu.kanade.presentation.more.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.kanade.presentation.more.LogoHeader
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.material.padding
import tachiyomi.presentation.core.i18n.stringResource

internal class WelcomeStep : OnboardingStep {

    override val isComplete: Boolean = true

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small),
        ) {
            LogoHeader(iconPadding = PaddingValues(vertical = 8.dp))

            Text("•  ${stringResource(MR.strings.onboarding_welcome_point_privacy)}")
            Text("•  ${stringResource(MR.strings.onboarding_welcome_point_sources)}")
            Text("•  ${stringResource(MR.strings.onboarding_welcome_point_fork)}")
        }
    }
}
