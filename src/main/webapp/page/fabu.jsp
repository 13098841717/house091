﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" language="JavaScript">
  $(function () {
    $.post("getTypeData",function (data) {
        // var option="<opiton value="+data.id+">"+data.name+"</opiton>"
        // $("#type_id").append(option);
      for (var i = 0; i < data.length; i++) {
      var ooption=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#type_id").append(ooption);
      }
    },"json");
    $.post("getDistrictData",function (data) {
      // var option="<opiton value="+data.id+">"+data.name+"</opiton>"
      // $("#type_id").append(option);
        for (var i = 0; i < data.length; i++) {
        var ooption=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#district_id").append(ooption);
      }
    },"json");
    $("#district_id").change(function () {
      var did=$("#district_id").val();
      $("#street_id>option:gt(0)").remove();
      $.post("getStreetData",{"did":did},function (data) {
        for (var i = 0; i <data.length ; i++) {
          var ooption=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#street_id").append(ooption);
        }
      },"json")
    })
  })
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action action=addHouse enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD>
      <SELECT class=text name=typeId id="type_id">
      <OPTION selected value="">=请选择=</OPTION>
    </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text 
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=districtId id="district_id">
      <OPTION selected value="">=请选择=</OPTION></SELECT>
      街：<SELECT class=text name=streetId id="street_id"><OPTION selected value="">=请选择=</OPTION>
      </SELECT> </TD></TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description></TEXTAREA></TD></TR>
  <tr>
    <td class=field>上传:</td>
    <td>
      <input type="file" name="filename">
    </td>
  </tr></TBODY></TABLE>
<DIV class=buttons><INPUT value=立即发布 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>

</HTML>
