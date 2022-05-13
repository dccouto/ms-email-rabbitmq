# ms-email-rabbitmq
Projeto de estudo sobre microserviços utilizando rabbitmq 

## Comando para criar fila SQS AWS
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name ms.email

## Comando para enviar mensagem via aws-cli
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/ms.email --message-body "{\"ownerRef\": \"Diego\",\"from\": \"diegocorreacouto@gmail.com\",\"to\": \"die_go0107@yahoo.com.br\",\"subject\": \"Teste envio de email - AWS\",\"text\": \"Enviando via Aws\"}"

