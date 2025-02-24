import xmlrpc.server

def factorial(a):
    print("call zala")
    fact=1
    i=a
    while(i>0):
        fact=fact*i
        i=i-1
    return fact

server=xmlrpc.server.DocXMLRPCServer(("0.0.0.0",8000))
server.register_function(factorial,"factorial")

print("server started")
server.serve_forever()
                                         