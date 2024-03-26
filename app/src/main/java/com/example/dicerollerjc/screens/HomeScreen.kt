package com.example.dicerollerjc.screens

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dicerollerjc.R
import com.example.dicerollerjc.model.NavigationMenuItem
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreen(){
    val items = listOf(
        NavigationMenuItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationMenuItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        ),
        NavigationMenuItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        ),
        NavigationMenuItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        NavigationMenuItem(
            title = "All Moves",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .fillMaxWidth()
                        .height(150.dp)

                ){

                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                items.forEachIndexed{ index, item ->
                    NavigationDrawerItem(
                        label = {
                                Text(
                                    text = item.title
                                )
                                },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(route = when(index){
                                0 -> Screens.Home.screen
                                1 -> Screens.Profile.screen
                                2 -> Screens.Settings.screen
                                3 -> Screens.Search.screen
                                4 -> Screens.AllMoves.screen
                                else -> Screens.Home.screen
                            }){
                                popUpTo(0)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if(index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                NavigationDrawerItem(
                    label = {
                            Text(
                                text = "Logout"
                            )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout"
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                        selectedItemIndex = 0
                        navigationController.navigate(route = Screens.Home.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Dice Roller",
                            modifier = Modifier
                                .clickable {
                                    selectedItemIndex = 0
                                    navigationController.navigate(
                                        route = Screens.Home.screen
                                    ){
                                        popUpTo(0)
                                    }
                                }
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            //open navigation drawer
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu Icon",
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Cyan,
                        titleContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    ),
                    actions = {
                        IconButton(onClick = {
                            selectedItemIndex = 1
                            navigationController.navigate(route = Screens.Profile.screen){
                                popUpTo(0)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Profile Icon"
                            )
                        }
                        IconButton(onClick = {
                            selectedItemIndex = 3
                            navigationController.navigate(route = Screens.Search.screen){
                                popUpTo(0)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Cyan
                ) {
                    IconButton(
                        onClick = {
                            selectedItemIndex = 0
                            navigationController.navigate(route = Screens.Home.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ){
                        Icon(
                            imageVector = if(selectedItemIndex == 0) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = "Home Icon"
                        )
                    }
                    IconButton(
                        onClick = {
                            selectedItemIndex = 1
                            navigationController.navigate(route = Screens.Profile.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ){
                        Icon(
                            imageVector = if(selectedItemIndex == 1) Icons.Filled.Person else Icons.Outlined.Person,
                            contentDescription = "Profile Icon"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ){
                        FloatingActionButton(
                            onClick = {
                                showBottomSheet = true
                            }
                        ) {
                            Image(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Icon"
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            selectedItemIndex = 2
                            navigationController.navigate(route = Screens.Settings.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ){
                        Icon(
                            imageVector = if(selectedItemIndex == 2) Icons.Filled.Settings else Icons.Outlined.Settings,
                            contentDescription = "Settings Icon"
                        )
                    }
                    IconButton(
                        onClick = {
                            selectedItemIndex = 3
                            navigationController.navigate(route = Screens.Search.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ){
                        Icon(
                            imageVector = if(selectedItemIndex == 3) Icons.Filled.Search else Icons.Outlined.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            }
        ) {innerPadding ->
            NavHost(
                navController = navigationController,
                startDestination = Screens.Home.screen
            ){
                composable(route = Screens.Home.screen){
                    DiceRollerSection(Modifier.padding(innerPadding))
                }
                composable(route = Screens.Profile.screen){
                    Profile()
                }
                composable(route = Screens.Settings.screen){
                    Settings()
                }
                composable(route = Screens.Search.screen){
                    Search()
                }
                composable(route = Screens.Post.screen){
                    Post()
                }
                composable(route = Screens.Story.screen){
                    Story()
                }
                composable(route = Screens.Reel.screen){
                    Reel()
                }
                composable(route = Screens.Live.screen){
                    Live()
                }
                composable(route = Screens.AllMoves.screen){
                    AllMoves(innerPadding)
                }
            }
        }
    }
    if(showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement =  Arrangement.spacedBy(20.dp),
            ) {
                BottomSheetItem(
                    icon = Icons.Default.ThumbUp,
                    title = "Create Post",
                    onClick = {
                        showBottomSheet = false
                        navigationController.navigate(route = Screens.Post.screen){
                            popUpTo(0)
                        }
                    }
                )
                BottomSheetItem(
                    icon = Icons.Default.Star,
                    title = "Add a story",
                    onClick = {
                        showBottomSheet = false
                        navigationController.navigate(route = Screens.Story.screen){
                            popUpTo(0)
                        }
                    }
                )
                BottomSheetItem(
                    icon = Icons.Default.PlayArrow,
                    title = "Create a Reel",
                    onClick = {
                        showBottomSheet = false
                        navigationController.navigate(route = Screens.Reel.screen){
                            popUpTo(0)
                        }
                    }
                )
                BottomSheetItem(
                    icon = Icons.Default.Favorite,
                    title = "Go Live",
                    onClick = {
                        showBottomSheet = false
                        navigationController.navigate(route = Screens.Live.screen){
                            popUpTo(0)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun DiceRollerSection(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        var num by remember {
            mutableIntStateOf(1)
        }
        Image(
            painter = getPainterResource(num = num),
            contentDescription = "Dice Image"
        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = {
                num = Random.nextInt(from = 1, until = 7)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = stringResource(R.string.roll),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun getPainterResource(num: Int): Painter {
    return painterResource(id = when(num){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    })
}

@Composable
fun BottomSheetItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title
        )
        Text(
            text = title,
            fontSize = 22.sp
        )
    }
}