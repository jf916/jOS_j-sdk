package jOS.Core

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer

class OSSLicenceActivity : jActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        configure(R.layout.ossactivity, false)
        super.onCreate(savedInstanceState)
        findViewById<ComposeView>(R.id.my_composable).setContent {
            MaterialTheme(
                colorScheme = ThemeEngine.getColourScheme()
            ) {
                Surface {
                    LibrariesContainer(
                        Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
