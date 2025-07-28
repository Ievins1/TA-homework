package homework;

import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class StepDefinitions {

    // Stores the retrieved public IP address
    String publicIP;

    // Stores the resolved IP address of a given domain
    String resolvedIP;

    // Stores the number of hops taken to reach a target via traceroute
    int hopsTaken = Integer.MAX_VALUE;

    @Given("retrieve my public IP")
    public void i_retrieve_my_public_ip() throws Exception {
        // Retrieves the public IP using ipinfo.io service
        URL url = new URL("https://ipinfo.io/ip");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        publicIP = in.readLine().trim(); // Reads and stores public IP
        in.close();
    }

    @Then("it should not be in the range {string} to {string}")
    public void validate_ip_range(String startIp, String endIp) {
        // Converts IP addresses to long integers for comparison
        long ipLong = ipToLong(publicIP);
        long start = ipToLong(startIp);
        long end = ipToLong(endIp);

        // Asserts that the public IP is outside the forbidden range
        assertThat(ipLong < start || ipLong > end)
            .as("IP is in forbidden range")
            .isTrue();
    }

    // Helper method to convert an IP address from string to long format
    private long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;
        for (String part : parts) {
            result = result * 256 + Integer.parseInt(part);
        }
        return result;
    }

    @Given("resolve the domain {string}")
    // Resolves the IP address of a given domain name
    public void i_resolve_the_domain(String domain) throws Exception {
        InetAddress address = InetAddress.getByName(domain);
        resolvedIP = address.getHostAddress();
    }

    @Then("the resolved IP address should be {string}")
    public void the_resolved_ip_should_be(String expectedIP) {
        // Verifies that the resolved IP matches the expected IP
        assertThat(resolvedIP)
            .as("Resolved IP did not match expected")
            .isEqualTo(expectedIP);
    }

    @Given("perform a traceroute to {string}")
    public void i_perform_a_traceroute_to(String targetIP) throws Exception {
        // Runs a traceroute command with a max of 10 hops to the target IP
        ProcessBuilder pb = new ProcessBuilder("traceroute", "-m", "10", targetIP);
        pb.redirectErrorStream(true); // Merges error and output streams
        Process process = pb.start();

        // Reads traceroute output and counts the number of hops
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        int hopCount = 0;
        while ((line = reader.readLine()) != null) {
            hopCount++;
            if (line.contains(targetIP)) {
                hopsTaken = hopCount; // Stop when target IP is reached
                break;
            }
        }
        reader.close();
        process.waitFor(); // Waits for the traceroute process to finish
    }

    @Then("the target should be reached within {int} hops")
    public void the_target_should_be_reached_within_hops(Integer maxHops) {
        // Asserts that the target was reached within the maximum allowed hops
        assertThat(hopsTaken)
            .as("Target was not reached within " + maxHops + " hops")
            .isLessThanOrEqualTo(maxHops);
    }
}
