package com.compose.app.presentation.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.app.presentation.components.CustomAppBar
import com.compose.app.presentation.ui.login.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

class AccountPage : Screen {
    @Composable
    @Preview
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<AccountViewModel>()
        val isLogout by viewModel.isLogout.collectAsState()

        val options = listOf("Profile", "My Order", "Customer Support", "About Us", "Contact Us", "Terms & Conditions")

        LaunchedEffect(isLogout) {
            isLogout.let {
                if (it) {
                    navigator.replaceAll(LoginScreen())
                    viewModel.onLogoutSuccess()
                }
            }
        }
        Scaffold(
            topBar = {
                CustomAppBar(
                    title = "Account",
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)).padding(15.dp).clip(
                            RoundedCornerShape(8.dp))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier.size(width = 60.dp, height = 60.dp).clip(
                                    RoundedCornerShape(30.dp)).background(MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(text = "P", fontSize = 30.sp, color = MaterialTheme.colorScheme.onPrimary)
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            Column(

                            ) {
                                Text(text = "Lorem Ipsum", fontSize = 18.sp, fontWeight = FontWeight.W600)
                                Text(text = "loremipsum12@gmail.com", fontSize = 14.sp, fontWeight = FontWeight.W400)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Settings", fontSize = 16.sp, fontWeight = FontWeight.W600)
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn {
                        itemsIndexed(options) { index, item ->
                            Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary.copy(alpha = 0.04f)).padding(10.dp)) {
                                Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.List,
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = item, fontSize = 16.sp, fontWeight = FontWeight.W500)
                                    }
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            if(index < options.lastIndex) {
                                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 0.5.dp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary.copy(alpha = 0.04f)).padding(10.dp).clickable { viewModel.logout() }) {
                        Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Logout,
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Logout", fontSize = 16.sp, fontWeight = FontWeight.W500, lineHeight = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}