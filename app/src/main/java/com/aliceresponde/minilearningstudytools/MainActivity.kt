package com.aliceresponde.minilearningstudytools

import android.os.Bundle
import android.preference.PreferenceActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliceresponde.minilearningstudytools.domain.model.CourseClip
import com.aliceresponde.minilearningstudytools.ui.theme.CardColor
import com.aliceresponde.minilearningstudytools.ui.theme.DarkGreen
import com.aliceresponde.minilearningstudytools.ui.theme.DarkPurple
import com.aliceresponde.minilearningstudytools.ui.theme.LightGreen
import com.aliceresponde.minilearningstudytools.ui.theme.LightPink
import com.aliceresponde.minilearningstudytools.ui.theme.LightPurple
import com.aliceresponde.minilearningstudytools.ui.theme.MiniLearningStudyToolsTheme
import com.aliceresponde.minilearningstudytools.ui.theme.Montserrat
import com.aliceresponde.minilearningstudytools.ui.theme.PoltawskiNowyFont
import com.aliceresponde.minilearningstudytools.ui.theme.PrimaryText
import com.aliceresponde.minilearningstudytools.ui.theme.SecondaryText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniLearningStudyToolsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
                    val configuration = LocalConfiguration.current
                    val screenWidth = configuration.screenWidthDp
                    val isTabletOrHorizontal = screenWidth > 680
                    val clips = listOf(
                        CourseClip.IconClip(
                            text = "Intermedia",
                            textColor = DarkPurple,
                            bgColor = LightPurple,
                            icon = ImageVector.vectorResource(R.drawable.eclips_icon),
                            "icon"
                        ),
                        CourseClip.TextClip(
                            text = "Science",
                            textColor = DarkGreen,
                            bgColor = LightGreen,
                        ),
                        CourseClip.TextClip(
                            text = "Physics",
                            textColor = DarkGreen,
                            bgColor = LightGreen,
                        ),
                        CourseClip.BorderClip(
                            text = "15 mins",
                            textColor = SecondaryText,
                            bgColor = CardColor,
                            icon = ImageVector.vectorResource(R.drawable.time_icon),
                            contentDescription = "icon",
                            borderColor = SecondaryText,
                        )
                    )
                    CourseScreen(
                        modifier = Modifier
                            .padding(inner)
                            .fillMaxWidth(),
                        isTablet = isTabletOrHorizontal,
                        title = "Physics Crash Course",
                        content = "The Physics Crash Course offers a foundational overview of essential concepts, teaching learners to understand Newton’s three laws of motion, explain the principle of energy conservation, distinguish between kinetic and potential energy with real-world examples, solve basic problems involving force and mass, and apply the concept of momentum in everyday situations.",
                        clips = clips,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun CourseScreen(
    isTablet: Boolean,
    title: String,
    content: String,
    clips: List<CourseClip>,
    topics: List<String> = listOf(
        "Understand Newton's three laws of motion",
        "Explain the principle of energy conservation",
        "Identify real-world examples of kinetic and potential energy",
        "Solve simple physics problems involving force and mass",
        "Apply concepts of momentum in everyday scenarios"
    ),
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(DarkPurple),
        horizontalAlignment = if (isTablet) Alignment.CenterHorizontally else Alignment.Start,
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(CardColor)

        ) {

            stickyHeader { Text(
                text = title,
                fontFamily = PoltawskiNowyFont,
                fontSize = 36.sp,
                lineHeight = 40.sp,

                )  }
            item {
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 28.dp)
                ) {

                    Text(
                        text = content,
                        fontFamily = Montserrat,
                        fontSize = 15.sp,
                        lineHeight = 24.sp,
                        color = SecondaryText
                    )

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)

                    ) {
                        clips.forEach { clip ->
                            when (clip) {
                                is CourseClip.BorderClip -> ClipItem(
                                    text = clip.text,
                                    textColor = clip.textColor,
                                    bgColor = clip.bgColor,
                                    borderColor = clip.borderColor,
                                    icon = clip.icon,
                                    contentDescription = clip.contentDescription
                                )

                                is CourseClip.IconClip -> {
                                    ClipItem(
                                        text = clip.text,
                                        textColor = clip.textColor,
                                        bgColor = clip.bgColor,
                                        icon = clip.icon,
                                        contentDescription = clip.contentDescription
                                    )
                                }

                                is CourseClip.TextClip -> {
                                    ClipItem(
                                        text = clip.text,
                                        textColor = clip.textColor,
                                        bgColor = clip.bgColor,
                                    )
                                }
                            }
                        }
                    }
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                )

                Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                    Text(
                        text = "What youʼll learn:",
                        fontFamily = Montserrat,
                        fontWeight = W500,
                        color = PrimaryText,
                        modifier = Modifier.padding(start = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn {
                        items(topics) { topic ->

                            // Composable
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.arrow),
                                    contentDescription = "icon"
                                )
                                Text(
                                    text = topic,
                                    fontFamily = Montserrat,
                                    fontWeight = W400,
                                    fontSize = 18.sp,
                                    lineHeight = 26.sp
                                )
                            }
                        }
                    } // Topic

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .background(LightPink)
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tag),
                            contentDescription = "icon",
                            modifier = Modifier
                                .size(40.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "Dr. Eleanor Maxwell",
                            fontFamily = Montserrat,
                            fontWeight = W500,
                            color = PrimaryText,
                            lineHeight = 26.sp,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(40.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun ClipItem(
    text: String,
    textColor: Color,
    bgColor: Color,
    icon: ImageVector? = null,
    contentDescription: String = "",
    borderColor: Color? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(10.dp))
            .then(
                if (borderColor != null) {
                    Modifier.border(width = 1.dp, color = borderColor, shape = CircleShape)
                } else Modifier
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))
        }

        Text(
            text = text,
            modifier = Modifier
                .clip(CircleShape),
            color = textColor
        )
    }
}

@Preview
@Composable
fun CourseScreenPrev() {
    CourseScreen(
        isTablet = false,
        modifier = Modifier.fillMaxSize(),
        title = "Physics Crash Course",
        content = "The Physics Crash Course offers a foundational overview of essential concepts, teaching learners to understand Newton’s three laws of motion, explain the principle of energy conservation, distinguish between kinetic and potential energy with real-world examples, solve basic problems involving force and mass, and apply the concept of momentum in everyday situations.",
        clips = listOf<CourseClip>(
            CourseClip.IconClip(
                text = "Intermedia",
                textColor = DarkPurple,
                bgColor = LightPurple,
                icon = ImageVector.vectorResource(R.drawable.eclips_icon),
                "icon"
            ),
            CourseClip.TextClip(
                text = "Science",
                textColor = DarkGreen,
                bgColor = LightGreen,
            ),
            CourseClip.TextClip(
                text = "Physics",
                textColor = DarkGreen,
                bgColor = LightGreen,
            ),
            CourseClip.BorderClip(
                text = "15 mins",
                textColor = SecondaryText,
                bgColor = CardColor,
                icon = ImageVector.vectorResource(R.drawable.time_icon),
                contentDescription = "icon",
                borderColor = SecondaryText,
            )
        )
    )
}
