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

    <h3>Time: ${time}</h3>

    <h3>Total Number: ${number}</h3>

    <br/>

    <div>
        <div>
            <h5>Select by:</h5>
            <table class="datatable">
                <tr>
                    <form name="flightsFromOpenSky" action="showByIcao24" method="get">
                        <td><input type="text" name="icao24" value=""/></td>
                        <td><input type="submit" value="Show by Icao24"/></td>
                    </form>
                    <form name="flightsFromOpenSky" action="showByOriginCountry" method="get">
                        <td><input type="text" name="originCountry" value=""/></td>
                        <td><input type="submit" value="Show by Origin Country"/></td>
                    </form>
                    <form name="flightsFromOpenSky" action="showByOnGround" method="get">
                        <td><input type="text" name="onGround" value=""/></td>
                        <td><input type="submit" value="Show by On Ground"/></td>
                    </form>
                    <form name="flightsFromOpenSky" action="showByCallsign" method="get">
                        <td><input type="text" name="callsign" value=""/></td>
                        <td><input type="submit" value="Show by Call Sign"/></td>
                    </form>

                </tr>
            </table>
        </div>
        <div>
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
    </div>


</div>
</body>
</html>

<link href="/resources/css/main.css" rel="stylesheet"/>