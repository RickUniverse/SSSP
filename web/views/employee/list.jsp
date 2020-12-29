<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Employee</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-1.8.3.js"></script>

    <script>
        $(function () {
            $('.deleteEmp').click(function () {
                var name = $(this).next(':hidden').val();
                var flag = confirm("确定要删除："+name+"吗？");
                if (!flag) {
                    return;
                }
                $('#_FORM').attr('action',"${pageContext.request.contextPath}/emp/"+$(this).attr('empId')+"");
                $('#_FORM').children(':hidden').val('DELETE');

                $('#_FORM').submit();
            })
        })
    </script>
</head>
<body>
    <form action="" method="post" id="_FORM">
        <input type="hidden" name="_method" value="">
    </form>
    <c:if test="${requestScope.page.content == null || requestScope.page.content.size() == 0}">
        No anly Employee
    </c:if>
    <c:if test="${requestScope.page.content != null && requestScope.page.content.size() >= 0}">
        <table cellpadding="10" cellspacing="0" border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>birth</th>
                <th>createTime</th>
                <th>department</th>
                <th>edit</th>
                <th>delete</th>
            </tr>
            <c:forEach items="${requestScope.page.content}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>
                        <fmt:formatDate value="${emp.birth}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${emp.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                    </td>
                    <td>${emp.department.name}</td>
                    <td>
                        <a href="emp/${emp.id}">edit</a>
                    </td>
                    <td>
                        <a class="deleteEmp" href="javascript:;" empId="${emp.id}">delete</a>
                        <input type="hidden" name="hidName" value="${emp.name}">
                    </td>
                </tr>
            </c:forEach>
            <tr>
               <td colspan="7">
                   共${page.totalElements}条数据
                   共${page.totalPages}页
                   当前页数据：${page.numberOfElements}
                   当前页:${page.number + 1}
                    <a href="?pageNo=${page.number + 1- 1}">上一页</a>
                    <a href="?pageNo=${page.number + 1 + 1}">下一页</a>
               </td>
            </tr>
        </table>
    </c:if>
    <br>
    <a href="input">Add Employee</a>
</body>
</html>
