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
<#if errorMsg??>
<div class="red" ><h3>${errorMsg}</h3></div>
</#if>

<h5>Please login</h5>
    <form name="login" action="login" method="post">
        <input type="text" name="name" value=""/>
        <input type="text" name="password" value=""/>
        <input type="submit" value="Log in"/>
    </form>

</div>
</body>
</html>

<link href="/resources/css/main.css" rel="stylesheet"/>