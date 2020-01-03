<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">--%>
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <link rel="stylesheet" href="../css/style.css">
  <%--<link rel="stylesheet" href="../css/bootstrap.css">--%>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script src="../scripts/jquery-1.8.3.js"></script>
<%--<script src="../scripts/bootstrap.js"></script>--%>
<script>
   $(function(){
       //添加点击事件
     $("#inputname").change(function(){
         //取值
       var uname=$("#inputname").val();
         //发送异步请求
         $.post("isExistence",{"uName":uname},function(data){
              if(data.result){
                $("#notice").html("用户名可用").css("color","green");
              }else{
                $("#notice").html("用户名不可用").css("color","red");
              }
         },"json");
     });
   });
   function formcheck() {
     var inputname=$("#inputname");
     var pwd1 =$("#pwd1");
     var pwd2 =$("#pwd2");
     var phone =$("#phone");
     var age = $("#age");
     var reg_name = /^[a-zA-Z_$]\w{5}/;
     var reg_pwd = /\w{6}/;
     var reg_age = /^(1[6-9])|([2-9]\d)$/;
     var reg_phone = /^1\d{10}$/;
     if(inputname.val().trim()=== ''){
       $("#notice").html("用户名不能为空").css("color","red");
       return false;
     }
     /*trim():去掉空格*/
     if(!reg_name.test(inputname.val().trim())){
       alert("用户名格式不正确");
       return false;
     }
     if(pwd1.val().trim()=== ''){
       alert("密码不能为空");
       return false;
     }
     if(!reg_pwd.test(pwd1.val().trim())){
       alert("密码格式不正确");
       return false;
     }
     if(pwd1.val().trim()!==pwd2.val().trim()){
       alert("2次密码输入不一致");
       return false;
     }
     if(!reg_age.test(age.val().trim())){
       alert("年龄格式不正确");
       return false;
     }
     if(!reg_phone.test(phone.val().trim())){
       alert("手机格式不正确");
       return false;
     }
     return true;
   }
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="register" method="post" name="form1" onsubmit="return formcheck();">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text id="inputname" type=text name=name><span id="notice"></span></TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text id="pwd1" type=password name=password></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text id="pwd2" type=password name=repassword> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text id="phone" type=text name=telephone> </TD></TR>
  <TR>
    <TD class=field>年    龄：</TD>
    <TD><INPUT class=text id="age" type=text name=age> </TD></TR></TBODY></TABLE>
<DIV class=buttons>
  <input type="submit" value="立即注册">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
</HTML>
