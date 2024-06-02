In order to run a project:
0. Open folder in InteliJ IDEA
0.1. Add rabbitmq dependencies (located in lib folder):
    File -> Project Structure -> Modules -> Dependencies -> "+" -> JARs or directories -> select lib folder -> apply -> OK
1. Run a rabbitmq server
    # latest RabbitMQ 3.13
    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
2. Run configurations:
    Technician Knee Hip
    Technician Knee Elbow
    Doctor Edgar
    Doctor Filip
    Administrator

