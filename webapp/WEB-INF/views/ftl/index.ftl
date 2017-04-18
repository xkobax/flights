<html>
<head><title> FreeMarker Spring MVC Hello World</title>

<body>
<div id="content">

    <h3>Time: ${time}</h3>

    <h3>Total Number: ${number}</h3>

    <br/>
    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>Origin Country</th>
            <th>On Ground</th>
            <th>Call Sign</th>
            <th></th>
        </tr>
    <#list flights as flight>
        <tr>
                <form name="flightsFromOpenSky" action="persistFlight" method="post">
                    <td><input type="text" name="icao24" value="${flight.icao24}"/></td>
                    <td><input type="text" name="originCountry" value="${flight.originCountry}"/></td>
                    <td><input type="text" name="onGround" value="${flight.onGround?c}"/></td>
                    <td><input type="text" name="callsign" value="${flight.callsign}"/></td>
                    <td><input type="submit" value="Save"/></td>
                </form>
        </tr>
    </#list>
    </table>

</div>

<div id="header">
    <H2>
        Flights Online
    </H2>
</div>
</body>
</html>

<link href="/resources/css/main.css" rel="stylesheet" />