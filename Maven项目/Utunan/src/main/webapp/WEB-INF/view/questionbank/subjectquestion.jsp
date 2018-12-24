<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙程程
  Date1: 2018/12/4
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>好题尽在优图南</title>
    <link rel="shortcut icon" href="/images/common/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/common.css">
</head>
<body>
<%@include file="../common/header.jsp"%>
    <div>
        <h2>当前位置：【${subjectName}】</h2>
    </div>
    <div>
    <c:choose>
        <c:when test="${User==null}">
            <a href="#">开始答题</a>
        </c:when>
        <c:otherwise>
            <span>已解答${resolveQuestionNumber}题</span>
            <a href="#">继续上次答题</a>
            <a href="#">重新开始答题</a>
            <a href="#">查看题目收藏夹</a>
        </c:otherwise>
    </c:choose>

    </div>
    <div>
        <table>
            <tr>
                <td>题号</td>
                <td>章节</td>
                <td>题目内容</td>
                <td>题目类型</td>
                <td>正确率</td>
            </tr>
            <c:forEach items="${questionList}" var="question">
                <tr>
                    <td>${question.questionId}</td>
                    <td>${question.chapterName}</td>
                    <td>${question.questionContent}</td>
                    <td>
                        <c:choose>
                            <c:when test="${question.questionType=='1'}">
                                选择题
                            </c:when>
                            <c:when test="${question.questionType=='2'}">
                                填空题
                            </c:when>
                            <c:otherwise>
                                程序题
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${((question.rightNum)+(question.errorNum))==0}">
                                0
                            </c:when>
                            <c:otherwise>
                                <c:set var="balance" value="${((question.rightNum)/((question.rightNum)+(question.errorNum)))}" />
                                <fmt:formatNumber type="percent" maxIntegerDigits="3"
                                      value="${balance}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <nav id="page" class="page">
            <li class="home"><a href="/subject/${subjectName}/1">首页</a></li>
            <li class="next"><a href="/subject/${subjectName}/${PageInfo.prePage}">上一页</a></li>
            <c:forEach var="i" begin="${PageInfo.navigateFirstPage}" end="${PageInfo.navigateLastPage}">
                <li class="pagenum"><a name="${i}" href="/subject/${subjectName}/${i}">${i}</a></li>
            </c:forEach>
            <c:choose>
                <c:when test="${PageInfo.nextPage==0}">
                    <li class="next"><a href="/subject/${subjectName}/${PageInfo.pages}">下一页</a></li>
                </c:when>
                <c:otherwise>
                    <li class="next"><a href="/subject/${subjectName}/${PageInfo.nextPage}">下一页</a></li>
                </c:otherwise>
            </c:choose>
            <li class="tail"><a href="/subject/${subjectName}/${PageInfo.pages}">尾页</a></li>
        </nav>
    </div>
<%@include file="../common/footer.jsp"%>
</body>
</html>
