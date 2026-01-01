
<div align="center">
  <img width="300" height="300" alt="Design sem nome" src="https://github.com/user-attachments/assets/fd718139-0812-4c58-82bd-f249dc794539" />
</div>


# 📊 API de Transações — Desafio Backend Itaú

## 🧠 Visão Geral
Esta aplicação expõe endpoints para:
- Registrar transações financeiras
- Limpar todas as transações armazenadas em memória
- Calcular estatísticas das transações ocorridas nos últimos N segundos
- Não utiliza banco de dados
- Todos os dados são armazenados exclusivamente em memória


## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Web**
- **Spring Validation**
- **Spring Actuator**
- **Micrometer**
- **Swagger/OpenAPI**
- **JUnit 5/Mockito**
- **Docker & Docker Compose**
- **Maven**

## 📁 Estrutura do Projeto
```
src/main/java/desafio.itau.springboot
 └── config
 │   ├── OpenApiConfig
 │   └── MetricsConfig
 └── controller
 │   ├── TransactionController
 │   └── StatisticsController
 └── dto
 │   ├── TransactionDTO
 │   └── StatisticsDTO
 └── exception
 │   ├── GlobalExceptionHandler
 │   └── ValorCannotBeLessThanZeroException
 └── model
 │   └── Transaction
 └── service
     ├── TransactionService
     └── StatisticsService

src/test/java/desafio.itau.springboot
 └── (testes unitários)
```

## 🚀 Como Executar o Projeto

✅ Pré-requisitos
- Java21+
- Maven 3.6+
- Docker (Opcional)


### ▶️ Executando Localmente (sem Docker)
```
git clone https://github.com/LucasMedici/desafio-itau-backend.git
cd desafio.itau.springboot
mvn clean install
mvn spring-boot:run

A aplicação estará disponível em:
http://localhost:8080

```
### 🐳 Executando com Docker
```
docker-compose up --build
```

### 📄 Documentação da API (Swagger)
Após subir a aplicação acesse:
```
http://localhost:8080/docs
```

## ⚙️ Configuração do Período de Cálculo
O tempo considerado para cálculo das estatísticas pode ser configurado via application.properties
```
time.to.get.statistics=60
```

## 🧪 Testes Automatizados
Testes unitários implementadas com JUnit5, Mockito e MockMvc
```
mvn test
```

## 📊 Observabilidade & Métricas
A Aplicação conta com Spring Actuator, Micrometer e métricas de tempo via @Timed

Endpoints úteis:
```
/actuator/health
/actuator/metrics
```

## 📜 Logs
Logs estruturados para acompanhar:
- Recebimento de transações
- Processamento de estatísticas
- Limpeza de dados
- Erros e exceções tratadas globalmente

## ❌ Tratamento de Erros
- Validações via services e @Valid
- @ControllerAdvice com GlobalExceptionHandler
- Retorno de erros HTTP conforme especificado no desafio

  
