package alsatang.co.kr.mygallery

import alsatang.co.kr.mygallery.databinding.ActivityMainBinding
import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        // 권한처리 관련 내용 작성
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        //setContentView(R.layout.activity_main)
        setContentView(view)

        if(
            ActivityCompat.checkSelfPermission(this,
                                               Manifest.permission.READ_EXTERNAL_STORAGE
                                              ) != PackageManager.PERMISSION_GRANTED
        )
        {
            // 전에 권한을 거부한 적이 있는지 확인하는 부분
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                AlertDialog.Builder(this).apply {
                    setTitle("권한이 필요한 이유")
                    setMessage("사진정보를 얻으려면 권한이 필요합니다.")
                    setPositiveButton("권한요청"){
                        _,_ ->
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                    setNegativeButton("거부", null)
                }.show()    // AlertDialog 를 보여주는 부분
            }
            else{
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            return
        }

        // 권한이 이미 허용됨.
        getAllPhotos()
    }
    /*
    val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null,
        null,
        null,
        "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )

     */

    private fun getAllPhotos() {
        var uris = mutableListOf<Uri>()

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )?.use{cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                uris.add(contentUri)
            }

        }
    }
}