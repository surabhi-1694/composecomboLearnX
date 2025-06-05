package com.example.emoposexmlmigration

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emoposexmlmigration.composefuns.composeButtonUI
import com.example.emoposexmlmigration.composefuns.composeMigrateView
import com.example.emoposexmlmigration.databinding.ActivityCompactMigrationBinding

class CompactMigrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCompactMigrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_compact_migration)
        binding = ActivityCompactMigrationBinding.inflate(layoutInflater).apply {
            composeView.setContent {
                MaterialTheme{
                    composeMigrateView()
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