<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/8/22
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/styles/global_color.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
    <script language="javascript" type="text/javascript">
      $(function () {
          $("input[value='取消']").click(function () {
              $("#roleName").val("");
              $("input:checked").removeAttr("checked");
          });
          $("#goupRoleServlet").click(function () {
              $("#loginForm").submit();
          });
      })
    </script>
</head>
<body>
<!--Logo区域开始-->
<div id="header" >
    <img src="<%=request.getContextPath()%>${admin.aimage}" class="user_img" />
    <span  class="user_span">${admin.auname}</span>
    <img src="<%=request.getContextPath()%>/images/logo.png" alt="logo" class="left"/>
    <a href="<%=request.getContextPath()%>/exitServlet"  class="user_a">[退出]</a>
</div>
<!--Logo区域结束-->
<!--导航区域开始-->
<div id="navi">
    <ul id="menu">
        <li><a href="<%=request.getContextPath()%>/view/index.jsp" class="index_off"></a></li>
        <c:forEach items="${admin.powerList}" var="url">
            <c:choose>
                <c:when test="${url.pclass=='role'}">
                    <li><a href="${pageContext.request.contextPath}${url.purl}" class="${url.pclass}_on"></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}${url.purl}" class="${url.pclass}_off"></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li><a href="<%=request.getContextPath()%>/view/user/user_info.jsp" class="information_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/view/user/user_modi_pwd.jsp" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <!--保存操作后的提示信息：成功或者失败-->
    <div id="save_result_info" class="save_success"></div>
    <form action="<%=request.getContextPath()%>/upRoleServlet" method="post" class="main_form" id="loginForm">
        <div class="text_info clearfix"><span>角色名称：</span></div>
        <div class="input_info">
            <input type="text" class="width200" value="${role.rname}" name="roleName" id="roleName" />
            <span class="required">*</span>

        </div>
        <div class="text_info clearfix"><span>设置权限：</span></div>
        <div class="input_info_high">
            <div class="input_info_scroll">
                <ul>
                    <c:forEach items="${powerList}" var="power">
                        <li><input type="checkbox" value="${power.pid}" name="power"
                                <c:forEach items="${role.pidList}" var="pid">
                                    <c:if test="${power.pid==pid}">
                                        checked
                                    </c:if>
                                </c:forEach>
                        />${power.by1}</li>
                    </c:forEach>
                </ul>
            </div>
            <span class="required">*</span>
            <div class="validate_msg_tiny"></div>
        </div>
        <div class="button_info clearfix">
            <input type="hidden" value="${role.rid}" name="rid"/>
            <input type="button" value="保存" class="btn_save" id="goupRoleServlet"/>
            <input type="button" value="取消" class="btn_save" />
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">

</div>
</body>
</html>
