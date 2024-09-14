package dizzcode.com.topics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dizzcode.com.topics.data.DataSource
import dizzcode.com.topics.model.Topic
import dizzcode.com.topics.ui.theme.TopicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopicsTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicsApp(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            top = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_small)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopicsApp(modifier: Modifier = Modifier) {
    TopicGrid()
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(DataSource.topics){topic ->
            TopicCard(topic = topic)
        }
    }
}


@Composable
fun TopicCard(
    topic: Topic,
    modifier: Modifier = Modifier
){
    Card{
        Row{
            Box{
                Image(
                    painter = painterResource(id = topic.iconResId),
                    contentDescription = stringResource(id = topic.titleResId),
                    modifier = modifier
                        .width(72.dp)
                        .aspectRatio(0.7f),
                    contentScale = ContentScale.Crop
                )
            }

            Column{
                    Text(
                        text = LocalContext.current.getString(topic.titleResId),
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.padding_medium),
                                top = dimensionResource(id = R.dimen.padding_medium),
                                end = dimensionResource(id = R.dimen.padding_medium),
                                bottom = dimensionResource(id = R.dimen.padding_small)
                                ),
                        style = MaterialTheme.typography.bodyMedium
                    )

                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_medium)
                        )
                    )
                    Text(
                        text = topic.label.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun TopicCardPreview(){
    val topic = DataSource.topics[2]
    TopicCard(topic = topic)
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AppPreview() {
    TopicsTheme {
        TopicsApp()
    }
}
