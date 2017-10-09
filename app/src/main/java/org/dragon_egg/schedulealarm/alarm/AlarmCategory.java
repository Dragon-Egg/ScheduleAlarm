package org.dragon_egg.schedulealarm.alarm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Crow314 on 2017/10/02.
 */

public class AlarmCategory {

	//################
	//Field
	//################

	//Static Field
	public static final List<AlarmCategory> CATEGORY_LIST = new LinkedList<>(); //カテゴリー一覧
	public static final AlarmCategory DEFAULT_CATEGORY; //デフォルトのカテゴリー

	//Normal Field
	private String name; //カテゴリー名
	private String description; //説明

	//################
	//Method
	//################

	//StaticInitBlock
	static{
		//いろいろ未定なのでそのうち書きます
		DEFAULT_CATEGORY = new AlarmCategory("(None)", "");
	}

	//Constructor

	/**
	 * 引数の名称のアラームカテゴリーを作成します。
	 * @param name カテゴリー名
	 */
	AlarmCategory(String name){
		this(name, "");
	}

	/**
	 * 引数の名称,説明のアラームカテゴリーを作成します。
	 * @param name カテゴリー名
	 * @param description 説明
	 */
	AlarmCategory(String name, String description){
		this.setName(name);
		this.setDescription(description);
	}

	//########
	//StaticMethod
	//########



	//########
	//PublicMethod
	//########

	//OverrideMethod
	public String toString(){
		return "AlarmCategory{name='" + this.getName() + "', description='" + this.getDescription() +"'}";
	}

	//Getter&SetterMethod

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
