function checkall() {
	var alls = document.getElementsByName("ids");
	var ch = document.getElementById("checkall");
	if (ch.checked) {
		for ( var i = 0; i < alls.length; i++) {
			alls[i].checked = true;
		}
	} else {
		for ( var i = 0; i < alls.length; i++) {
			alls[i].checked = false;
		}
	}
}

function backSelect() {

	var alls = document.getElementsByName("ids");
	for ( var i = 0; i < alls.length; i++) {
		if (alls[i].checked) {

			alls[i].checked = false;
		} else {

			alls[i].checked = true;
		}
	}

}
function selectDelete(id) {

	var num = id;
	var flag = 0;
	var allSelect = document.getElementsByName("ids");
	for ( var i = 0; i < allSelect.length; i++) {
		if (allSelect[i].checked == true) {
			flag = 1;
		}

	}
	if (flag == 1) {
		if (confirm("确定要删除吗？？？")) {

			var id = document.getElementsByName("ids");
			var ids = "";
			for ( var i = 0; i < id.length; i++) {
				var checkbox = id[i];
				if (checkbox.checked) {
					ids = ids + checkbox.value + ",";
				}
			}
			var newIds = ids.substring(0, ids.length - 1);
			if (num == 1) {
				window.location.href = "mypackage/email_selectDelete.action?ids="
						+ newIds;

			}
			if (num == 2) {

				window.location.href = "mypackage/email_selectDelete2.action?ids="
						+ newIds;
			}

		}
	} else {
		alert("请先勾选，再删除！！");
	}
}

function deleteEmail(id) {

	if (confirm("确定删除吗？？？")) {

		window.location.href = "mypackage/email_deleteById.action?id=" + id;
	}

}
function deleteEmail2(id) {

	if (confirm("确定删除吗？？？")) {

		window.location.href = "mypackage/email_deleteById2.action?id=" + id;
	}

}

function outExcel() {

	window.location.href = "mypackage/email_outExport.action";

}
function outExcel2() {

	window.location.href = "mypackage/email_outExport2.action";

}

function del(btn) {

	var tr = btn.parentNode.parentNode;
	// alert(tr.tagName);
	// 获取tr在table中的下标
	var index = tr.rowIndex;
	// 删除
	var tb = document.getElementById("tb");
	tb.deleteRow(index);
}

function add() {

	var tb = document.getElementById("tb");
	// 写入一行

	var tr = tb.insertRow(-1);
	// 写入列
	var td = tr.insertCell(-1);

	// 写入数据

	td.innerHTML = "附件";
	// 再声明一个新的td

	var td2 = tr.insertCell(-1);
	// 写入一个input

	td2.innerHTML = '<input type="file" name="emailFile" /><button onclick="del(this);" class="btn btn-primary">删除</button> <span id = "msgFile"></span>';

}

/* ============删除附件的方法=========== */
/* ============提交表单时对上传文件的控制============ */
function Receives() {
	var flag = 0;
	var res = document.getElementsByName("email.receive");
	var msg = document.getElementById("msgReceives");
	for ( var i = 0; i < res.length; i++) {
		if (res[i].checked == true) {
			flag = 1;
		}
	}
	if (flag == 0) {
		msg.innerHTML = "收件人不能空";
		msg.style.color = "red";
		return false;
	}
	if (flag == 1) {
		msg.innerHTML = "√";
		msg.style.color = "green";

	}

}
function Title() {
	var res = document.getElementById("title");
	var msg = document.getElementById("msgTitle");
	if (res == null || res.value == null || res.value == "") {
		msg.innerHTML = "标题不能空";
		msg.style.color = "red";
		return false;
	} else {
		msg.style.color = "green";
		msg.innerHTML = "√";

	}
}
function Content() {
	var res = document.getElementById("content");
	var msg = document.getElementById("msgContent");
	if (res == null || res.value == null || res.value == "") {
		msg.innerHTML = "不要这么吝啬，说几句把！";
		msg.style.color = "red";
		return false;
	} else {
		msg.style.color = "green";
		msg.innerHTML = "√";

	}
	;
}
function file() {

	var files = document.getElementsByName("emailFile");
	var msg = document.getElementById("msgFile");
	if (files.length == 0) {
		if (confirm("你确定不上传附件吗？？？")) {
			return true;

		} else {

			msg.innerHTML="请选择！！！";
			msg.style.color="green";
			
			return false;
		}

	}

}

function submitform() {
	
	Receives();

	if (Receives() == false) {

		return false;
	}
	Title();
	if (Title() == false) {

		return false;
	}

	Content();
	if (Content() == false) {
		return false;
	}

	file();
	for ( var i = 0; i < files.length; i++) {
		if (files[i].value == "" || files[i].length == 0) {
			alert("第" + (i + 1) + "个文件不能为空");
			return false;
		}

	}
	
	if (file() == false) {

		return false;
	}

}