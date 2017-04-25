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


<#if !loggedIn>
    <input type="button" onclick="location.href='/loginPage'" value="Log in">
<#else>
    <input type="button" onclick="location.href='/list'" value="View Current Flights">
    <input type="button" onclick="location.href='/alwaysFreshList'" value="View Fresh Current Flights">
<#--SHOULD BE FORM WITH METHOD POST-->
    <form name="logout" action="logout" method="post">
        <input type="submit" value="Log Out"/>
    </form>
</#if>

    <br/>
    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>Origin Country</th>
            <th>On Ground</th>
            <th>Call Sign</th>
        </tr>
    <#list flights as flight>
        <tr>
            <form name="flightsFromOpenSky" action="persistFlight" method="post">
                <td><input type="text" name="icao24" value="${flight.icao24}"/></td>
                <td><input type="text" name="originCountry" value="${flight.originCountry}"/></td>
                <td><input type="text" name="onGround" value="${flight.onGround?c}"/></td>
                <td><input type="text" name="callsign" value="${flight.callsign}"/></td>
            </form>
        </tr>
    </#list>
    </table>


</div>
</body>
</html>

<link href="/resources/css/main.css" rel="stylesheet"/>