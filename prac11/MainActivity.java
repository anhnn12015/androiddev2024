import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;
    private static final String FILE_NAME = "music.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kiểm tra và yêu cầu quyền WRITE_EXTERNAL_STORAGE nếu cần
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            } else {
                copyFileToExternalStorage();
            }
        } else {
            copyFileToExternalStorage();
        }
    }

    // Xử lý khi người dùng cấp quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                copyFileToExternalStorage();
            }
        }
    }

    private void copyFileToExternalStorage() {
        // Đường dẫn của file mp3 trên SD card (external storage)
        File sdCard = Environment.getExternalStorageDirectory();
        File file = new File(sdCard, FILE_NAME);

        if (!file.exists()) {
            // Copy file từ res/raw vào external storage
            try (InputStream in = getResources().openRawResource(R.raw.music); // nếu sử dụng thư mục assets thì dùng: getAssets().open("music.mp3");
                 OutputStream out = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
                playMusic(file.getAbsolutePath()); // Sau khi copy xong thì chơi nhạc
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Nếu file đã tồn tại, phát nhạc
            playMusic(file.getAbsolutePath());
        }
    }

    private void playMusic(String path) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
