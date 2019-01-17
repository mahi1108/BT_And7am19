package cubex.mahesh.bt_and7am19

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        var h = Handler()
        h.postDelayed({
            var i = Intent(this@WelcomeActivity,
                MainActivity::class.java)
            startActivity(i)
        },3000)

    }
}
