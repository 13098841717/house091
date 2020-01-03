<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script src="../scripts/jquery-1.8.3.js"></script>
</HEAD>
<script type="text/javascript" language="JavaScript">
  $(function () {
    $.post("getTypeData",function (data) {
      // var option="<opiton value="+data.id+">"+data.name+"</opiton>"
      // $("#type_id").append(option);
      for (var i = 0; i < data.length; i++) {
        var ooption=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#type_id").append(ooption);
        $("#type_id").val(${condition.tid})
      }
    },"json");
    $.post("getDistrictData",function (data) {
      // var option="<opiton value="+data.id+">"+data.name+"</opiton>"
      // $("#type_id").append(option);0
      for (var i = 0; i < data.length; i++) {
        var ooption=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#district_id").append(ooption);
        $("#district_id").val(${condition.did});
        loadData();
      }
    },"json");
    function loadData(){
      var did=$("#district_id").val();
      $("#street_id>option:gt(0)").remove();
      $.post("getStreetData",{"did":did},function (data) {
        for (var i = 0; i <data.length ; i++) {
          var ooption=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#street_id").append(ooption);
        }
        $("#street_id").val(${condition.sid});
      },"json")
    }
    $("#district_id").change(function () {
      loadData();
    })
  })
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<form action="mainHouse" method="post" id="myform">
  <input type="hidden" value="1" id="pageNum" name="page">
  <DIV id=navbar class=wrap>
    标题：<input type="text" name="title" size="10" value="${condition.title}">
    区域：<select name="did" id="district_id"><option value="">==请选择==</option></select>
    街道：<select name="sid" id="street_id"><option value="">==请选择==</option></select>
    类型：<select name="tid" id="type_id"><option value="">==请选择==</option></select>
    价格区间：<input type="text" name="startPrice" size="5">-<input type="text" name="endPrice" size="5">
    <input type="submit" value="提交">
  </DIV>
</form>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
    <TR>
      <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
      <TD>
        <DL>
          <DT><A href="details.htm" target="_blank">${h.title}</A></DT>
          <DD>${h.dname}区${h.sname}大街,${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
      <TD class=house-type>${h.tname}</TD>
      <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
    <TR>无租房信息</TR>
  </c:forEach>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pageNum-1})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pageNum+1})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script>
  function goPage(pageNum) {
    $("#pageNum").val(pageNum);
    $("#myform").submit();
  }
</script>
</HTML>
