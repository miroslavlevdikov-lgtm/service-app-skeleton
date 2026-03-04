package app.skeleton.service.data.repository

import app.skeleton.service.data.datastore.OnboardingDataStoreManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OnboardingRepository(
    private val onboardingDataStoreManager: OnboardingDataStoreManager,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return onboardingDataStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            onboardingDataStoreManager.setOnboardedState(state)
        }
    }
}