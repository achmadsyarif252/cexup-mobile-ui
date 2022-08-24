package com.cexup.datum

import com.trian.component.R
import com.trian.domain.models.model.OnBoardingModel

/**
 * Datum Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */

val listOnboard = listOf(
    OnBoardingModel(
        title = R.string.onboarding1,
        text = R.string.onboardingvalue1,
        image = R.drawable.illus_medicalrecord
    ),
    OnBoardingModel(
        title = R.string.onboarding2,
        text = R.string.onboardingvalue2,
        image = R.drawable.ilus_janjitemu
    ),
    OnBoardingModel(
        title = R.string.onboarding3,
        text = R.string.onboardingvalue3,
        image = R.drawable.ilus_healthtracker
    )

)