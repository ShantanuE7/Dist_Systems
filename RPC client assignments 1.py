import xmlrpc.client

# Connect to the remote server (replace with your lab computer's IP address)
server = xmlrpc.client.ServerProxy("http://172.16.10.7:8000")  # Replace <lab_computer_ip> with actual IP

# Call remote functions
input1=int(input("Enter a number for factorial"))
result_add = server.factorial(input1)
# result_subtract = server.subtract(10, 4)

# Print the results
print(f"factorial result: {result_add}")

