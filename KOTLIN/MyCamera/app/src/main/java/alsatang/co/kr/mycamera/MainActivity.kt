package alsatang.co.kr.mycamera

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import java.io.File

class MainActivity : AppCompatActivity() {
    var uri: Uri? = null;

    val btnCamera = findViewById<Button>(R.id.btnCamera);
    val output1 = findViewById<TextView>(R.id.output1);
    val output2 = findViewById<ImageView>(R.id.output2);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera.setOnClickListener {
            takePicture();

            AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted { permissions -> Log.d("Main","허용권한 갯수 : ${permissions.size}") }
                .onDenied { permissions -> Log.d("Main","거부권한 갯수 : ${permissions.size}") }
                .start()

        }
    }

    fun takePicture(){
        val captureFile = File(filesDir, "capture.jpg");
        try {
            if(captureFile.exists()){
                captureFile.delete()
            }
            captureFile.createNewFile()
        }catch (e:Exception) {
            e.printStackTrace()
        }

        uri = if(Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, captureFile );
        }else {
            Uri.fromFile(captureFile);
        }

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivity(intent)
    }
}