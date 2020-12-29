<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#validateName').change(function () {
                var name = $(this).val();
                name = $.trim(name);
                $(this).val(name);
                var oldName = $.trim($("#oldName").val());
                if (oldName!=null && oldName!="" && oldName == name) {
                    alert("用户名可用")
                    return;
                }
                $.post("${pageContext.request.contextPath}/validateName",{'name':name},function (data) {
                    if (data == 0) {
                        alert("用户名可用")
                    } else {
                        alert("用户名已存在")
                    }
                })
            })
        })
    </script>
</head>
<body>
    <c:set value="${pageContext.request.contextPath}/emp" var="url"></c:set>
    <c:if test="${emp.id != null}">
        <c:set value="${pageContext.request.contextPath}/emp/${emp.id}" var="url"></c:set>
    </c:if>
    <form:form action="${url}" method="post" modelAttribute="emp" >
        <c:if test="${emp.id != null}">
            <input type="hidden" id="oldName" value="${emp.name}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        Name: <form:input path="name" id="validateName"/>
        <br>
<%--        Birth: <form:input path="birth"/>--%>
        <br>
<%--        CreateTime: <form:input path="createTime"/>--%>
        <br>
        Department: <form:select path="department.id"
                                 items="${deps}"
                                 itemLabel="name"
                                 itemValue="id"/>
        <br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
