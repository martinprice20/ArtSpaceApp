package com.martinprice20.artspaceapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martinprice20.artspaceapp.ui.theme.ArtSpaceAppTheme
import java.util.*

@Suppress("OPT_IN_IS_NOT_ENABLED")
class MainActivity : ComponentActivity() {

    private lateinit var gallery: List<ArtWork>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gallery = ArtGallery.getGallery()

        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ArtSpaceApp() {

        var currentArtWork: ArtWork by remember { mutableStateOf(gallery[0]) }
        val maxIndex = gallery.size - 1

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.art_space_app),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    ArtImageAndText(currentArtWork)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)

                    ) {
                        Button(onClick = {
                            var currentIndex = gallery.indexOf(currentArtWork)
                            currentArtWork = if (currentIndex > 0) {
                                currentIndex--
                                gallery[currentIndex]
                            } else {
                                gallery[maxIndex]
                            }
                        }) {
                            Text(stringResource(R.string.previous))
                        }
                        Button(onClick = {
                            var currentIndex = gallery.indexOf(currentArtWork)
                            currentArtWork = if (currentIndex < maxIndex) {
                                currentIndex++
                                gallery[currentIndex]
                            } else {
                                gallery[0]
                            }
                        }) {
                            Text(stringResource(R.string.next))
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ArtSpaceAppTheme {
            ArtSpaceApp()
        }
    }

    @Composable
    fun ArtImageAndText(
        artWork: ArtWork
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(getResourceId(baseContext, artWork.resId)),
                contentDescription = artWork.contentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(24.dp)
                    .size(550.dp)
            )
            Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text("Artwork: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)
                Text(artWork.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)
            }
            Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text("Artist: ")
                Text(artWork.artist)
                Text(text = " (" + artWork.date + ")")
            }
        }
    }

    private fun getResourceId(context: Context, name: String): Int {
        var newName = name
        newName = newName.lowercase(Locale.getDefault())
        return context.resources.getIdentifier(newName, "drawable", context.packageName)
    }
}