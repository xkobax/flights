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

    <input type="button" onclick="location.href='/list'" value="View Current Flights">
    <input type="button" onclick="location.href='/alwaysFreshList'" value="View Fresh Current Flights">

</div>
</body>
</html>

<link href="/resources/css/main.css" rel="stylesheet"/>