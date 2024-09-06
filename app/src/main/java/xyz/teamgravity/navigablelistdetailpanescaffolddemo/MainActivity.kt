package xyz.teamgravity.navigablelistdetailpanescaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.teamgravity.navigablelistdetailpanescaffolddemo.ui.theme.NavigableListDetailPaneScaffoldDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigableListDetailPaneScaffoldDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()

                    NavigableListDetailPaneScaffold(
                        navigator = navigator,
                        listPane = {
                            LazyColumn(
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(100) { index ->
                                    val text = stringResource(id = R.string.your_item, index)
                                    Text(
                                        text = text,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                navigator.navigateTo(
                                                    pane = ListDetailPaneScaffoldRole.Detail,
                                                    content = text
                                                )
                                            }
                                            .padding(16.dp)
                                    )
                                }
                            }
                        },
                        detailPane = {
                            val content = navigator.currentDestination?.content?.toString() ?: stringResource(id = R.string.select_item)

                            AnimatedPane {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.primaryContainer)
                                ) {
                                    Text(
                                        text = content
                                    )
                                    Row {
                                        val option1 = stringResource(id = R.string.option_1)
                                        val option2 = stringResource(id = R.string.option_2)

                                        AssistChip(
                                            onClick = {
                                                navigator.navigateTo(
                                                    pane = ListDetailPaneScaffoldRole.Extra,
                                                    content = option1
                                                )
                                            },
                                            label = {
                                                Text(
                                                    text = option1
                                                )
                                            }
                                        )
                                        Spacer(
                                            modifier = Modifier.width(16.dp)
                                        )
                                        AssistChip(
                                            onClick = {
                                                navigator.navigateTo(
                                                    pane = ListDetailPaneScaffoldRole.Extra,
                                                    content = option2
                                                )
                                            },
                                            label = {
                                                Text(
                                                    text = option2
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        },
                        extraPane = {
                            val content = navigator.currentDestination?.content?.toString() ?: stringResource(id = R.string.select_option)

                            AnimatedPane {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                                ) {
                                    Text(
                                        text = content
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                }
            }
        }
    }
}