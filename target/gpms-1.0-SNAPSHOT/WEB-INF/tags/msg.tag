<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="msgObj"
             required="true" type="com.hncu.common.Msg"  %>
<c:if test="${not empty msgObj}">
    <c:if test="${not empty msgObj.content && not empty msgObj.type}">
        <div class="alert alert-success">
            <span class="glyphicon glyphicon-${msgObj.type}"></span>&nbsp;&nbsp;${msgObj.content}<span id="close-alert" class="close">&times;</span>
            <script type="text/javascript">
                $("#close-alert").click(function(){
                    $(".alert").remove();
                });
                setInterval(function(){
                 $(".alert").remove();
                 },3000);
            </script>
        </div>
    </c:if>
</c:if>