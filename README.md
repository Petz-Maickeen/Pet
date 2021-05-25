# Pet

Executar o comando "mvn install" para criar o arquivo .jar no diretório.

Executar o comando "docker build -t pet-spring ." para gerar a imagem Docker.

Copiar o [arquivo](https://github.com/Petz-Maickeen/Documentacao/blob/main/Script%20Docker%20Compose/bd.env) para o diretório.

Executar o comando "docker run -p 8080:8080 --env-file bd.env pet-spring" para executar a imagem Docker.
