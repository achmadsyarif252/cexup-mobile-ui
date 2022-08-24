package com.cexup.datum

import com.trian.component.R
import com.trian.domain.models.model.SportItem
import com.trian.domain.models.model.TypeSport


val listSport = listOf(
    SportItem(
        icon = R.drawable.ic_vectorrun,
        name = "Run",
        typeSport = TypeSport.RUN
    ),
    SportItem(
        icon = R.drawable.ic_cycling,
        name = "Cycling",
        typeSport = TypeSport.CYCLING
    ),
    SportItem(
        icon = R.drawable.ic_fitness,
        name = "Fitness",
        typeSport = TypeSport.FITNESS
    ),
    SportItem(
        icon = R.drawable.ic_groupskip,
        name = "Skip",
        typeSport = TypeSport.SKIP
    ),
    SportItem(
        icon = R.drawable.mdi_badmintonbtm,
        name = "BTM",
        typeSport = TypeSport.BMT
    ),
    SportItem(
        icon = R.drawable.ic_pingpong,
        name = "Pingpo",
        typeSport = TypeSport.PINGPONG
    ),
    SportItem(
        icon = R.drawable.mdi_basketball,
        name = "Basket",
        typeSport = TypeSport.BASKET
    )
)