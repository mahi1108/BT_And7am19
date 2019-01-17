package cubex.mahesh.bt_and7am19

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

var temp_list = mutableListOf<String>()
var myadapter:ArrayAdapter<String>? = null
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bAdapter = BluetoothAdapter.getDefaultAdapter()
        s1.isChecked = bAdapter.isEnabled  // 1

        var h = Handler()
        h.postDelayed(object : Runnable {
            override fun run() {
                h.postDelayed(this,1000)
                s1.isChecked = bAdapter.isEnabled  // 1
            }
        },1000)


        myadapter  = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_single_choice,
            temp_list)
        lview.adapter = myadapter




        s1.setOnCheckedChangeListener {     // 2
                compoundButton, b ->
            if(b)
                bAdapter.enable()
            else
                bAdapter.disable()
        }
        bt.setOnClickListener {       // 3
                bAdapter.startDiscovery()

                var filter = IntentFilter()
                filter.addAction(BluetoothDevice.ACTION_FOUND)
                registerReceiver(MyReceiver(),filter)

        }
    }

    class MyReceiver : BroadcastReceiver()
    {
        override fun onReceive(p0: Context?, p1: Intent?) {
                var device = p1?.getParcelableExtra<BluetoothDevice>(
                            BluetoothDevice.EXTRA_DEVICE)
                    temp_list.add(device?.name+"\n"+device?.address)
                     myadapter?.notifyDataSetChanged()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(MyReceiver())
    }
}
