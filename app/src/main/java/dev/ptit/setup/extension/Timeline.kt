package dev.ptit.setup.extension

import dev.ptit.R
import dev.ptit.data.goal.GoalEntity
import dev.ptit.data.substitution.SubstitutionEntity
import dev.ptit.data.yellowcard.YellowCardEntity

data class Timeline(
    val time: Int = 0,
    val side : Int = 0,
    val title : String = "",
    val icon : Int = 0,
)

fun GoalEntity.toTimeline() = Timeline(
    time = time,
    side = side,
    title = "${this.score} ${this.name}",
    icon = R.drawable.icon_goal
)

fun YellowCardEntity.toTimeline() = Timeline(
    time = time,
    side = side,
    title = this.name,
    icon = R.drawable.icon_yellow_card
)

fun SubstitutionEntity.toTimeline() = Timeline(
    time = time,
    side = side,
    title = "In: ${this.inPlayer} Out: ${this.outPlayer}",
    icon = R.drawable.icon_substitution
)