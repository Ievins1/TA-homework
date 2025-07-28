# TASK 1: Sorting Algorithm

Objective:

Implement a sorting algorithm from scratch using a programming language of your choice. Your implementation should not use built-in sorting functions.

1.1 Implement a sorting algorithm (e.g., Merge Sort, Quick Sort, or any other efficient sorting algorithm).

1.2 Your implementation must:§

Not use built-in sorting functions (e.g., sort(), qsort(), or Collections.sort()).

Work correctly and efficiently on large datasets.

# TASK 2: Networking Validation

Retrieve your public IP address by making a request to the IP information API (https://ipinfo.io/ip).

2.1 Write a test to ensure your public IP does not fall within the IP range 101.33.28.0 - 101.33.29.0.

2.2 Write a test to verify that you can resolve the domain google-public-dns-a.google.com to the IP address 8.8.8.8.

2.3 Perform a traceroute to 8.8.8.8 from your local machine. Validate that the target is reached within 10 hops.

Bonus Task (Optional): If you have time, dockerize your solution to allow it to run in a containerized environment.

Additional Notes: The solution should be written using Cucumber with appropriate feature files. Focus on ensuring the tests are/ can be parameterized and modular. If you dockerize the solution, provide a Dockerfile and any additional scripts needed to run the container.