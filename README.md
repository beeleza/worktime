# WorkTime

Este projeto oferece uma API REST para o registro de pontos de funcionários, onde você pode registrar entradas, saídas, retornos de almoço, e calcular as horas trabalhadas e o pagamento baseado nas horas trabalhadas.

## Endpoints
### 1. Registrar Entrada
`curl -X POST "http://localhost:8080/api/registro-ponto/entrada?funcionario=João&entrada=09:03&data=2025-02-01"`

### 2. Registrar Saída para o Almoço
`curl -X POST "http://localhost:8080/api/registro-ponto/saida-almoco/1?saidaAlmoco=13:00`

### 3. Registrar Retorno do Almoço
`curl -X POST "http://localhost:8080/api/registro-ponto/retorno-almoco/1?retornoAlmoco=15:00"`

### 4. Registrar Saída Final
`curl -X POST "http://localhost:8080/api/registro-ponto/saida/1?saida=19:00"`

### 5. Calcular Horas Trabalhadas
`curl -X GET "http://localhost:8080/api/registro-ponto/horas/1"`

### 6. Recuperar Registros de Ponto de um Mês
`curl -X GET "http://localhost:8080/api/registro-ponto/registros-mes?ano=2025&mes=2"`

### 7. Calcular Total de Horas Trabalhadas em um Mês
`curl -X GET "http://localhost:8080/api/registro-ponto/total-horas-mes?ano=2025&mes=2"`

### 8. Calcular Pagamento de um Funcionário
`curl -X GET "http://localhost:8080/api/registro-ponto/pagamento/1"`

### 9. Calcular Pagamento Total do Mês
`curl -X GET "http://localhost:8080/api/registro-ponto/pagamento-total-mes?ano=2025&mes=2"`

## Observações

- Formato de Hora: Todas as horas devem ser passadas no formato `HH:mm` (por exemplo, 09:03).
- Cálculo de Pagamento: O pagamento é calculado com base em R$ 6,90 por hora. A conversão de minutos para pagamento considera que 1 hora equivale a 60 minutos.
- Validação de Data: Certifique-se de passar uma data válida no formato `yyyy-MM-dd`.
