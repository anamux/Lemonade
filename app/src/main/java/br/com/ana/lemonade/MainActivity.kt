package br.com.ana.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ana.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade() {
    ImageAndText(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ImageAndText(modifier: Modifier = Modifier) {
    var result by rememberSaveable { mutableStateOf(1) }
    var clickRequired by rememberSaveable { mutableStateOf(0) }
    var clickCount by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            when (result) {
                1 -> {
                    result = 2
                    clickRequired = generateRandomClicks()
                }
                2 -> {
                    clickCount++
                    if (clickCount >= clickRequired) {
                        result = 3
                        clickCount = 0
                    }
                }
                3 -> result = 4
                else -> {
                    result = 1
                    clickCount = 0
                }
            }
        }) {
            Image(
                painter = painterResource(
                    when (result) {
                        1 -> R.drawable.lemon_tree
                        2 -> R.drawable.lemon_squeeze
                        3 -> R.drawable.lemon_drink
                        else -> R.drawable.lemon_restart

                    }
                ),
                contentDescription = stringResource(
                    when (result) {
                        1 -> R.string.lemon_tree_content_description
                        2 -> R.string.lemon_content_description
                        3 -> R.string.glass_content_description
                        else -> R.string.empty_glass_content_description
                    }

                )
            )

        }

        Spacer(modifier = Modifier
            .height(16.dp)
            .padding(8.dp))
        Text(
            text = stringResource(
                when (result){
                    1 -> R.string.tap_tree
                    2 -> R.string.tap_to_squeeze
                    3 -> R. string.tap_to_drink
                    else -> R.string.tap_to_start
                }
            ),
            fontSize = 18.sp
        )

    }
}

fun generateRandomClicks(): Int {
    return Random.nextInt(2,5)

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Lemonade()
    }
}