package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.component.common.AvatarCexup
import com.cexup.ui.corporate.screen.DataDoctorActive
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardDoctors(
    modifier: Modifier = Modifier,
    dataDoctor: DataDoctorActive,
    onSelectDoctor: (userCode: String) -> Unit = {},
) {
    val ctx = LocalConfiguration.current
    Card(
        shape = RoundedCornerShape(16.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim
    ) {
        Column(
            modifier = Modifier
                .height(320.dp.from(ctx))
                .padding(16.dp.from(ctx)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AvatarCexup(
                isWithStatusOnline = true,
                sizeAvatar = 60.dp.from(ctx),
                sizeDummy = 24.dp.from(ctx),
                sizeCircleOnline = 14.dp.from(ctx),
                thumb = dataDoctor.thumb,
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dataDoctor.title,
                    style = MaterialThemeCexup.typography.hh2.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dataDoctor.specialty,
                    style = MaterialThemeCexup.typography.hh4,
                    textAlign = TextAlign.Center,
                    color = MaterialThemeCexup.colors.color.text.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp.from(ctx)),
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp.from(ctx))
                            .offset(y = 2.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Icon Star Rating",
                        tint = MaterialThemeCexup.colors.color.warning.warningMain
                    )
                    Text(
                        text = dataDoctor.rating,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                    Text(
                        text = "(${dataDoctor.countRating})",
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.color.text.textInactive
                    )
                    Spacer(modifier = Modifier.width(4.dp.from(ctx)))
                    Icon(
                        modifier = Modifier.size(18.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_bag_health),
                        contentDescription = "Icon Bag Health",
                        tint = MaterialThemeCexup.colors.palette.neutral.neutral8
                    )
                    Text(
                        text = stringResource(
                            id = R.string.count_years_exp,
                            dataDoctor.experienced
                        ),
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.color.text.textSecondary
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))) {
                Divider(
                    thickness = 1.dp.from(ctx),
                    color = MaterialThemeCexup.colors.color.borderline.borderline1,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSelectDoctor(dataDoctor.userCode) },
                    shape = RoundedCornerShape(4.dp.from(ctx)),
                    colors = ButtonDefaults.buttonColors(
                        MaterialThemeCexup.colors.color.primary.primaryMain
                    ),
                    contentPadding = PaddingValues(vertical = 13.dp.from(ctx))
                ) {
                    Text(
                        text = stringResource(id = R.string.book_appointment),
                        style = MaterialThemeCexup.typography.textButton3.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialThemeCexup.colors.palette.neutral.neutral1
                    )
                }
            }
        }
    }
}