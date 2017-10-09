package org.dragon_egg.schedulealarm.alarm;

/**
 * Created by Crow314 on 2017/10/01.
 */
import android.app.PendingIntent;
import android.media.Ringtone;

import java.io.Serializable;
import java.util.Date;

/**
 * Alarmの継承元となるクラス
 * 自身はOnceAlarmとして機能
 */
public class Alarm implements Comparable<Alarm>, Serializable{

	//################
	//Field
	//################

	//Static Field
	private static boolean defaultEnable; //デフォルトで有効か否か(定数で有効)
	private static Ringtone defaultSound; //デフォルトの音声ファイル
	private static boolean defaultVibration; //デフォルトでのバイブレーションの可否
	private static int defaultSnoozeInterval; //デフォルトでのスヌーズの間隔(0の場合はスヌーズ無し)
	private static  AlarmCategory defaultCategory; //デフォルトのカテゴリー

	//Normal Field
	private Date time; //設定時刻
	private boolean enable; //有効か否か
	private Ringtone sound; //音声ファイル
	private boolean vibration; //バイブレーションの可否
	private int snoozeInterval; //スヌーズの間隔(0の場合はスヌーズ無し)
	private AlarmType type; //種別(繰り返し、スケジュール等)
	private AlarmCategory category; //カテゴリー
	private String text; //アラームテキスト(備考)

	//################
	//Method
	//################

	//StaticInitBlock
	static{
		//デフォルトの設定を読み込む処理 設定関連の仕様が固まり次第書きます
	}

	//Constructor

	/**
	 * 時刻のみを引数で受け取り、その他の設定項目をデフォルトのもので設定します。
	 * @param time 設定時刻
	 * @throws IllegalArgumentException 不正な値が設定された場合 主として設定時刻が過去の場合
	 */
	Alarm(Date time) throws IllegalArgumentException{
		this(time, defaultEnable, defaultSound, defaultVibration, defaultSnoozeInterval, AlarmType.ONCE, defaultCategory, ""); //登録用コンストラクタを呼び出す
	}

	/**
	 * 引数の値をフィールドにもつAlarmインスタンスを生成します。
	 * @param time 設定時刻
	 * @param enable 有効か否か
	 * @param sound 音声ファイル
	 * @param vibration バイブレーションの可否
	 * @param snoozeInterval スヌーズの間隔(0の場合はスヌーズ無し)
	 * @param type 種別(繰り返し、スケジュール等)
	 * @param category カテゴリー
	 * @param text アラームテキスト(備考)
	 * @throws IllegalArgumentException 不正な値が設定された場合 主として設定時刻が過去の場合
	 */
	Alarm(Date time, boolean enable, Ringtone sound, boolean vibration, int snoozeInterval, AlarmType type, AlarmCategory category, String text) throws IllegalArgumentException{
		//引数設定作業
		this.setTime(time);
		this.setEnable(enable);
		this.setSound(sound);
		this.setVibration(vibration);
		this.setSnoozeInterval(snoozeInterval);
		this.setType(type);
		this.setCategory(category);
		this.setText(text);
	}

	//########
	//PrivateMethod
	//########

	/**
	 * 引数の時刻が不正な値でないか確認します。
	 * @param time 確認する時刻
	 * @return 確認結果(問題なければtrue)
	 * @throws IllegalArgumentException 引数の時刻が不正な値だった場合 主として時刻が過去だった場合
	 */
	private boolean dateCheck(Date time) throws IllegalArgumentException{
		boolean result = false;
		if(time.before(new Date())){ //引数が過去の場合IllegalArgumentExceptionをスロー
			throw new IllegalArgumentException("過去の日付が設定されています");//将来的にメッセージはenumより読み出し予定
		}else{ //何れのチェックでも問題なかった場合trueを返す
			result = true;
		}
		return result;
	}

	/**
	 * 有効化時に実行し、AlarmをAlarmManagerに登録します。
	 */
	private void onEnable(){
		//Receiver系が完成次第書きます
	}

