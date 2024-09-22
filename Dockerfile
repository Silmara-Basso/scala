# Use a base image com OpenJDK 11
FROM openjdk:11-jre-slim

# Instale dependências necessárias
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    sudo \
    curl \
    gnupg2 \
    && rm -rf /var/lib/apt/lists/*

# Baixe e instale o Scala 3.4.2
RUN wget https://github.com/scala/scala3/releases/download/3.4.2/scala3-3.4.2.zip \
    && unzip scala3-3.4.2.zip \
    && rm scala3-3.4.2.zip \
    && mv scala3-3.4.2 /usr/local/scala

# Adicione o Scala ao PATH
ENV PATH="/usr/local/scala/bin:${PATH}"

# Adicione o repositório do SBT e instale o SBT
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list \
    && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x99E82A75642AC823" | sudo apt-key add \
    && apt-get update \
    && apt-get install -y sbt

# Defina o diretório de trabalho
WORKDIR /scripts

# Copie os scripts Scala para o diretório de trabalho
COPY . /scripts

# Comando padrão para executar o SBT
CMD ["sbt"]
