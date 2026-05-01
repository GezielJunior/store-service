# Store Service 🚗

Um microserviço de vendas de automóveis construído com **Micronaut 4.10** em **Groovy**, especializado em gerenciar transações de vendas de veículos integrando-se com um serviço externo de catálogo de veículos.

## 📋 Pré-requisitos

- **Java 25+** instalado
- **Gradle 8.0+** (ou usar o gradle wrapper incluído)
- **Maven** (opcional, para compilação AOT)
- Um serviço de veículos rodando em `http://localhost:8080` (configurável)

## 🚀 Quick Start

### 1. Clone e instale as dependências

```bash
git clone <seu-repositorio>
cd store-service
```

### 2. Execute o projeto

```bash
./gradlew run
```

O serviço estará disponível em `http://localhost:8081`

### 3. Teste o endpoint

```bash
curl -X POST http://localhost:8081/sales \
  -H "Content-Type: application/json" \
  -d '{
    "client": "João Silva",
    "vehicle": 1,
    "value": 50000.00,
    "installmentsQty": 12
  }'
```

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── groovy/br/com/carstore/
│   │   ├── Application.groovy              # Entrada da aplicação
│   │   ├── controller/
│   │   │   └── SalesController.groovy      # Endpoints REST
│   │   ├── service/
│   │   │   └── SalesService.groovy         # Lógica de negócios
│   │   ├── client/
│   │   │   └── VehicleClient.groovy        # Cliente HTTP para serviço externo
│   │   └── dto/
│   │       ├── input/
│   │       │   └── InputSalesDTO.groovy    # Payload de entrada
│   │       └── output/
│   │           └── VehicleDTO.groovy       # Resposta do serviço externo
│   └── resources/
│       ├── application.properties           # Configurações
│       └── logback.xml                      # Logging
└── test/
    └── groovy/br/com/carstore/
        └── StoreServiceTest.groovy          # Testes unitários
```

## 🔌 Endpoints

### POST `/sales`
Registra uma nova venda de veículo.

**Request:**
```json
{
  "client": "string",           // Nome do cliente
  "vehicle": "integer",         // ID do veículo
  "value": "decimal",           // Valor da venda
  "installmentsQty": "integer"  // Quantidade de parcelas
}
```

**Response:**
- `204 No Content` - Venda registrada com sucesso
- `4xx/5xx` - Erro na validação ou comunicação com serviço externo

**Exemplo:**
```bash
curl -X POST http://localhost:8081/sales \
  -H "Content-Type: application/json" \
  -d '{
    "client": "Maria Santos",
    "vehicle": 5,
    "value": 85000.50,
    "installmentsQty": 24
  }'
```

## ⚙️ Configuração

### Arquivo: `src/main/resources/application.properties`

| Propriedade | Padrão | Descrição |
|---|---|---|
| `micronaut.application.name` | `store-service` | Nome da aplicação |
| `micronaut.server.port` | `8081` | Porta do servidor |
| `external.service.vehicle.url` | `http://localhost:8080` | URL do serviço de veículos |

### Variáveis de Ambiente

Você pode sobrescrever as propriedades usando variáveis de ambiente:

```bash
MICRONAUT_SERVER_PORT=9000 ./gradlew run
EXTERNAL_SERVICE_VEHICLE_URL=http://vehicle-service:8080 ./gradlew run
```

## 🧪 Testes

### Executar todos os testes

```bash
./gradlew test
```

### Executar teste específico

```bash
./gradlew test --tests StoreServiceTest
```

### Executar com cobertura

```bash
./gradlew test jacocoTestReport
```

## 🛠️ Build

### Build padrão

```bash
./gradlew clean build
```

### Build com AOT (Ahead-of-Time)

```bash
./gradlew aotOptimize
```

### Native Image (GraalVM)

```bash
./gradlew nativeImage
```

### Docker Image (Native)

```bash
./gradlew dockerfileNative
```

## 📊 Logging

O projeto utiliza **SLF4J** com **Logback**. Configure o nível de logs em `src/main/resources/logback.xml`.

Logs padrão:
```
[main] INFO  br.com.carstore.service.SalesService - {id=..., model=..., brand=..., licensePlate=...}
```

## 🏗️ Arquitetura

A aplicação segue uma **arquitetura em camadas**:

```
HTTP Request
    ↓
SalesController (HTTP layer)
    ↓
SalesService (Business logic)
    ↓
VehicleClient (External integration)
    ↓
Vehicle Service (External API)
```

### Padrões Utilizados

- **Dependency Injection**: Micronaut Container (Jakarta)
- **Serialização**: Jackson via Micronaut Serde
- **Client HTTP**: Micronaut HTTP Client (Declarativo)
- **Singleton**: SalesService
- **Groovy Static Compilation**: `@CompileStatic` para performance

## 🔄 Integração com Serviço Externo

O `VehicleClient` faz requisições para um serviço externo de veículos:

```
GET http://localhost:8080/vehicles/{id}
```

**Resposta esperada:**
```json
{
  "id": 1,
  "model": "Civic",
  "brand": "Honda",
  "licensePlate": "ABC1234"
}
```

## 📝 Desenvolvimento

### Adicionar nova dependência

```bash
./gradlew dependencies
```

### Formato de código

O projeto segue as convenções Groovy. Execute:

```bash
./gradlew check
```

### Hot Reload

Durante desenvolvimento, você pode usar:

```bash
./gradlew run --continuous
```

## 📚 Documentação Completa

- [Micronaut 4.10 Documentation](https://docs.micronaut.io/4.10.13/guide/index.html)
- [Groovy Language](https://groovy-lang.org/documentation.html)
- [Java 25 Features](https://www.oracle.com/java/technologies/javase/25-relnotes.html)

## 🤝 Contribuindo

1. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
2. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
3. Push para a branch (`git push origin feature/AmazingFeature`)
4. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a MIT License - veja o arquivo LICENSE para mais detalhes.

## 👨‍💻 Autor

**CarStore Development Team**

---

**Última atualização:** Maio 2026
