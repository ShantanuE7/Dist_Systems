import random
from collections import deque

class Server:
    def __init__(self, name):
        self.name = name
        self.load = 0  # Number of active connections
    
    def process_request(self):
        self.load += 1  # Simulating request handling
    
    def complete_request(self):
        if self.load > 0:
            self.load -= 1
    
    def __str__(self):
        return f"{self.name} (Load: {self.load})"

class LoadBalancer:
    def __init__(self, servers, strategy='round_robin'):
        self.servers = servers
        self.strategy = strategy
        self.queue = deque(servers)  # Used for round robin
    
    def distribute_request(self):
        if self.strategy == 'round_robin':
            return self.round_robin()
        elif self.strategy == 'least_connections':
            return self.least_connections()
        else:
            raise ValueError("Unknown strategy")
    
    def round_robin(self):
        server = self.queue.popleft()
        server.process_request()
        self.queue.append(server)
        return server
    
    def least_connections(self):
        server = min(self.servers, key=lambda s: s.load)
        server.process_request()
        return server
    


# Example usage
if __name__ == "__main__":
    servers = [Server(f"Server-{i+1}") for i in range(3)]
    strategies = ['round_robin', 'least_connections']
    
    for strategy in strategies:
        print(f"\nTesting Load Balancer with {strategy} strategy:")
        lb = LoadBalancer(servers, strategy)
        for _ in range(10):  # Simulating 10 client requests
            selected_server = lb.distribute_request()
            print(f"Request sent to {selected_server}")
