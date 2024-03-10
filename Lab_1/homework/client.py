import threading
import socket
import sys
from config import SERVER_PORT, SERVER_ADDRESS, BUF_SIZE, MULTI_GROUP, MULTI_PORT
import select
import struct

class Chat:
    def __init__(self, client_id):
        self.conn = True
        self.client_id = client_id

        self.tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.tcp_socket.connect((SERVER_ADDRESS, SERVER_PORT))

        self.udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.udp_socket.bind(self.tcp_socket.getsockname())

        self.udp_multi = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
        self.udp_multi.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.udp_multi.bind(('', MULTI_PORT))
        mreq = struct.pack("4sl", socket.inet_aton(MULTI_GROUP), socket.INADDR_ANY)
        self.udp_multi.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

        print(f'Connecting to server at {SERVER_ADDRESS}:{SERVER_PORT}')
        threading.Thread(target=self.chat_sending).start()
        threading.Thread(target=self.udp_receive, args=(self.udp_socket, )).start()
        threading.Thread(target=self.udp_receive, args=(self.udp_multi, )).start()
        self.tcp_chat_receive()

    def close_connection(self):
        print("Closing connection...")
        self.conn = False
        self.tcp_socket.close()

    def tcp_chat_receive(self):
        while True:
            ready_to_read, _, _ = select.select([self.tcp_socket], [], [], 3) #ready_to_read, ready_to_write, in_error
            if self.tcp_socket in ready_to_read:
                try:
                    data = self.tcp_socket.recv(BUF_SIZE)
                    data_decoded = data.decode()

                    if "already exists" in data_decoded:
                        print(f'{self.client_id} already exists.')
                        self.close_connection()
                        break
                    elif not data:
                        self.close_connection()
                        break
                    print(f'\n{data_decoded}\nEnter message: ',end="")

                except OSError:
                    break

            if not self.conn:
                break

    def udp_receive(self, sock):
        while True:
            ready_to_read, _, _ = select.select([sock], [], [], 3) #ready_to_read, ready_to_write, in_error
            if sock in ready_to_read:
                buff, _ = sock.recvfrom(1024)
                print(f"\n{buff.decode()}\nEnter message: ",end="")
            if self.conn == False:
                break

    def chat_sending(self):
        self.tcp_socket.sendall(self.client_id.encode())
        while True:
            message = input('Enter message: ')
            if '/exit' in message or self.conn == False:
                if self.conn:
                    self.close_connection()
                break
            elif message == 'U':
                with open('ascii.txt', 'r') as f:
                    ascii_hitman = f.read()
                    ascii_hitman = f"Image from {self.client_id}:\n{ascii_hitman}"
                    print("seding ascii")
                    self.udp_socket.sendto(ascii_hitman.encode(), (SERVER_ADDRESS, SERVER_PORT))
            elif message == 'M':
                sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
                sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, 1)
                message = b'This is the multicast message.'
                sock.sendto(message, (MULTI_GROUP, MULTI_PORT))

            else:
                message = f"Message from {self.client_id}: " + message
                self.tcp_socket.sendall(message.encode())



if __name__ == '__main__':
    if len(sys.argv) == 2:
        client_id = sys.argv[1]
    else:
        print("Usage: python client.py <client_id>")
        exit(1)
    print(f'Client: {client_id} is starting...')
    Chat(client_id)
