package homework;

import io.cucumber.java.en.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class StepDefinitions {

    String publicIP;
    String resolvedIP;
    int hopsTaken = Integer.MAX_VALUE;

    @Given("retrieve my public IP")
    public void i_retrieve_my_public_ip() throws Exception {
        URL url = new URL("https://ipinfo.io/ip");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        publicIP = in.readLine().trim();
        in.close();
    }

    @Then("it should not be in the range {string} to {string}")
    public void validate_ip_range(String startIp, String endIp) {
        long ipLong = ipToLong(publicIP);
        long start = ipToLong(startIp);
        long end = ipToLong(endIp);
        assertThat(ipLong < start || ipLong > end)
            .as("IP is in forbidden range")
            .isTrue();
    }

    private long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;
        for (String part : parts) {
            result = result * 256 + Integer.parseInt(part);
        }
        return result;
    }

    @Given("resolve the domain {string}")
    public void i_resolve_the_domain(String domain) throws Exception {
        InetAddress address = InetAddress.getByName(domain);
        resolvedIP = address.getHostAddress();
    }

    @Then("the resolved IP address should be {string}")
    public void the_resolved_ip_should_be(String expectedIP) {
        assertThat(resolvedIP)
            .as("Resolved IP did not match expected")
            .isEqualTo(expectedIP);
    }

    @Given("perform a traceroute to {string}")
    public void i_perform_a_traceroute_to(String targetIP) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("traceroute", "-m", "10", targetIP);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        int hopCount = 0;
        while ((line = reader.readLine()) != null) {
            hopCount++;
            if (line.contains(targetIP)) {
                hopsTaken = hopCount;
                break;
            }
        }
        reader.close();
        process.waitFor();
    }

    @Then("the target should be reached within {int} hops")
    public void the_target_should_be_reached_within_hops(Integer maxHops) {
        assertThat(hopsTaken)
            .as("Target was not reached within " + maxHops + " hops")
            .isLessThanOrEqualTo(maxHops);
    }
}
