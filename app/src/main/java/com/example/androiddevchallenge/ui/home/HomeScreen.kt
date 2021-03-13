/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.soothelist
import com.example.androiddevchallenge.ui.components.SootheBottomNavigation
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        bottomBar = {
            SootheBottomNavigation()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
        ) {
            item {
                var keyword: String by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = keyword,
                    onValueChange = { keyword = it },
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .height(64.dp),
                    textStyle = MaterialTheme.typography.body1,
                    placeholder = { Text(text = stringResource(R.string.home_search)) },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    singleLine = true
                )
            }

            item {
                Text(
                    text = stringResource(R.string.home_browse_themes),
                    modifier = Modifier.padding(horizontal = 8.dp)
                        .paddingFromBaseline(top = 32.dp, bottom = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h1
                )
            }

            item {
                LazyRow(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)) {
                    items(soothelist) { itemContent ->
                        HomeBrowseItem(itemContent)
                    }
                }
            }
            item {
                Layout(
                    content = {
                        Text(
                            text = stringResource(R.string.home_garden),
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h1
                        )
                        Icon(
                            Icons.Filled.FilterList,
                            contentDescription = stringResource(R.string.home_filter),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                ) { measurables, constraints ->
                    val paddingPx = 16.dp.roundToPx()
                    val contentWidth = constraints.maxWidth - paddingPx * 2
                    val icon = measurables[1].measure(constraints)
                    val text = measurables[0].measure(
                        constraints.copy(maxWidth = contentWidth - icon.width - 8.dp.roundToPx())
                    )
                    val baseline = text[FirstBaseline]
                    val top = 32.dp.roundToPx() - baseline
                    layout(constraints.maxWidth, 44.dp.roundToPx()) {
                        text.place(paddingPx, top)
                        icon.place(
                            constraints.maxWidth - icon.width - paddingPx,
                            top + (text.height - icon.height) / 2
                        )
                    }
                }
            }

            items(soothelist) { itemContent ->
                HomeThemeItem(itemContent)
            }
        }
    }
}
