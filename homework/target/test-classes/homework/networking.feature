Feature: Networking tests

@ip_check
  Scenario: Public IP is outside forbidden range
    Given retrieve my public IP
    Then it should not be in the range "101.33.28.0" to "101.33.29.0"

@dns_check
  Scenario: Resolve domain to expected IP address
    Given resolve the domain "google-public-dns-a.google.com"
    Then the resolved IP address should be "8.8.8.8"

@traceroute_check
  Scenario: Traceroute to 8.8.8.8 should complete within 10 hops
    Given perform a traceroute to "8.8.8.8"
    Then the target should be reached within 10 hops
