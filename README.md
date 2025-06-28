# 🔍 Observability for Spring Boot Microservices

This project demonstrates **end-to-end observability** for Java Spring Boot microservices using:

- 📈 **Prometheus** – metrics collection and alerting
- 📦 **Loki + Promtail** – structured log aggregation
- 📊 **Grafana** – unified dashboard visualization
- 🔎 **Jaeger** – distributed tracing (via Zipkin format ingestion)

> ✅ Supports **multi-tenancy**, enabling tenant-, project-, and user-level filtering across logs, metrics, and distributed traces.

---

## 🧩 Features

- **Multi-Tenant Observability**:  
  Logs and traces are enriched with tenant, project, and user IDs via MDC context.

- **Structured Logging**:  
  Logs follow a structured JSON format that is parsed and labeled by Promtail for advanced querying in Grafana.

- **Application Metrics**:  
  Metrics include JVM stats, HTTP request timing, datasource pool usage, etc., exported to Prometheus.

- **Distributed Tracing with Jaeger**:  
  All HTTP requests are traced across services with Zipkin-compatible trace IDs and spans.

---

## ⚙️ Tech Stack

| Component  | Tool                      |
|------------|---------------------------|
| Logs       | Loki + Promtail           |
| Metrics    | Prometheus                |
| Dashboards | Grafana                   |
| Tracing    | Jaeger (via Zipkin format)|
| Backend    | Spring Boot (Micrometer)  |
| Logging    | Logback + Logstash Encoder|

---

## 📂 Project Structure

├── logs/ # Application log output (tailed by Promtail) <br />
├── temp/ # Promtail position tracking <br />
├── devops/ promtail-config.yml # Log parsing config <br />
├── devops/ prometheus.yml # Prometheus scrape config <br />
├── devops/ docker-compose.yml # All observability services <br />
└── src/ # Spring Boot application <br />


---

## 🐳 Docker Compose Setup

Ensure Docker is installed and run:

in bash:
docker compose up -d --build

## This spins up:
- **Prometheus on localhost:9090**
- **Grafana on localhost:3000 (admin/admin)**
- **Loki on localhost:3100**
- **Promtail (log shipping)**
- **Jaeger on localhost:16686 (trace viewer)**

## Accessing Dashboards

| Service    | URL                                              |
| ---------- | ------------------------------------------------ |
| Grafana    | [http://localhost:3000](http://localhost:3000)   |
| Prometheus | [http://localhost:9090](http://localhost:9090)   |
| Loki       | [http://localhost:3100](http://localhost:3100)   |
| Jaeger     | [http://localhost:16686](http://localhost:16686) |

## 📌 Notes

- **Ensure your Spring Boot logs include traceId, tenantId, etc. via MDC.**
- **Jaeger ingests traces in Zipkin format on port 9411.**
- **Promtail only parses logs with valid JSON format. Logs from Hikari/etc. may not contain labels.**
- **In Grafana, use Explore > Log Labels or set up custom dashboards.**

## 🤝 Contributing
Pull requests and suggestions welcome! Focus is on observability tooling for production-grade Java microservices.
