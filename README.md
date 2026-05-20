
# 💈 BarberConnect

O **BarberConnect** é uma solução Full-Stack moderna para o gerenciamento de barbearias, permitindo agendamentos ágeis de serviços e profissionais, com uma interface focada em experiência do usuário (UX) e uma API robusta e escalável.

## 🚀 Tecnologias Utilizadas

### Back-end

* **Java 21** com **Spring Boot 3.x**
* **Spring Data JPA / Hibernate** (Persistência)
* **PostgreSQL** (via Supabase)
* **Maven** (Gerenciamento de dependências)

### Front-end

* **React** com **TypeScript**
* **Tailwind CSS** (Estilização industrial)
* **Lucide React** (Biblioteca de ícones)
* **Vite** (Build tool de alta performance)

---

## 🛠️ Funcionalidades

* [x] **Gestão de Profissionais:** Cadastro e listagem dinâmica de barbeiros.
* [x] **Catálogo de Serviços:** Configuração de preços e duração dos cortes.
* [x] **Agendamento Inteligente:** Fluxo de reserva com validação de dados em tempo real.
* [x] **Arquitetura Desacoplada:** API RESTful que permite integração com qualquer front-end.
* [x] **UI Premium:** Interface responsiva com design focado em conversão.

---

## 🏗️ Arquitetura do Sistema

O sistema foi construído sob uma arquitetura de microsserviços (ou monólito modular), garantindo separação de responsabilidades.

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

* Node.js (v20+)
* JDK 21
* Docker (opcional, para rodar banco de dados local)

### Configuração do Back-end

1. Navegue até a pasta `api`.
2. Configure o seu `application.yml` com as credenciais do banco de dados.
3. Execute o comando:
```bash
./mvnw spring-boot:run

```



### Configuração do Front-end

1. Navegue até a pasta `front/barber-web`.
2. Instale as dependências:
```bash
npm install

```


3. Inicie o servidor de desenvolvimento:
```bash
npm run dev

```



---

## ✒️ Autores

**Ellen Monroe**

**Rodrigo Gois**

**Telma Regina**

**Davi Maia**

* Estudantes da Escola De Tecnologia na UNDB.

