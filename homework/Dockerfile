# Use official Maven image with JDK 17
FROM maven:3.9.4-eclipse-temurin-17

# Install traceroute and clean up to reduce image size
RUN apt-get update && \
    apt-get install -y traceroute && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy project files into container
COPY . .

# Default command: run tests
CMD ["mvn", "clean", "test"]
