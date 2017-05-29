<html>
<head><title>Flights Online</title>

<body>
<div id="header">
    <a href="/">
        <H2>
            Flights Online
        </H2>
    </a>
</div>
<div id="content">

<#--ERROR MESSAGE-->
<#if error??>
<div class="red" ><h3>${error}</h3></div>
</#if>
<#if message??>
<div class="red" ><h3>${message}</h3></div>
</#if>

<h5>Please login</h5>
    <form name="login" action="login" method="post">
        <input type="text" name="username" value=""/>
        <input type="text" name="password" value=""/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Log in"/>
    </form>

</div>
</body>
</html>

<link href="/resources/css/main.css" rel="stylesheet"/>