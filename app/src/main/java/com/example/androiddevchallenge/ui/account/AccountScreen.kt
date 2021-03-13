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
package com.example.androiddevchallenge.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.SootheImage
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun AccountScreen(navigateToHome: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        SootheImage(
            imageRes = R.drawable.ic_login,
            modifier = Modifier.fillMaxSize(),
            scale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Text(
                text = stringResource(id = R.string.log_in_title),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(32.dp))

            var email: String by rememberSaveable { mutableStateOf("") }
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .height(56.dp),
                textStyle = MaterialTheme.typography.body1,
                placeholder = { Text(text = stringResource(R.string.email_address)) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            var password: String by rememberSaveable { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .height(56.dp),
                textStyle = MaterialTheme.typography.body1,
                placeholder = { Text(text = stringResource(R.string.password)) },
                singleLine = true
            )

            Button(
                onClick = navigateToHome,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(72.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                ),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Text(text = stringResource(R.string.log_in))
            }

            val notice = buildAnnotatedString {
                val underline = SpanStyle(textDecoration = TextDecoration.Underline)
                append(stringResource(R.string.log_in_notice_1))
                pushStyle(underline)
                append(stringResource(R.string.sign_up))
            }
            Text(
                text = notice,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
