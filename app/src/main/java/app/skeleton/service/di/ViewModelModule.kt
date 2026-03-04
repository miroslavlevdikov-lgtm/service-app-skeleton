package app.skeleton.service.di

import app.skeleton.service.ui.viewmodel.BookingViewModel
import app.skeleton.service.ui.viewmodel.CheckoutViewModel
import app.skeleton.service.ui.viewmodel.OnboardingViewModel
import app.skeleton.service.ui.viewmodel.ServiceDetailsViewModel
import app.skeleton.service.ui.viewmodel.ServiceViewModel
import app.skeleton.service.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}