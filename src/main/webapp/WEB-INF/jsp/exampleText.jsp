<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="i18n.text">
    <head>
        <title>exampleText.jsp</title>

    </head>
    <body>
    <p><fmt:message key="default.inputText"></fmt:message></p>

    <form action="${pageContext.request.contextPath}/do/repeatText" method="post">
        <textarea rows="15" cols="200" name="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus
            fringilla lectus eu lectus adipiscing pretium. In hac habitasse platea dictumst. Donec turpis tellus,
            tincidunt sed orci et, venenatis tempor orci. Quisque bibendum pharetra felis et rutrum. Nam luctus leo non
            tristique auctor. Ut molestie imperdiet lectus, sit amet faucibus felis lobortis feugiat. Curabitur laoreet
            ultrices arcu ac aliquam. Nunc tristique vestibulum lorem iaculis aliquet. Pellentesque imperdiet metus quis
            ante hendrerit pretium. Nullam eget dapibus libero, in sollicitudin arcu. Pellentesque malesuada scelerisque
            risus sit amet lobortis. Pellentesque sagittis eros at felis sollicitudin, at euismod justo hendrerit.
            Nam elit nibh, commodo eu lorem a, dapibus tristique orci. Vestibulum aliquet, elit in commodo tristique,
            justo ipsum sagittis metus, ut malesuada diam massa vel nisi. Pellentesque condimentum scelerisque augue.
            Curabitur felis massa, rutrum in fermentum in, adipiscing in arcu. Cras hendrerit consequat auctor. Morbi at
            volutpat massa. Duis eleifend purus eu ullamcorper viverra. Praesent at volutpat felis. Curabitur lacus
            enim, scelerisque scelerisque urna quis, posuere faucibus massa. Donec hendrerit ligula nisi, id interdum
            justo bibendum quis. Maecenas eget commodo libero.
        </textarea>
        <button name="action" value="RepeatTextAction" type="submit" class="btn"><fmt:message
                key="default.submit"/></button>
    </form>
    </body>
</fmt:bundle>
</html>