import logging
import threading
import time
import concurrent.futures
import socket
from config import SERVER_PORT, SERVER_ADDRESS, BUF_SIZE

tcp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_server_socket.bind((SERVER_ADDRESS, SERVER_PORT))
tcp_server_socket.listen()

udp_server_socekt = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
udp_server_socekt.bind((SERVER_ADDRESS, SERVER_PORT))

clients = {} #ID, (socket, addr) dict
clients_lock = threading.Lock()

def brodcast_msg(client_id, encoded_msg, protocol, sender_addr=None):
    with clients_lock:
        for id, info in clients.items():
            sock, addr = info
            if protocol == 'tcp' and id != client_id:
                sock.sendall(encoded_msg)
            elif protocol == 'udp' and addr != sender_addr:
                udp_server_socekt.sendto(encoded_msg, addr)

def handle_udp():
    while True:
        buff, address = udp_server_socekt.recvfrom(1024)
        brodcast_msg(None, buff, 'udp', address)

def handle_tcp_client(client_socket, addr):
    #registering new client
    client_id = client_socket.recv(BUF_SIZE).decode()
    print(f'{client_id} has entered the chat from addr: {addr}')
    brodcast_msg(client_id, f'{client_id} has entered the chat'.encode(), 'tcp')

    with clients_lock:
        if client_id in clients:
            print(f'{client_id} already exists. Closing connection...')
            client_socket.sendall('Client already exists'.encode())
            client_socket.close()
            return
        clients[client_id] = (client_socket, addr)

    #receving messages
    while True:
        data = client_socket.recv(BUF_SIZE)
        if not data:
            break
        brodcast_msg(client_id, data, 'tcp')

    client_socket.close()

    #disconnecting client
    print(f'{client_id} has left the chat')
    brodcast_msg(client_id, f'{client_id} has left the chat'.encode(), 'tcp')
    clients.pop(client_id, None)



if __name__ == '__main__':
    print(f'Server is starting at {SERVER_ADDRESS}:{SERVER_PORT}')
    threading.Thread(target=handle_udp).start()
    while True:
        client_socket, addr = tcp_server_socket.accept()
        threading.Thread(target=handle_tcp_client, args=(client_socket, addr, )).start()
