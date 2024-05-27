package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntForm;

@Repository
public class SampleDao {

	private final JdbcTemplate db;

	@Autowired
	public SampleDao(JdbcTemplate db) {
		this.db = db;
	}

	public void insertDb(EntForm entform) {
		db.update("INSERT INTO sample (date, naiyou, yen) VALUES(?, ?, ?)", entform.getDate(),entform.getNaiyou(), entform.getYen());
	}

//検索処理
public List<EntForm> searchDb(){
	String sql = "SELECT * FROM sample";

	//データベースから取り出したデータをresultDB1に入れる
	List<Map<String, Object>> resultDb1 = db.queryForList(sql);

	//画面に表示しやすい形のList(resultDB2)を用意
	List<EntForm> resultDb2 = new ArrayList<EntForm>();

	//1件ずつピックアップ
	for(Map<String,Object> result1:resultDb1) {

		//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
		EntForm entformdb = new EntForm();

		//id、nameのデータをentformdbに移す
		entformdb.setId((int)result1.get("id"));
		entformdb.setDate((String)result1.get("date"));
		entformdb.setNaiyou((String)result1.get("naiyou"));
		entformdb.setYen((int)result1.get("yen"));

		//移し替えたデータを持ったentformdbを、resultDB2に入れる
		resultDb2.add(entformdb);
	}

	//Controllerに渡す
	return resultDb2;}


	//削除(DELETE)
	public void deleteDb(Long id) {
		//コンソールに表示
		System.out.println("削除しました");
		//DBからデータを削除
		db.update("delete from sample where id=?", id);
		}
	
	//更新画面の表示(SELECT)
		public List<EntForm> selectOne(Long id) {

			//コンソールに表示
			System.out.println("編集画面を出します");
			//データベースから目的の1件を取り出して、そのままresultDB1に入れる
			List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM sample where id=?", id);
			//画面に表示しやすい形のList(resultDB2)を用意
			List<EntForm> resultDb2=new ArrayList<EntForm>();

			//1件ずつピックアップ
			for(Map<String,Object> result1:resultDb1) {
				//データ1件分を1つのまとまりとするので、EntForm型の「entformdb」を生成
				EntForm entformdb = new EntForm();
				//id、nameのデータをentformdbに移す
				entformdb.setId((int)result1.get("id"));
				entformdb.setDate((String)result1.get("date"));
				entformdb.setNaiyou((String)result1.get("naiyou"));
				entformdb.setYen((int)result1.get("yen"));
				//移し替えたデータを持ったentformdbを、resultDB2に入れる
				resultDb2.add(entformdb);
			}

			//Controllerに渡す
			return resultDb2;
		}
	
		//更新の実行(UPDATE)
		public void updateDb(Long id, EntForm entform) {
			//コンソールに表示
			System.out.println("編集の実行");
			//UPDATEを実行
			db.update("UPDATE sample SET date = ?, naiyou=?, yen=? WHERE id = ?",entform.getDate(),entform.getNaiyou(),entform.getYen(),id);
		}
		
	}