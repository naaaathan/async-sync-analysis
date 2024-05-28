package hotelapp;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


public class HotelAppSimulation extends Simulation {

    ScenarioBuilder createRoomOccupation = scenario("RoomOccupationScenario")
            .exec(
                    http("Room Occupation Batch Create")
                            .post("/room-occupation/batch/create")
                            .body(StringBody("{\"hotelRoom\": {\"hotelRoomId\": 1}, \"roomOccupationBeginDate\": \"2024-06-01T00:00:00\", \"roomOccupationEndDate\": \"2024-12-31T23:59:59\"}"))
                            .check(status().is(200))
            );


    ScenarioBuilder reserveScenario = scenario("Reserve Scenario")
            .repeat(30, "counter").on(
                    exec(session -> {
                        int counter = session.getInt("counter");
                        String payload = createPayload(counter);
                        return session.set("payload", payload);
                    })
                            .exec(
                                    http("Reserve Request")
                                            .post("/reserve/1")
                                            .body(StringBody("#{payload}"))
                            )
            );


    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8,application/json")
            .header("Content-Type", "application/json")
            .header("Authorization","Basic YWRtaW46cGFzc3dvcmQ=")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0"
            );


    {
        setUp(
                createRoomOccupation.injectOpen(atOnceUsers(1)),
                reserveScenario.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }

    private String createPayload(int counter) {
        String day = String.format("%02d", counter + 1);

        return "{\n" +
                "  \"reserveBegin\": \"2024-06-" + day + "T00:00:00\",\n" +
                "  \"reserveEnd\": \"2024-06-" + day + "T23:59:59\",\n" +
                "  \"hotelRoomId\": 1,\n" +
                "  \"paymentMethod\": {\n" +
                "    \"paymentMethodType\": \"CREDIT_CARD\",\n" +
                "    \"card\": {\n" +
                "      \"number\": \"123456789\",\n" +
                "      \"expirationDate\": \"2020-01-01\",\n" +
                "      \"securityCode\": \"123\",\n" +
                "      \"holderName\": \"John Doe\",\n" +
                "      \"cvv\": \"123\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

}