	/**
	 * 無効化時に実行し、AlarmをAlarmManagerから削除します。
	 */
	private void onDisable(){
		//Receiver系が完成次第書きます
	}

	//########
	//PublicMethod
	//########

	//NormalMethod

	//OverrideMethod

	/**
	 * 順序付けのために2つのAlarmの時刻を比較します。
	 * @param anotherAlarm 比較対象のAlarm
	 * @return 引数AlarmがこのAlarmと等しい場合は値0。このAlarmが引数Alarmより前の場合は0より小さい値。このAlarmが引数Alarmより後の場合は0より大きい値。
	 */
	@Override
	public int compareTo(Alarm anotherAlarm){
		return this.time.compareTo(anotherAlarm.getTime());//インスタンスのtimeとanotherAlarmのtimeをDate.compareTo()で比較
	}

	//Getter&SetterMethod

	/**
	 * 設定時刻を取得します。
	 * @return 設定時刻
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 設定時刻を引数のものに変更します。
	 * @param time 設定時刻
	 * @throws IllegalArgumentException timeが過去の値の場合
	 */
	public void setTime(Date time) throws IllegalArgumentException{
		dateCheck(time);
		this.time = time;
	}

	/**
	 * 有効か否かを取得します。
	 * @return 有効か否か
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * 有効か否かを引数のものに変更し、変更した場合はAlarmを有効化･無効化します。

	 * @param enable
	 */
	public void setEnable(boolean enable) {
		//enableを変更しないならなにもしない
		if(this.enable == enable){
			return;
		}
		if (enable){ //enableがtrueに変更される場合onEnableを呼ぶ
			onEnable();
		}else if(!enable){ //enableがfalseに変更される場合onDisableを呼ぶ
			onDisable();
		}
		this.enable = enable;
	}

	/**
	 * 音声ファイルを取得します。
	 * @return 音声ファイル
	 */
	public Ringtone getSound() {
		return sound;
	}

	/**
	 * 音声ファイルを引数のものに変更します。
	 * @param sound　音声ファイル
	 */
	public void setSound(Ringtone sound){
		this.sound = sound;
	}

	/**
	 * バイブレーションの可否を取得します。
	 * @return バイブレーションの可否
	 */
	public boolean isVibration() {
		return vibration;
	}

	/**
	 * バイブレーションの可否を引数のものに変更します。
	 * @param vibration バイブレーションの可否
	 */
	public void setVibration(boolean vibration){
		this.vibration = vibration;
	}

	/**
	 * スヌーズの間隔を取得します。
	 * @return スヌーズの間隔(0の場合はスヌーズ無し)
	 */
	public int getSnoozeInterval() {
		return snoozeInterval;
	}

	/**
	 * スヌーズの間隔を引数のものに変更します。
	 * @param snoozeInterval スヌーズの間隔(0の場合はスヌーズ無し)
	 */
	public void setSnoozeInterval(int snoozeInterval){
		this.snoozeInterval = snoozeInterval;
	}

	/**
	 * 種別(繰り返し、スケジュール等)を取得します。
	 * @return 種別(繰り返し、スケジュール等)
	 */
	public AlarmType getType() {
		return type;
	}

	/**
	 * 種別(繰り返し、スケジュール等)を引数のものに変更します。
	 * @param type 種別(繰り返し、スケジュール等)
	 */
	public void setType(AlarmType type) {
		this.type = type;
	}

	/**
	 * カテゴリーを取得します。
	 * @return カテゴリー
	 */
	public AlarmCategory getCategory() {
		return category;
	}

	/**
	 * カテゴリーを引数のものに変更します。
	 * @param category カテゴリー
	 */
	public void setCategory(AlarmCategory category) {
		this.category = category;
	}

	/**
	 * アラームテキストを取得します。
	 * @return アラームテキスト(備考)
	 */
	public String getText() {
		return text;
	}

	/**
	 * アラームテキストを引数のものに変更します。
	 * @param text アラームテキスト(備考)
	 */
	public void setText(String text){
		this.text  = text;
	}

}
