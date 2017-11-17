package org.dragon_egg.schedulealarm.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.dragon_egg.schedulealarm.activity.RingActivity;

/**
 * Created by Crow314 on 2017/10/22.
 */

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent){
		Intent startActivityIntent = new Intent(context, RingActivity.class); //Intent作成
		startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //新規タスク起動のフラグ設定
		context.startActivity(startActivityIntent); //Activity起動
	}
}
