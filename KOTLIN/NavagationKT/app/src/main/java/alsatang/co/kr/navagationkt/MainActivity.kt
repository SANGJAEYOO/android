package alsatang.co.kr.navagationkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.START)
        }

        naviView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.access -> {showToast("액세스")}
            R.id.search -> {showToast("서피")}
            R.id.send -> {showToast("전송")}
        }
        layout_drawer.closeDrawers()
        return false
    }

    private fun showToast (msg:String)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        if(layout_drawer.isDrawerOpen(GravityCompat.START))
        {
            layout_drawer.closeDrawers()
        }
        else {
            super.onBackPressed()
        }
    }
}
