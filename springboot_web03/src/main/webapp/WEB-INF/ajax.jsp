<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>您好Springboot</title>
    <script type="text/javascript"></script>
    <script type="text/html" src="/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">


        $(function(){

            $.ajax({
                type : "get",
                url  : "/findAjax",
                //dataType : "text/json/html/xml",		//返回值类型
                async : false,	//关闭异步操作,改为同步的请求
                success : function(data){
                    let trs = "";
                    for(let user of data){
                        let id = user.id;
                        let name = user.name;
                        let age = user.age;
                        let sex = user.sex;
                        trs += "<tr align='center'><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>"
                    }
                    $("#tab1").append(trs);
                },
                error : function(){
                    alert("请求异常!!!!");
                }
            })

        });

    </script>
</head>
<body>
<table border="1px" width="65%" align="center">
    <tr>
        <td colspan="6" align="center"><h3>学生信息</h3></td>
    </tr>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th></th>
    </tr>
</table>
</body>
</html>