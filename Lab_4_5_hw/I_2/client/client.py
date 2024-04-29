import subprocess
import os

# Run the ls command
result = subprocess.run(['ls'], capture_output=True, text=True)

# Check the return code
if result.returncode == 0:
    # Print the output
    print(result.stdout)
else:
    # Print the error message
    print(result.stderr)

def run_command(rpc, args):
    print(str(args))
    args = args.replace("'", '"')
    result = subprocess.run(['grpcurl', '-plaintext', '-d', args, 'localhost:50051', rpc], capture_output=True, text=True)
    if result.returncode == 0:
        return result.stdout
    else:
        return result.stderr

if __name__ == '__main__':
    print('Client is running')
    while True:
        print("Available commands: add, subtract, many, exit")
        command = input("Enter command: ")
        if command == 'exit':
            break
        elif command == 'add' or command == 'subtract':
            num1 = input("Enter first number: ")
            num2 = input("Enter second number: ")
            print(run_command(f'calculator.Calculator.{command.capitalize()}', f'{{"arg1":{num1}, "arg2":{num2}}}'))
        elif command == 'many':
            operations = []
            while True:
                operation = input("Enter operation (+,-,*,/,fin): ")
                if operation == '+':
                    arg1 = input("Enter first number: ")
                    arg2 = input("Enter second number: ")
                    operations.append({"operation": "+", "arguments": {"arg1": float(arg1), "arg2": float(arg2)}})
                elif operation == '-':
                    arg1 = input("Enter first number: ")
                    arg2 = input("Enter second number: ")
                    operations.append({"operation": "-", "arguments": {"arg1": float(arg1), "arg2": float(arg2)}})
                elif operation == '*':
                    arg1 = input("Enter first number: ")
                    arg2 = input("Enter second number: ")
                    operations.append({"operation": "*", "arguments": {"arg1": float(arg1), "arg2": float(arg2)}})
                elif operation == '/':
                    arg1 = input("Enter first number: ")
                    arg2 = input("Enter second number: ")
                    operations.append({"operation": "/", "arguments": {"arg1": float(arg1), "arg2": float(arg2)}})
                elif operation == 'fin':
                    break
                else:
                    print("Invalid operation")
                    break
            os.system('clear')
            print(run_command('calculator.Calculator.ManyArithmeticOperations', f'{{"operations":{operations}}}'))
        else:
            print("Invalid command")
