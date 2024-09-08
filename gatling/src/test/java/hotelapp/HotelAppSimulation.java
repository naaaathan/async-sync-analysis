package hotelapp;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.util.concurrent.ThreadLocalRandom;

public class HotelAppSimulation extends Simulation {

    ScenarioBuilder createRoomOccupation = scenario("RoomOccupationScenario")
            .repeat(5, "counter").on(
                    exec(session -> {
                        int counter = session.getInt("counter");
                        int hotelRoomId = (counter % 5) + 1; // Assuming 5 rooms
                        String payload = createRoomOccupationPayload(hotelRoomId);
                        return session.set("payload", payload).set("hotelRoomId", hotelRoomId);
                    })
                            .exec(
                                    http("Room Occupation Batch Create")
                                            .post("/room-occupation/batch/create")
                                            .body(StringBody("#{payload}"))
                                            .check(status().in(200))
                            )
            );

    ScenarioBuilder reserveScenario = scenario("Reserve Scenario")
            .exec(session -> {
                int hotelRoomId = ThreadLocalRandom.current().nextInt(1, 6); // Assuming 5 rooms
                int userId = ThreadLocalRandom.current().nextInt(1, 6); // Assuming 5 users
                String payload = createPayload(hotelRoomId, userId);
                return session.set("payload", payload).set("hotelRoomId", hotelRoomId).set("userId", userId);
            })
            .exec(
                    http("Reserve Request")
                            .post("/reserve/#{userId}")
                            .body(StringBody("#{payload}"))
                            .check(status().in(200,201,409))
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
                createRoomOccupation.injectOpen(atOnceUsers(1))
                        .andThen(
                                reserveScenario.injectOpen(atOnceUsers(100),
                                        rampUsers(500).during(20),
                                        constantUsersPerSec(50).during(60)
                                ))
        ).protocols(httpProtocol);
    }

    private String createRoomOccupationPayload(int hotelRoomId) {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append("[\n");

        for (int month = 1; month <= 12; month++) {
            int daysInMonth = getDaysInMonth(month);
            for (int day = 1; day <= daysInMonth; day++) {
                payloadBuilder.append("{\n")
                        .append("  \"hotelRoom\": {\"hotelRoomId\": ").append(hotelRoomId).append("},\n")
                        .append("  \"roomOccupationBeginDate\": \"2024-")
                        .append(String.format("%02d", month)).append("-")
                        .append(String.format("%02d", day)).append("T00:00:00\",\n")
                        .append("  \"roomOccupationEndDate\": \"2024-")
                        .append(String.format("%02d", month)).append("-")
                        .append(String.format("%02d", day)).append("T23:59:59\"\n")
                        .append("},\n");
            }
        }

        // Remove the last comma and newline
        payloadBuilder.setLength(payloadBuilder.length() - 2);
        payloadBuilder.append("\n]");

        return payloadBuilder.toString();
    }

    private int getDaysInMonth(int month) {
        switch (month) {
            case 2:
                return 28; // Not considering leap years for simplicity
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    private String createPayload(int hotelRoomId, int userId) {
        int month = ThreadLocalRandom.current().nextInt(1, 13); // Random month between 1 and 12
        int day = ThreadLocalRandom.current().nextInt(1, 29); // Random day between 1 and 28 to avoid invalid dates

        return "{\n" +
                "  \"reserveBegin\": \"2024-" + String.format("%02d", month) + "-" + String.format("%02d", day) + "T00:00:00\",\n" +
                "  \"reserveEnd\": \"2024-" + String.format("%02d", month) + "-" + String.format("%02d", day) + "T23:59:59\",\n" +
                "  \"hotelRoomId\": " + hotelRoomId + ",\n" +
                "  \"userId\": " + userId + ",\n" +
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