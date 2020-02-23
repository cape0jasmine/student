<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加成绩</title>
    <script>
  		$(function() {
    		$( "#dialog" ).dialog();
  		});
  	</script>
</head>
<body>
	<div id="dialog" title="添加成绩" style="background-color:white">
		<form method="post" action="../update_score"  >
			<table id="table" frame="box" align="center" border="1px solid red">
				<tr height="35">
					<td>学号</td>
					<td></td>
				</tr>
				<tr height="35">
					<td>姓名</td>
					<td></td>
				</tr>
				<tr height="35">
					<td>专业</td>
					<td></td>
				</tr>
				<tr height="35">
					<td>科目</td>
					<td>
						<select>
							<option value="">--请选择--</option>
						</select>
					</td>
				</tr>
				<tr height="35">
					<td>分数</td>
					<td><input type="text" id="score"></td>
				</tr>
			</table>
	</form>
	</div>
</body>
</html>

