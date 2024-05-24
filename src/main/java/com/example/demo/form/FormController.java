package com.example.demo.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.SampleDao;
import com.example.demo.entity.EntForm;


@Controller
public class FormController {

	@RequestMapping("/sample")
	public String top(Model model) {
		model.addAttribute("title","こんにちわ");
			return "index";
		}
	
	@RequestMapping("/")
	public String hello(Model model) {
		model.addAttribute("title","Hello World");
			return "hello";
		}
	
	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("title","サンプルフォーム");
		return "form/input";
	}
	
	@RequestMapping("/confirm")
	public String confirm(@Validated Form form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title","入力ページ");
			return "form/input";
			}

		model.addAttribute("title","入力ページ");
		return "form/confirm";
	}
	
		//SampleDaoの用意
		private final SampleDao sampledao;

		@Autowired
		public FormController(SampleDao sampledao) {
			this.sampledao = sampledao;
		}

		//完了の処理
		@RequestMapping ("/complete")
		public String complete(Form form, Model model){
			EntForm entform = new EntForm();
			entform.setName(form.getName1());
			entform.setAge(form.getAge());
			sampledao.insertDb(entform);
			return "form/complete";
		}

//全件検索(SELECT)
	@RequestMapping("/view")
	public String view(Model model) {
		List<EntForm> list = sampledao.searchDb();
		model.addAttribute("dbList",list);
		model.addAttribute("title","一覧ページ");
		return "form/view";
	}
	
	//削除(DELETE)
		@RequestMapping("/del/{id}")
		public String destory(@PathVariable Long id) {
			sampledao.deleteDb(id);
			return "redirect:/view";
		}
	
		//更新画面の表示(SELECT)
		@RequestMapping("/edit/{id}")
		public String editView(@PathVariable Long id, Model model) {

			//DBからデータを1件取ってくる(リストの形)
			List<EntForm> list = sampledao.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			EntForm entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("form", entformdb);
			model.addAttribute("title", "編集ページ");
			return "form/edit";
		}
		
		//更新処理(UPDATE)
		@RequestMapping("/edit/{id}/exe")
		public String editExe(@PathVariable Long id, Model model, Form form) {
			//フォームの値をエンティティに入れ直し
			EntForm entform = new EntForm();
			System.out.println(form.getName1());//取得できているかの確認
			entform.setName(form.getName1());
			//更新の実行
			sampledao.updateDb(id,entform);
			//一覧画面へリダイレクト
			return "redirect:/view";
		}
}