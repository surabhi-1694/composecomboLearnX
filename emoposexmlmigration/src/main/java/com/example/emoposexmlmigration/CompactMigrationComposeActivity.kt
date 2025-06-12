package com.example.emoposexmlmigration

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emoposexmlmigration.composefuns.composeButtonUI
import com.example.emoposexmlmigration.composefuns.composeMigrateView
import com.example.emoposexmlmigration.databinding.ActivityCompactMigrationBinding

class CompactMigrationComposeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCompactMigrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_compact_migration)
        binding = ActivityCompactMigrationBinding.inflate(layoutInflater).apply {
            composeTxtView.setContent {
                MaterialTheme{
                    composeMigrateView(stringResource(R.string.textcompose))
                }
            }

            composeBtnView.setContent {
                MaterialTheme{
                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        //first Button
                        composeButtonUI(btntext = stringResource(R.string.btncompose1), onClickCallBack = {
                            Toast.makeText(this@CompactMigrationComposeActivity,"Btn 1 Clicked ",Toast.LENGTH_LONG).show()
                        })

                        //second Button
                        composeButtonUI(btntext = stringResource(R.string.btncompose2), onClickCallBack = {
                            Toast.makeText(this@CompactMigrationComposeActivity,"Btn 2 Clicked ",Toast.LENGTH_LONG).show()
                        })
                    }

                }
            }
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




    }
}